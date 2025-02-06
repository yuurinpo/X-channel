package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

/**
 * つぶやき情報を管理するDAOクラス
 * - つぶやきの取得
 * - つぶやきの登録
 * - つぶやきの検索
 * - つぶやきの削除
 */
public class MuttersDAO {
    
    // データベース接続情報
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/dokoTsubu";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    /**
     * すべてのつぶやきを取得する
     * 
     * @return つぶやきリスト (投稿がない場合は空リスト)
     */
    public List<Mutter> findAll() {
        List<Mutter> mutterList = new ArrayList<>();

        // JDBCドライバを読み込む
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT ID, NAME, TEXT, CREATED_AT FROM MUTTERS ORDER BY ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            // 結果をリストに格納
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");

                Mutter mutter = new Mutter(id, userName, text, createdAt);
                mutterList.add(mutter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mutterList;
    }

    /**
     * 新しいつぶやきをデータベースに登録する
     * 
     * @param mutter 登録するつぶやき情報
     * @return 登録成功: true / 失敗: false
     */
    public boolean create(Mutter mutter) {
        // JDBCドライバを読み込む
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO MUTTERS(NAME, TEXT) VALUES(?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, mutter.getUserName());
            pStmt.setString(2, mutter.getText());

            int result = pStmt.executeUpdate();
            return result == 1; // 成功すれば true を返す
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定されたキーワードを含むつぶやきを検索する
     * 
     * @param keyword 検索キーワード
     * @return 検索結果のつぶやきリスト (該当なしの場合は空リスト)
     */
    public List<Mutter> search(String keyword) {
        List<Mutter> mutterList = new ArrayList<>();

        // JDBCドライバを読み込む
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM MUTTERS " +
                         "WHERE CAST(ID AS CHAR) LIKE ? " +
                         "   OR NAME LIKE ? " +
                         "   OR TEXT LIKE ? " +
                         "ORDER BY ID DESC";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            pStmt.setString(1, searchPattern);
            pStmt.setString(2, searchPattern);
            pStmt.setString(3, searchPattern);

            ResultSet rs = pStmt.executeQuery();

            // 結果をリストに格納
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");

                Mutter mutter = new Mutter(id, userName, text, createdAt);
                mutterList.add(mutter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mutterList;
    }

    /**
     * 指定された ID のつぶやきを削除する
     * 
     * @param id 削除するつぶやきの ID
     * @return 削除成功: true / 失敗: false
     */
    public boolean delete(int id) {
        // JDBCドライバを読み込む
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM MUTTERS WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);

            int result = pStmt.executeUpdate();
            return result == 1; // 成功すれば true を返す
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
