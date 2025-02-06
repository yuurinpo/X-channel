package model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * つぶやき（投稿）を管理するデータモデルクラス
 * - つぶやきの ID、ユーザー名、投稿内容、投稿日時を保持する
 */
public class Mutter implements Serializable {
  
    private int id;          // つぶやきの一意なID
    private String userName; // 投稿者のユーザー名
    private String text;     // つぶやき内容
    private Timestamp createdAt; // 投稿日時（データベースから取得する）

    /**
     * デフォルトコンストラクタ
     * - オブジェクトを生成する際に利用
     */
    public Mutter() {}

    /**
     * つぶやき投稿用コンストラクタ
     * - 投稿時にユーザー名とテキストを設定
     *
     * @param userName 投稿者のユーザー名
     * @param text 投稿内容
     */
    public Mutter(String userName, String text) {
        this.userName = userName;
        this.text = text;
    }

    /**
     * つぶやき表示用コンストラクタ
     * - データベースから取得した情報をオブジェクトとして保持
     *
     * @param id つぶやきの ID
     * @param userName 投稿者のユーザー名
     * @param text 投稿内容
     * @param createdAt 投稿日時
     */
    public Mutter(int id, String userName, String text, Timestamp createdAt) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.createdAt = createdAt;
    }

    /** @return つぶやきの ID */
    public int getId() { return id; }

    /** @return 投稿者のユーザー名 */
    public String getUserName() { return userName; }

    /** @return 投稿内容 */
    public String getText() { return text; }

    /** @return 投稿日時 */
    public Timestamp getCreatedAt() { return createdAt; }
}
