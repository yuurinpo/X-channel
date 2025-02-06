// ==============================
// 背景アニメーション用の Canvas 設定
// ==============================

// Canvas 要素を取得
var Canvas = document.getElementById('canvas');
var ctx = Canvas.getContext('2d');

// 画面サイズに適応するように Canvas のサイズを変更する
var resize = function() {
    Canvas.width = window.innerWidth * window.devicePixelRatio;  // 高解像度対応
    Canvas.height = window.innerHeight * window.devicePixelRatio; // 高解像度対応
    ctx.scale(window.devicePixelRatio, window.devicePixelRatio); // 描画スケール調整
};

// ウィンドウサイズ変更時に再計算
window.addEventListener('resize', resize);
resize();

// ==============================
// 背景アニメーションの設定
// ==============================

var elements = [];  // 描画要素のリスト
var presets = {};  // 図形のプリセット

// 丸 (〇) を描画するオブジェクト
presets.o = function(x, y, s, dx, dy) {
    return {
        x: x,
        y: y,
        r: 12 * s,  // 半径
        w: 5 * s,   // 線の太さ
        dx: dx,     // X 軸移動量
        dy: dy,     // Y 軸移動量
        draw: function(ctx, t) {
            this.x += this.dx;
            this.y += this.dy;

            ctx.beginPath();
            ctx.arc(
                this.x + Math.sin((50 + x + (t / 10)) / 100) * 3,
                this.y + Math.sin((45 + x + (t / 10)) / 100) * 4,
                this.r,
                0,
                2 * Math.PI,
                false
            );
            ctx.lineWidth = this.w;
            ctx.strokeStyle = '#fff';  // 白色の線
            ctx.stroke();
        }
    };
};

// ✕ (X) を描画するオブジェクト
presets.x = function(x, y, s, dx, dy, dr, r) {
    r = r || 0;  // 初期回転角度

    return {
        x: x,
        y: y,
        s: 20 * s,  // サイズ
        w: 5 * s,   // 線の太さ
        r: r,       // 回転角度
        dx: dx,     // X 軸移動量
        dy: dy,     // Y 軸移動量
        dr: dr,     // 回転速度
        draw: function(ctx, t) {
            this.x += this.dx;
            this.y += this.dy;
            this.r += this.dr;

            ctx.save();

            ctx.translate(
                this.x + Math.sin((x + (t / 10)) / 100) * 5,
                this.y + Math.sin((10 + x + (t / 10)) / 100) * 2
            );
            ctx.rotate(this.r * Math.PI / 180);

            // X の線を描画する関数
            var drawLine = function(x, y, tx, ty, o) {
                o = o || 0;
                ctx.beginPath();
                ctx.moveTo(-o + ((this.s / 2) * x), o + ((this.s / 2) * y));
                ctx.lineTo(-o + ((this.s / 2) * tx), o + ((this.s / 2) * ty));
                ctx.lineWidth = this.w;
                ctx.strokeStyle = '#fff';
                ctx.stroke();
            }.bind(this);

            drawLine(-1, -1, 1, 1);
            drawLine(1, -1, -1, 1);

            ctx.restore();
        }
    };
};

// ==============================
// 要素をランダムに生成
// ==============================

for (var x = 0; x < Canvas.width; x++) {
    for (var y = 0; y < Canvas.height; y++) {
        if (Math.round(Math.random() * 8000) === 1) {
            var s = ((Math.random() * 5) + 1) / 10;
            if (Math.round(Math.random()) === 1) {
                elements.push(presets.o(x, y, s, 0, 0));  // ○ (丸) を追加
            } else {
                elements.push(presets.x(x, y, s, 0, 0, ((Math.random() * 3) - 1) / 10, (Math.random() * 360)));  // ✕ (X) を追加
            }
        }
    }
}

// ==============================
// アニメーションの実行
// ==============================

setInterval(function() {
    ctx.clearRect(0, 0, Canvas.width, Canvas.height);

    var time = new Date().getTime();
    elements.forEach(function(element) {
        element.draw(ctx, time);
    });
}, 10);
