package model;

import java.io.Serializable;

/**
 * ユーザー情報を管理するモデルクラス
 * - ユーザーID、ユーザー名、パスワードを管理
 * - データベースとのやり取りに利用
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ユーザーID */
    private int id;

    /** ユーザー名 */
    private String name;

    /** パスワード */
    private String pass;

    /** デフォルトコンストラクタ */
    public User() {}

    /**
     * 登録・存在チェック用コンストラクタ
     * @param name ユーザー名
     * @param pass パスワード
     */
    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    /**
     * フルデータ用コンストラクタ（データベース取得用）
     * @param id ユーザーID
     * @param name ユーザー名
     * @param pass パスワード
     */
    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    /** ユーザーIDの取得 */
    public int getId() { return id; }

    /** ユーザーIDの設定 */
    public void setId(int id) { this.id = id; }

    /** ユーザー名の取得 */
    public String getName() { return name; }

    /** ユーザー名の設定 */
    public void setName(String name) { this.name = name; }

    /** パスワードの取得 */
    public String getPass() { return pass; }

    /** パスワードの設定 */
    public void setPass(String pass) { this.pass = pass; }
}
