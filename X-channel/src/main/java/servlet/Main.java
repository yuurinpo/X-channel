package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * メイン画面の処理を担当するサーブレット
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * GETリクエストの処理
     * - つぶやきリストを取得し、リクエストスコープに保存
     * - ログイン済みか確認し、未ログインならログイン画面へリダイレクト
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // つぶやきリストを取得し、リクエストスコープに保存
        GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
        List<Mutter> mutterList = getMutterListLogic.execute();
        request.setAttribute("mutterList", mutterList);

        // セッションスコープからユーザー情報を取得し、ログイン状態を確認
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            // 未ログインの場合はログイン画面へリダイレクト
            response.sendRedirect("index.jsp");
        } else {
            // ログイン済みの場合はメイン画面 (`main.jsp`) にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * POSTリクエストの処理
     * - つぶやきの投稿を処理し、バリデーション後、DBに保存
     * - 投稿がない場合はエラーメッセージを表示
     * - 最新のつぶやきリストを取得し、メイン画面へフォワード
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 文字エンコーディングをUTF-8に設定（日本語対応）
        request.setCharacterEncoding("UTF-8");

        // リクエストパラメータから投稿内容を取得
        String text = request.getParameter("text");

        if (text != null && !text.isEmpty()) {
            // セッションスコープからログインユーザー情報を取得
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");

            // ログイン中のユーザー名と投稿内容で `Mutter` インスタンスを生成し、DBに登録
            Mutter mutter = new Mutter(loginUser.getName(), text);
            PostMutterLogic postMutterLogic = new PostMutterLogic();
            postMutterLogic.execute(mutter);
        } else {
            // 入力がない場合はエラーメッセージをリクエストスコープに保存
            request.setAttribute("errorMsg", "投稿が入力されていません");
        }

        // 最新のつぶやきリストを取得し、リクエストスコープに保存
        GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
        List<Mutter> mutterList = getMutterListLogic.execute();
        request.setAttribute("mutterList", mutterList);

        // メイン画面 (`main.jsp`) にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}
