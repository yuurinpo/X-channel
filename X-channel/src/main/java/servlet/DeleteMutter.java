package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DeleteMutterLogic;

/**
 * つぶやき削除処理を担当するサーブレット
 */
@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * GETリクエストの処理
     * - リクエストパラメータからつぶやきIDを取得
     * - つぶやき削除処理 (`DeleteMutterLogic`) を実行
     * - 削除後、`Main.jsp` へリダイレクト
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータの取得（エンコーディング設定）
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        System.out.println("削除対象ID: " + id); // デバッグ用ログ

        // `DeleteMutterLogic` インスタンスの生成と処理実行
        DeleteMutterLogic delMutterLogic = new DeleteMutterLogic();
        delMutterLogic.execute(id);

        // 削除後、メインページ (`Main.jsp`) にリダイレクト
        response.sendRedirect(request.getContextPath() + "/Main");
    }
}
