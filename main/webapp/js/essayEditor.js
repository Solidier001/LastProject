var tip = "新格式在此编辑，请在编辑完并写完改格式外内容后删除外侧**号";

var listtip = "列表在此编辑，请在编辑完并写完改格式外内容后删除外侧**号";

function getObjectURL(file) {
    var url = null;
    var windowURL = window.URL || window.webkitURL;
    url = webkitURL.createObjectURL(file);
    return url;
}

function insertHtmlAtCaret(html) {
    var sel, range;
    if (window.getSelection) {
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            range = sel.getRangeAt(0);
            range.deleteContents();
            var el = document.createElement("div");
            el.innerHTML = html;
            range.insertNode(document.createTextNode(html));
        }

    } else if (document.selection && document.selection.type != "Control") {
        document.selection.createRange().pasteHTML(html);
    }
}


var callback = function () {
    $("#reachEdit>div").css("text-indent", $("#reachEdit>div").first().css("text-indent"));
    $("#reachEdit+.show").html($("#reachEdit").html());
    $(".Editor>.show>*").attr("contenteditable", "false");
}

var options = {
    childList: true,
    characterData: true,
    subtree: true,
    attributes: true
}
var observer = new MutationObserver(callback);

var reachEdit = document.getElementById("reachEdit");
observer.observe(reachEdit, options);

function fontcontent(layero, index) {
    layer.style(index, {
        width: '405px'
    });
    $(".fonts>div").click(function () {
        $(".font-chooser>.show").text($(this).text());
        $(".font-chooser>.show").css("font-family", $(this).text());
    });
    $(".font-content>.comfirm>button").click(function () {
        var r = $("#reachEdit>div").last().html();
        var font = $(".font-chooser>.show").css("font-family");
        if (r.substr(r.length - 9) == "</span>**") {
            $("#reachEdit>div").last().children("span").last().css("font-family", font);
        } else {
            var span = document.createElement("span");
            $(span).css("font-family", font);
            $(span).text(tip);
            $("#reachEdit>div").last().append("**");
            $("#reachEdit>div").last().append(span);
            $("#reachEdit>div").last().append("**");
        }
        layer.close(index);
    });
}

function fontwidth(layero, index) {
    layui.use('slider', function () {
        var slider = layui.slider;
        slider.render({
            elem: '#slider',
            type: 'vertical',
            min: 0,
            max: 100,
            value: 14,
            height: 200,
            change: function (value) {
                $(".text-width-content .sample").css("font-size", value + 'px');
            }
        });
    });

    $(".text-width-content button").click(function (e) {
        var r = $("#reachEdit>div").last().html();
        if (r.substr(r.length - 9) == "</span>**") {
            $("#reachEdit>div").last().children("span").last().css("font-size", $(".text-width-content .sample").css("font-size"))
        } else {
            var span = document.createElement("span");
            $(span).css("font-size", $(".text-width-content .sample").css("font-size"));
            $(span).text(tip);
            $("#reachEdit>div").last().append("**");
            $("#reachEdit>div").last().append(span);
            $("#reachEdit>div").last().append("**");
        }
        layer.close(index);
    });
}

function indentcontent(layero, index) {
    layer.style(index, {
        width: '405px'
    });
    $(".indent-value>div").click(function (e) {
        $(".indent-chooser>.show>div").css("text-indent", $(this).text() + "em");
    });
    $(".indent-content>.comfirm>button").click(function (e) {
        var indentvalue = $(".indent-chooser>.show>div").css("text-indent");
        $("#reachEdit>div").css("text-indent", indentvalue);
        layer.close(index);
    });
}

function linkcontent(layero, index) {
    layer.style(index, {
        width: "500px",
        height: "175px"
    });
    $(".link-content button").click(function (e) {
        var r = $("#reachEdit>div").last().html();
        r = r.substr(r.length - 9);
        var a = document.createElement("a");
        $(a).text($(".layui-layer-content .inputtext input").val());
        $(a).attr("href", $(".layui-layer-content .inputlink input").val());
        if (r == "</span>**" || r == "</span>") {
            $("#reachEdit>div").last().children("span").last().append(a)
        } else {
            $("#reachEdit>div").last().append(a);
        }
        layer.close(index);
    });
}

function headingcontent(layero, index) {
    layer.style(index, {
        width: "347px",
        height: "302px"
    });
    $(".heading-content .style>div>div:first-child>*").click(function () {
        $(".heading-content .style>div>.show").html($(this)[0].outerHTML);
    });
    $(".heading-content .confirm>button").click(function (e) {
        var r = $("#reachEdit>div").last().html();
        r = r.substr(r.length - 9);
        var h = document.createElement($(".layui-layer-content .heading-content .style>div>.show").children()[0].tagName);
        $(h).text($(".layui-layer-content .heading-content .input .tittle").val());
        if (r == "</span>**" || r == "</span>") {
            $("#reachEdit>div").last().children("span").last().append(h)
        } else {
            $("#reachEdit>div").last().append(h);
        }
        layer.close(index);
    });
}

function imagecontent(layero, index) {
    layer.style(index, {
        width: '405px'
    });
    var imglay = document.getElementsByClassName("image-content")[0];
    $(".image-content>.chooser-content button").click(function (e) {
        var file = imglay.getElementsByClassName("file")[0];
        var a = document.createEvent("MouseEvents");
        a.initEvent("click", true, true);
        file.dispatchEvent(a);
    });
    $(".image-content>.chooser-content .file").change(function (e) {
        var file = imglay.getElementsByClassName("file")[0];
        var objURL = getObjectURL(file.files[0]);
        $(".image-content>.chooser-content .imginfor").text(file.files[0].name)
        $(".image-content img").attr("src", objURL);
    });
    $(".image-content .confirm button").click(function (e) {
        var img = document.createElement("img");
        $(img).attr("src", $(".image-content img").attr("src"));
        $(".Editor>.input>div").append(img);
        layer.close(index);
    });
}

function videocontent(layero, index) {
    layer.style(index, {
        width: '405px'
    });
    var videolay = document.getElementsByClassName("video-content")[0];
    $(".video-content>.chooser-content button").click(function (e) {
        var file = videolay.getElementsByClassName("file")[0];
        var a = document.createEvent("MouseEvents");
        a.initEvent("click", true, true);
        file.dispatchEvent(a);
    });
    $(".video-content>.chooser-content .file").change(function (e) {
        var file = videolay.getElementsByClassName("file")[0];
        var objURL = getObjectURL(file.files[0]);
        $(".video-content>.chooser-content .videoinfor").text(file.files[0].name)
        $(".video-content video").attr("src", objURL);
    });
    $(".video-content .confirm button").click(function (e) {
        var video = document.createElement("video");
        $(video).attr("src", $(".video-content video").attr("src"));
        $(video).attr("controls", $(".video-content video").attr("controls"));
        $(video).attr("type", $(".video-content video").attr("type"));
        $(video).attr("width", $(".video-content video").attr("width"));
        $(video).attr("height", $(".video-content video").attr("height"));
        $(".Editor>.input>div").append(video);
        layer.close(index);
    });
}

function smilecontent(layero, index) {
    layer.style(index, {
        width: '365px',
        height: '315px'
    });
    $(".smile-content>div").click(function (e) {
        var edit = document.getElementById("reachEdit");
        edit.focus();
        insertHtmlAtCaret($(this).html());
    });
}


$(".bold").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    var r = $("#reachEdit>div").last().html();
    if (r.substr(r.length - 9) == "</span>**") {
        var cssattr = $("#reachEdit>div").last().children("span").last().css("font-weight")
        if (cssattr != "normal" && cssattr != '400') {
            $("#reachEdit>div").last().children("span").last().css("font-weight", "normal")
        } else {
            $("#reachEdit>div").last().children("span").last().css("font-weight", "bolder");
        }
    } else {
        var span = document.createElement("span");
        $(span).css("font-weight", "bolder");
        $(span).text(tip);
        $("#reachEdit>div").last().append("**");
        $("#reachEdit>div").last().append(span);
        $("#reachEdit>div").last().append("**");
    }
});

$(".italic").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    var r = $("#reachEdit>div").last().html();
    if (r.substr(r.length - 9) == "</span>**") {
        var cssattr = $("#reachEdit>div").last().children("span").last().css("font-style");
        if (cssattr == "italic") {
            $("#reachEdit>div").last().children("span").last().css("font-style", "normal");
        } else {
            $("#reachEdit>div").last().children("span").last().css("font-style", "italic");
        }
    } else {
        var span = document.createElement("span");
        $(span).css("font-style", "italic");
        $(span).text(tip);
        $("#reachEdit>div").last().append("**");
        $("#reachEdit>div").last().append(span);
        $("#reachEdit>div").last().append("**");
    }
});

$(".font").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '字体样式',
            content: $("#font").html(),
            success: fontcontent
        });
    })
});

$(".text-width").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    var content = $("#text-width").html();
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '字体大小',
            content: '<div class="text-width-content"><div class="showpannal"><div class="sample">T</div><div ><div id="slider"></div></div></div><div class="comfirm"><button type="button">确认</button></div></div>',
            success: fontwidth
        });
    })
});

$(".indent").click(function (e) {
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '段落缩进',
            content: $("#indent").html(),
            success: indentcontent
        });
    })
    // var r = document.getSelection().getRangeAt(0);
    /**/
});

$(".link").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '超链接',
            content: $("#link").html(),
            success: linkcontent
        });
    })
    /**/
});

$(".heading").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '标题',
            content: $("#heading").html(),
            success: headingcontent
        });
    })
});
$(".image").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '插入图片',
            content: $("#image").html(),
            success: imagecontent
        });
    })
});
$(".film").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '插入视屏',
            content: $("#video").html(),
            success: videocontent
        });
    })
});
$(".ol").click(function (e) {
    var r = $("#reachEdit>div").last().html();
    var ol = document.createElement("ol");
    var li = document.createElement("li");
    $(li).attr("contenteditable", "true");
    $(li).text(tip);
    $(ol).append(li);
    if (r.substr(r.length - 9) == "</span>**") {
        $("#reachEdit>div").last().children("span").last().append("**");
        $("#reachEdit>div").last().children("span").last().append(ol);
        $("#reachEdit>div").last().children("span").last().append("**");
    } else {
        $("#reachEdit>div").append("**");
        $("#reachEdit>div").append(ol);
        $("#reachEdit>div").append("**");
    }
});

$(".ul").click(function (e) {
    // var r = document.getSelection().getRangeAt(0);
    var r = $("#reachEdit>div").last().html();
    var ul = document.createElement("ul");
    var li = document.createElement("li");
    $(li).attr("contenteditable", "true");
    $(li).text(tip);
    $(ul).append(li);
    if (r.substr(r.length - 9) == "</span>**") {
        $("#reachEdit>div").last().children("span").last().append("**");
        $("#reachEdit>div").last().children("span").last().append(ul);
        $("#reachEdit>div").last().children("span").last().append("**");
    } else {
        $("#reachEdit>div").append("**");
        $("#reachEdit>div").append(ul);
        $("#reachEdit>div").append("**");
    }
});
$(".smile").click(function (e) {
    layui.use('layer', function () {
        layer.open({
            type: 1,
            title: '插入表情',
            content: $("#smile").html(),
            success: smilecontent
        });
    })
});
$(".upload").click(function () {
    // alert(navigator.appCodeName);
    layui.use('layer', function () {
        layer.confirm('确认上传?', {
            btn: ['上传', '再看看']
        }, function (index) {
            $.post('/project/essay/write',
                {content: $(".Editor>.show").html()}, function (data) {
                    if (data == "上传成功") {
                        if (navigator.appCodeName == "Mozilla") {
                            open(location, '_self').close();
                        }
                    } else {
                        layer.msg("上传失败");
                    }
                })
        }, function (index) {
            layer.close(index);
        })
    })
});