<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <!-- ページの文字コードをUTF-8に設定 -->
    <meta charset="UTF-8">
    <title>Xちゃんねる</title>
    
    <!-- CSSスタイルシートの読み込み -->
    <link rel="stylesheet" href="css/styles.css">
    
    <!-- 背景アニメーション用のキャンバス設定 -->
    <canvas id="canvas"></canvas>
    
    <!-- 背景アニメーション用のJavaScriptファイルを読み込み -->
    <script src="js/background.js"></script>
</head>

<body>
    <!-- コンテンツを中央に配置 -->
    <div class="standard-container">
        <h1>Xちゃんねる</h1>
        <h2>ログイン</h2>

        <!-- ログインフォーム -->
        <form action="Login" method="post">
            <input type="text" name="name" placeholder="ユーザー名を入力"><br>
            <input type="password" name="pass" placeholder="パスワードを入力"><br>
            <input type="submit" value="ログイン">
        </form>

        <!-- 会員登録ページへのリンク -->
        <a href="Register">会員登録はこちら</a>
    </div>
</body>

</html>