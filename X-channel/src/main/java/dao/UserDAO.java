package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 * ユーザー情報を管理するDAOクラス
 * - ユーザーの登録
 * - ログイン時のユーザー認証
 */
public class UserDAO {
    
    // データベース接続情報
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/dokoTsubu";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    /**
     * ユーザーをデータベースに登録する
     * 
     * @param user 登録するユーザー情報
     * @return 登録成功: true / 失敗: false
     */
    public boolean registerUser(User user) {
        // JDBCドライバを読み込む
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO users (name, pass) VALUES (?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user.getName());
            pStmt.setString(2, user.getPass());

            int result = pStmt.executeUpdate();
            return result == 1; // 1行追加された場合にtrueを返す
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定されたユーザー情報に一致するユーザーをデータベースから検索
     * 
     * @param user 検索するユーザー情報（ユーザー名・パスワード）
     * @return ユーザーが見つかった場合: User オブジェクト / 見つからない場合: null
     */
    public User findUser(User user) {
        // JDBCドライバを読み込む
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM users WHERE name = ? AND pass = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user.getName());
            pStmt.setString(2, user.getPass());

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                return new User(id, name, pass); // Userオブジェクトを作成して返す
            }
            return null; // ユーザーが見つからなかった場合
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
