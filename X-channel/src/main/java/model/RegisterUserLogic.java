package model;

import dao.UserDAO;

/**
 * ユーザー登録処理を行うロジッククラス
 * - 入力されたユーザー情報をデータベースに登録する
 */
public class RegisterUserLogic {

    /**
     * ユーザーをデータベースに登録する
     * @param user 登録するユーザー情報（ユーザー名・パスワード）
     * @return 登録成功時は true、失敗時は false
     */
    public boolean execute(User user) {
        // UserDAOのインスタンスを生成
        UserDAO dao = new UserDAO();
        
        // ユーザー登録処理を実行し、結果を返す
        return dao.registerUser(user);
    }
}
