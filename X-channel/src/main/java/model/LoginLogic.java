package model;

import dao.UserDAO;

/**
 * ユーザーログイン処理を行うロジッククラス
 * - データベース内のユーザー情報を確認し、認証を行う
 */
public class LoginLogic {

    /**
     * ユーザーの存在チェックおよび認証処理
     * - データベース内に一致するユーザー情報があるか確認
     *
     * @param user ログイン時に入力されたユーザー情報
     * @return 認証されたユーザー情報（認証失敗の場合は null）
     */
    public User find(User user) {
        UserDAO dao = new UserDAO();
        return dao.findUser(user);
    }
}
