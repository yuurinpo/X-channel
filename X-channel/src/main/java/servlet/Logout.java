package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ログアウト処理を担当するサーブレット
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * GETリクエストの処理
     * - セッションを無効化（ログアウト処理）
     * - ログアウト画面へフォワード
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // セッションスコープを破棄（ログアウト処理）
        HttpSession session = request.getSession();
        session.invalidate();

        // ログアウト画面 (`logout.jsp`) にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/logout.jsp");
        dispatcher.forward(request, response);
    }
}
