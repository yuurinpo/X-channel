# 掲示板システム (Xちゃんねる)

個人的に経験としてXと2ちゃんねるを真似して作ってみました。  

## 環境
 * ローカル環境

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
