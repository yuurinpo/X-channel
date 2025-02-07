# 掲示板システム (Xちゃんねる)

ご覧いただき、ありがとうございます。個人的に初めてのアプリケーションを作成してみました。<br>
Xと2ちゃんねるを掛け合わせた「Xちゃんねる」掲示板アプリケーションです。

## 画面遷移
<img src="https://github.com/user-attachments/assets/a3a47b80-d193-43ec-873d-2ddfc52f3ea0" alt="ログイン画面" width="400">
<img src="https://github.com/user-attachments/assets/cd06960f-2e66-4aa9-ac8a-6a5b0d09ef2a" alt="会員登録画面" width="400">
<img src="https://github.com/user-attachments/assets/98909d9e-9754-4870-a3b6-89e1cbb108cb" alt="掲示板ホーム画面" width="400">
<img src="https://github.com/user-attachments/assets/98909d9e-9754-4870-a3b6-89e1cbb108cb" alt="検索画面" width="400">

## 環境
 * ローカル環境

## 特徴
 * データベースに会員情報を登録して、登録された会員情報のみログインできる
 * 掲示板画面では自分が投稿した投稿のみ削除ボタンから削除ができる
 * 検索機能では、投稿者の名前、投稿内容に一致する投稿が一覧として表示される

## 工夫した点
 * 背景デザインにjavascriptを加えて動きを入れてみた点
 * MVCモデルに沿ってクラスをそれぞれの機能で分けて作成してみた点
 * DBから取得した情報によって処理をif文によって変えてみた点

## 作成期間
 * 約60日間

## 今後の課題、追加したい機能
 * 投稿一覧に表示される削除機能に加えて、編集機能を追加し、更に編集後に編集前の時間のまま投稿一覧に表示される機能を追加したい
 * 特定の管理者のみに全ての投稿を削除できる機能も追加できると便利なアプリケーションになると思う
 * ローカル環境でしか実行できないのでサーバーを借りて、DBを連携させてみたい

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

- - -

## 使用技術とバージョン情報

### 言語
- **Java**: 21.0.4+7-LTS

### フレームワーク
- **Jakarta Servlet**: バージョン 5.0

### ライブラリ
- **H2 Database**: バージョン 2.3.232 (リリース日: 2024-08-11)

### サーバー
- **Apache Tomcat**: バージョン 10.1.29

### 開発環境
- **Eclipse IDE for Java Developers**: バージョン 2024-09 (4.33.0)

### 使用技術
- **Git/GitHub**: ソースコードのバージョン管理
- **JDBC (Java Database Connectivity)**: データベース接続
- **HTML5 / CSS3**: フロントエンドのUIデザイン
- **JavaScript (Canvas API)**: 背景アニメーションの実装

