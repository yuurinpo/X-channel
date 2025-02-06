package model;

import dao.MuttersDAO;

/**
 * つぶやきを削除するロジッククラス
 * - 指定されたIDのつぶやきをデータベースから削除する
 */
public class DeleteMutterLogic {

    /**
     * つぶやきを削除
     * - IDを基にデータベースの投稿を削除する
     *
     * @param strId 削除対象のつぶやきID（文字列型）
     */
    public void execute(String strId) {
        System.out.println("削除対象ID: " + strId);

        // IDをint型に変換
        int id = Integer.parseInt(strId);
        
        // DAOを呼び出してつぶやきを削除
        MuttersDAO dao = new MuttersDAO();
        dao.delete(id);
    }
}
