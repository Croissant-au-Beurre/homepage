

//スクロールとブリンクの設定
function setScrollAndBlink() {
    //対象メッセージ
    var blog = localStorage.getItem("seq");
    localStorage.removeItem("seq");
    if (blog == "" || blog == null || blog == undefined) {
        //対象メッセージはない場合
        return;
    }

    //対象メッセージのインデックス
    var index;

    //対象メッセージ行ループ
    $(".blog-detail").each(function (i) {
        if (String($(this).data("seq")) == blog) {

            index = i;
        }
    });
    //枠
    var windowSize = window.innerWidth;

    if (windowSize > 992) {
        var w = $(".resumify_tm_blog_list").parent();
    } else {
        var w = $(".container");
    }

    //行
    var row = $(".resumify_tm_blog_list").find(".blog-detail").eq(index);

    if (row.length) {
        //行のオフセット(上)
        var rowOffsetTop = row.offset().top;

        if (windowSize > 992) {
            //スクロールの設定
            w.scrollTop(rowOffsetTop - 380);
        } else {
            w.scrollTop(rowOffsetTop - 70);
        }
    }

    //行のセルループ
    $(row)
        .find("li")
        .each(function () {
            //ブリンククラスの追加
            $(this).addClass("blink");
        });
}