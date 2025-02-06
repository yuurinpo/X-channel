package model;

import java.util.Collections;
import java.util.List;

import dao.MuttersDAO;

/**
 * 投稿の検索処理を行うロジッククラス
 * - ユーザーが指定したキーワードに一致する投稿を検索
 */
public class SearchMutterLogic {

    /**
     * 指定されたキーワードで投稿を検索し、結果をリストとして返す
     * @param keyword 検索キーワード
     * @return 検索結果のリスト（該当なしの場合は空リスト）
     */
    public List<Mutter> execute(String keyword) {
        // 検索キーワードがnullまたは空の場合は空リストを返す
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // DAOを呼び出して検索結果を取得
        MuttersDAO dao = new MuttersDAO();
        return dao.search(keyword);
    }
}
