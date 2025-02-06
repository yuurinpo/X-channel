package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.RegisterUserLogic;
import model.User;

/**
 * ユーザー登録処理を担当するサーブレット
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    /**
     * ユーザー登録フォームを表示 (GETリクエスト)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ユーザー登録フォーム (`registerView.jsp`) へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerView.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * ユーザー登録処理 (POSTリクエスト)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 文字エンコーディングをUTF-8に設定（日本語対応）
        request.setCharacterEncoding("UTF-8");

        // フォームデータを取得
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 入力バリデーション（ユーザー名とパスワードが未入力でないか確認）
        if ((username != null && !username.isEmpty()) && (password != null && !password.isEmpty())) {
            
            System.out.println("ユーザー登録処理開始");

            // ユーザー情報を作成し、データベースに登録
            User user = new User(username, password);
            RegisterUserLogic registerUserLogic = new RegisterUserLogic();
            boolean result = registerUserLogic.execute(user);

            if (result) {
                // 登録成功時：登録完了画面 (`registerResult.jsp`) にフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
                dispatcher.forward(request, response);
            } else {
                // 登録失敗時（既存ユーザー名）エラーメッセージをセットし、登録画面 (`registerView.jsp`) にフォワード
                request.setAttribute("errorMsg", "このユーザー名は既に使用されています。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerView.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // 入力エラー（ユーザー名またはパスワードが空の場合）
            request.setAttribute("errorMsg", "ユーザー名とパスワードを入力してください。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
