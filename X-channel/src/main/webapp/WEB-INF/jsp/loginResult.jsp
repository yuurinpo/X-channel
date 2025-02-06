<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% 
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser"); 

// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg"); 
%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>Xちゃんねる</title>

    <link rel="stylesheet" href="css/styles.css">
    <canvas id="canvas"></canvas>
    <script src="js/background.js"></script>
</head>

<body>
    <div class="standard-container">
        <h1>Xちゃんねる</h1>
        <h2>ログイン</h2>

        <% if (loginUser != null) { %>
            <!-- ログイン成功時のメッセージ -->
            <p>ログインに成功しました</p>
            <p>ようこそ<strong><%= loginUser.getName() %></strong>さん</p>

            <!-- メインページへのリンク -->
            <a href="Main">つぶやき投稿・閲覧へ</a>
        <% } else { %>
            <!-- ログイン失敗時のメッセージ -->
            <p>ログインに失敗しました</p>
            <p style="color: red"><%= errorMsg %></p>

            <!-- ログインページへのリンク -->
            <a href="index.jsp">TOPへ</a>
        <% } %>
    </div>
</body>

</html>
