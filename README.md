# 掲示板システム (Xちゃんねる)

個人的に経験としてXと2ちゃんねるを真似して作ってみました。  

## 画面遷移
![ログイン画面](https://github.com/user-attachments/assets/a3a47b80-d193-43ec-873d-2ddfc52f3ea0)
![会員登録画面](https://github.com/user-attachments/assets/cd06960f-2e66-4aa9-ac8a-6a5b0d09ef2a)
![掲示板ホーム画面](https://github.com/user-attachments/assets/98909d9e-9754-4870-a3b6-89e1cbb108cb)

## 環境
 * ローカル環境

## 特徴
 * データベースに会員情報を登録して、登録された会員情報のみログインできる
 * 掲示板画面では自分が投稿したもののみ削除ボタンから削除ができる

## X-channelの実行手順
 * 1.H2 Databseのインストール
 * 2.H2 Databseの起動
 * 3.データベースに接続
 * 4.index.jspを実行する

- - -

## 実行手順詳細
### H2 Databseのインストール
 * https://www.h2database.com/html/main.html
 * 上記のH2 Databaseのサイトにアクセスし、「Download」のかこみに記載されている「All Platforms」のリンクをクリックし、zipファイルを適当な場所に保存します。
 * ダウンロードしたzipファイルを解凍します。「ターミナル」を起動
 * 「ターミナル」を起動
 * 「cd <H2 Databaseの場所>/bin」にカレントディレクトリを移動
 * 「./h2.sh」で、H2コンソール起動
### H2 Databseに接続
 * 保存済み設定で「Generic H2(Server)」を選択します。
 * JDBC URLはjdbc:h2:tcp://localhost/~/dokoTsubu
 * 「接続」をクリックします。
