package model;

import java.util.List;

import dao.MuttersDAO;

/**
 * つぶやきリストを取得するロジッククラス
 * - データベースからすべての投稿（Mutter）を取得する
 */
public class GetMutterListLogic {

    /**
     * つぶやきリストを取得
     * - データベースから全ての投稿データを取得する
     *
     * @return つぶやきのリスト（Mutterオブジェクトのリスト）
     */
    public List<Mutter> execute() {
        MuttersDAO dao = new MuttersDAO();
        return dao.findAll();
    }
}
