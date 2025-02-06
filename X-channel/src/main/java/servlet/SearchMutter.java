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

import model.Mutter;
import model.SearchMutterLogic;
import model.User;

/**
 * つぶやき検索処理を担当するサーブレット
 */
@WebServlet("/SearchMutter")
public class SearchMutter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETリクエストを処理
	 * 検索キーワードを取得し、つぶやきの検索結果を表示
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 文字エンコーディングをUTF-8に設定（日本語対応）
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメーターから検索キーワードを取得
		String keyword = request.getParameter("keyword");

		// 検索ロジックを実行し、検索結果のつぶやきリストを取得
		SearchMutterLogic searchMutterLogic = new SearchMutterLogic();
		List<Mutter> mutterList = searchMutterLogic.execute(keyword);

		// 検索結果をリクエストスコープに保存
		request.setAttribute("mutterList", mutterList);

		// セッションスコープからログインユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		// ログインしていない場合はトップページへリダイレクト
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// ログイン済みの場合はメインページへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
}
