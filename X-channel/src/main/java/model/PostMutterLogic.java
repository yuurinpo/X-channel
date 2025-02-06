package model;

import dao.MuttersDAO;

/**
 * 投稿（つぶやき）をデータベースに登録するロジッククラス
 * - 入力された投稿内容を `MuttersDAO` を通じてデータベースに保存する
 */
public class PostMutterLogic {

    /**
     * 投稿（つぶやき）をデータベースに登録する
     * @param mutter 投稿内容を含む `Mutter` オブジェクト
     * @return 登録成功時は true、失敗時は false
     */
    public boolean execute(Mutter mutter) {
        // MuttersDAO のインスタンスを生成
        MuttersDAO dao = new MuttersDAO();
        
        // 投稿をデータベースに登録し、その結果を返す
        return dao.create(mutter);
    }
}
