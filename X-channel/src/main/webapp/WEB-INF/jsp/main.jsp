<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
    // 日付フォーマットの設定
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    // セッションスコープからユーザー情報を取得
    User loginUser = (User) session.getAttribute("loginUser");

    // リクエストスコープからつぶやきリストを取得
    List<Mutter> mutterList = (List<Mutter>) request.getAttribute("mutterList");

    // リクエストスコープに保存されたエラーメッセージを取得
    String errorMsg = (String) request.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xちゃんねる</title>

    <!-- CSSファイル -->
    <link rel="stylesheet" href="css/styles.css">

    <!-- 背景アニメーション -->
    <canvas id="canvas"></canvas>
    <script src="js/background.js"></script>
</head>

<body>
    <div class="container">

        <!-- 左サイドバー -->
        <aside class="sidebar-left">
            <header>
                <h2>Xちゃんねる</h2>
            </header>
            <% if (loginUser != null) { %>
                <p><%= loginUser.getName() %>さん<br>ログイン中</p>
            <% } %>

            <a href="Main">ホームへ</a>
            <a href="Logout">ログアウト</a>
        </aside>

        <!-- メインコンテンツ -->
        <main class="main-content">
            <header>
                <h1>掲示板</h1>
            </header>

            <!-- 投稿フォーム -->
            <div class="box tweet-form">
                <form action="Main" method="post">
                    <textarea name="text" placeholder="投稿を入力" rows="4" cols="50" style="resize: none;"></textarea>
                    <button type="submit">投稿</button>
                </form>
            </div>

            <!-- エラーメッセージの表示 -->
            <% if (errorMsg != null && !errorMsg.isEmpty()) { %>
                <p class="error"><%= errorMsg %></p>
            <% } %>

            <!-- 投稿リストの表示 -->
            <% if (mutterList != null && !mutterList.isEmpty()) { %>
                <ul class="tweet-list">
                    <% for (Mutter mutter : mutterList) { %>
                        <li>
                            <!-- 投稿者と投稿日時 -->
                            <p>
                                <strong>@<%= mutter.getUserName() %></strong>
                                <small><%= sdf.format(mutter.getCreatedAt()) %></small>
                                <br>
                                <%= mutter.getText().replace("\n", "<br>") %>
                                <br>
                            </p>

                            <% if (loginUser != null && loginUser.getName().equals(mutter.getUserName())) { %>
                                <!-- 削除ボタン -->
                                <div class="button-container">
                                    <a href="DeleteMutter?id=<%= mutter.getId() %>">削除</a>
                                </div>
                            <% } %>
                        </li>
                    <% } %>
                </ul>
            <% } else { %>
                <p>検索ワードに一致する投稿はありません。</p>
            <% } %>
        </main>

        <!-- 右サイドバー（検索フォーム） -->
        <aside class="sidebar-right">
            <h3>投稿検索</h3>
            <form action="<%= request.getContextPath() %>/SearchMutter" method="get" class="search-form">
                <input type="text" name="keyword" placeholder="サイト内を検索">
                <button type="submit">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" width="20" height="20">
                        <circle cx="11" cy="11" r="8" stroke="#2a2a2a" stroke-width="2"></circle>
                        <line x1="16" y1="16" x2="20" y2="20" stroke="#2a2a2a" stroke-width="2"></line>
                    </svg>
                </button>
            </form>
        </aside>
    </div>
</body>

</html>
