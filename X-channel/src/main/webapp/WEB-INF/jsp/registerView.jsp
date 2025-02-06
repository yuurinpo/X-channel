<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg"); 
%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>ユーザー登録</title>

    <link rel="stylesheet" href="css/styles.css">
    <canvas id="canvas"></canvas>
    <script src="js/background.js"></script>
</head>

<body>
    <div class="standard-container">
        <h1>Xちゃんねる</h1>
        <h2>ユーザー登録</h2>

        <!-- ユーザー登録フォーム -->
        <form method="post" action="Register">
            <input type="text" name="username" placeholder="ユーザー名を入力"><br>
            <input type="password" name="password" placeholder="パスワードを入力"><br>
            <input type="submit" value="登録">
        </form>

        <!-- エラーメッセージの表示 -->
        <% if (errorMsg != null) { %>
            <p style="color: red"><%= errorMsg %></p>
        <% } %>

        <!-- ログインページへのリンク -->
        <a href="index.jsp">ログインへ戻る</a>
    </div>
</body>

</html>
