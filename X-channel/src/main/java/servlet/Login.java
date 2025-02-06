package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

/**
 * ログイン処理を担当するサーブレット
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * POSTリクエストの処理
     * - ユーザー名・パスワードを取得
     * - 認証処理（DBチェック）
     * - セッションにログイン情報を保存
     * - ログイン結果画面 (`loginResult.jsp`) にフォワード
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // リクエストパラメータの取得（エンコーディング設定）
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // 入力チェック（ユーザー名とパスワードが未入力でないか確認）
        if ((name != null && !name.isEmpty()) && (pass != null && !pass.isEmpty())) {
            
            // ユーザーインスタンスを作成
            User user = new User(name, pass);
            LoginLogic loginLogic = new LoginLogic();
            
            // DBにユーザーが存在するか確認（パスワードチェック込み）
            User findUser = loginLogic.find(user);
            
            if (findUser != null) {
                // ログイン成功時、セッションスコープにユーザー情報を保存
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", findUser);
            } else {
                // 認証失敗時、エラーメッセージをリクエストスコープに保存
                request.setAttribute("errorMsg", "パスワードが間違っているか、ユーザーが未登録です。");
            }
            
        } else {
            // 入力不足時のエラーメッセージ
            request.setAttribute("errorMsg", "必要項目が未入力です。");
        }
        
        // ログイン結果画面 (`loginResult.jsp`) にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request, response);
    }
}
