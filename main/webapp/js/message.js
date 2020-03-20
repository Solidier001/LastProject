


function usercklick() {
    $(".appendix").addClass("lower");
    $(".appendix").removeClass("heighter");
    $(".emojis").addClass("lower");
    $(".emojis").removeClass("heighter");
    $(".userafterclick").addClass("userbeforclick");
    $(".userafterclick").removeClass("userafterclick");
    $(this).removeClass("userbeforclick");
    $(this).addClass("userafterclick");
    $(".mseeagelistafterclick").addClass("mseeagelistbeforclick");
    $(".mseeagelistafterclick").removeClass("mseeagelistafterclick");
    $($(this).attr("uid")).addClass("mseeagelistafterclick");
    $($(this).attr("uid")).removeClass("mseeagelistbeforclick");
    $(this).children("span").remove();
    $("#user").text($(this).find(".name").text());
}
$.post("/project/account/test",
    function (data, status, xhr) {
        if (data != "offline") {
            var user = JSON.parse(data);
            var id = user.uid;
            var thisnameStr = user.username;
            var websoket = new WebSocket("ws://localhost:8080/helloWebSocket?id=" + id + "&name=" + thisnameStr);
            $(".show>.img").css("background-image","url('/img/portait/"+user.img+"')");

            $.post("/project/page/messageuserlist",
                {id: id}, function (data) {
                    var list = JSON.parse(data);
                    for (var i = 0; i < list.length; i++) {
                        if(i==0){
                            $(".emojis>div").click(
                                function () {
                                    $(".inputfield>input[type='text']").val($(".inputfield>input[type='text']").val()+$(this).text());
                                }
                            );
                            $(".fa-smile-o").click(function (e) {
                                $("appendix").addClass("lower");
                                $("appendix").removeClass("heighter");
                                if ($(".emojis").hasClass("lower")) {
                                    $(".emojis").removeClass("lower");
                                    $(".emojis").addClass("heighter");
                                    $(".mseeagelistbox").removeClass("mseeagelistboxheighter");
                                    $(".mseeagelistbox").addClass("mseeagelistboxlower");
                                }
                                else {
                                    $(".emojis").removeClass("heighter");
                                    $(".emojis").addClass("lower");
                                    $(".mseeagelistbox").removeClass("mseeagelistboxlower");
                                    $(".mseeagelistbox").addClass("mseeagelistboxheighter");
                                }
                            });
                        }
                        $.post("/project/account/usermessage",
                            {id: list[i]},
                            function (data) {
                                var dot = document.createElement("span");
                                $(dot).addClass("layui-badge-dot");
                                var uinfo = JSON.parse(data);
                                var mseeagelist = document.createElement("div");
                                var user = document.createElement("div");
                                var userlable = document.createElement("div");
                                var userimg = document.createElement("div");
                                var detail = document.createElement("div");
                                var name = document.createElement("div");
                                var usermessage = document.createElement("div");
                                $(mseeagelist).attr("id", uinfo.id);
                                $(mseeagelist).addClass("mseeagelist");
                                $(mseeagelist).addClass("mseeagelistbeforclick");
                                $(".mseeagelistbox").append(mseeagelist);
                                $(name).addClass("name");
                                $(name).text(uinfo.nickname);
                                $(usermessage).addClass("message");
                                $(usermessage).load("/project/page/newmessage",
                                    {
                                        id: uinfo.id
                                    });
                                $(detail).append(name);
                                $(detail).append(usermessage);
                                $(detail).addClass("detail");
                                $(userimg).addClass("img");
                                $(userimg).css("background-image","url('/img/portait/"+uinfo.imglocation+"')")
                                $(userlable).append(userimg);
                                $(userlable).append(detail);
                                $(userlable).addClass("userlable");
                                $(user).append(userlable);
                                $(user).append(dot);
                                $(user).addClass("user");
                                $(user).addClass("userbeforclick");
                                $(user).attr("uid", "#" + uinfo.id);
                                $(user).click(usercklick);
                                $(".userlist").append(user);
                                $.post("/project/page/getMessages", {
                                    otherid: uinfo.id,
                                    uid: id
                                }, function (data) {
                                    var messagearry = JSON.parse(data);
                                    for (var i = 0; i < messagearry.length; i++) {
                                        var message = document.createElement("div");
                                        var label = document.createElement("div");
                                        var img = document.createElement("div");
                                        var username = document.createElement("div");
                                        var div = document.createElement("div");
                                        var embed = document.createElement("embed");
                                        $(message).addClass("message");
                                        $(label).addClass("label");
                                        $(img).addClass("img");
                                        $(username).addClass("username");
                                        $(embed).attr("type", "image/svg+xml");
                                        if (messagearry[i].form == id) {
                                            $(img).css("background-image",$(".show .img").css("background-image"));
                                            $(message).addClass("messageform");
                                            $(embed).attr("src", "/svg/messagebubblefrom.svg");
                                        } else {
                                            $(img).css("background-image","url('/img/portait/"+uinfo.imglocation+"')")
                                            $(message).addClass("messageto");
                                            $(embed).attr("src", "/svg/messagebubbleto.svg");
                                        }
                                        $(username).text(messagearry[i].name);
                                        $(div).html(messagearry[i].message);
                                        $(label).append(img)
                                        $(message).append(label)
                                        $(message).append(username)
                                        $(message).append(embed)
                                        $(message).append(div)
                                        $(mseeagelist).append(message);
                                    }
                                    mseeagelist.scrollTop = mseeagelist.scrollHeight - $(mseeagelist).height();
                                })
                            })
                    }
                })


            websoket.onmessage = function (e) {
                var messagebody = JSON.parse(e.data);
                var formid = messagebody.form.toString();
                var toid = messagebody.to.toString();
                var nameStr = messagebody.name.toString();
                var messagestr = messagebody.message;
                var div = document.createElement("div");
                var embed = document.createElement("embed");
                var label = document.createElement("div");
                var img = document.createElement("div");
                var message = document.createElement("div");
                var username = document.createElement("div");
                if (formid == id) {
                    var key = toid;
                } else {
                    var key = formid;
                }
                $(div).html(messagestr);
                $(embed).attr("type", "image/svg+xml");
                $(label).addClass("label");
                $(img).addClass("img");
                $(message).addClass("message");
                $(label).append(img);
                $(username).addClass("username");
                $(username).text(nameStr);
                $(message).append(label);
                $(message).append(username);
                $(message).append(embed);
                $(message).append(div);
                var dot = document.createElement("span");
                $(dot).addClass("layui-badge-dot");
                $(".userbeforclick[uid='" + formid + "']").append(dot);
                var mseeagelist = document.getElementById(key);
                if (mseeagelist == null) {
                    mseeagelist = document.createElement("div");
                    var user = document.createElement("div");
                    var userlable = document.createElement("div");
                    var userimg = document.createElement("div");
                    var detail = document.createElement("div");
                    var name = document.createElement("div");
                    var usermessage = document.createElement("div");
                    $(mseeagelist).attr("id", key);
                    $(mseeagelist).addClass("mseeagelist");
                    $(mseeagelist).addClass("mseeagelistbeforclick");
                    $(mseeagelist).append(message);
                    $(".mseeagelistbox").append(mseeagelist);
                    $(name).addClass("name");
                    $(name).text(nameStr);
                    $(usermessage).addClass("message");
                    $(usermessage).html(messagestr);
                    $(detail).append(name);
                    $(detail).append(usermessage);
                    $(detail).addClass("detail");
                    $(userimg).addClass("img");
                    $(userlable).append(userimg);
                    $(userlable).append(detail);
                    $(userlable).addClass("userlable");
                    $(user).append(userlable);
                    $(user).append(dot);
                    $(user).addClass("user");
                    $(user).addClass("userbeforclick");
                    $(user).attr("uid", "#" + key);
                    $(user).click(usercklick);
                    $(".userlist").append(user);
                }
                else {
                    mseeagelist.append(message);
                    $(".userlist .user[uid='#" + key + "'] .detail>.name").text(nameStr);
                    $(".userlist .user[uid='#" + key + "'] .detail>.message").html(messagestr);
                }
                if (formid == id) {
                    $(img).css("background-image",$(".show .img").css("background-image"));
                    $(message).addClass("messageform");
                    $(embed).attr("src", "/svg/messagebubblefrom.svg");
                } else {
                    $(img).css("background-image",$(".user[uid='#"+formid+"']>.userlable>.img").css("background-image"));
                    $(message).addClass("messageto");
                    $(embed).attr("src", "/svg/messagebubbleto.svg");
                }

                mseeagelist.scrollTop = mseeagelist.scrollHeight - $(mseeagelist).height();
            };
            websoket.onclose = function (ev) {
            }

            function Message(to, text) {
                this.to = to;
                this.message = text;
                this.date = null;
                this.form = null;
                this.id = null;
                this.name = null;
            }

            $(".inputfield button").click(function () {
                var message = new Message($($(".userafterclick").attr("uid")).attr("id"), $(".inputfield input").val());
                var messagestr = JSON.stringify(message);
                websoket.send(messagestr);
                $(".inputfield input").val("");
            });
            $(".user").click(usercklick);
        }
    }
)


/*function usercklick() {
    $(".userafterlick").addClass("userbeforclick");
    $(".userafterlick").removeClass("userafterlick");
    $(this).removeClass("userbeforclick");
    $(this).addClass("userafterclick");
    $(".mseeagelistafterclick").addClass("mseeagelistbeforclick");
    $(".mseeagelistafterclick").removeClass("mseeagelistafterclick");
    $($(this).attr("uid")).addClass("mseeagelistafterclick");
    $($(this).attr("uid")).removeClass("mseeagelistbeforclick");
    $(this).children("span").remove();
    $("#user").text($(this).find(".name").text());
}
$(".userlist>.user").click(usercklick);*/
