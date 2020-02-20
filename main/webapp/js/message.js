$.post("/project/account/test",
    function (data, status, xhr) {
        if (data != "offline") {
            var user = JSON.parse(data);
            var id = user.uid;
            var thisnameStr = user.username;

            var websoket = new WebSocket("ws://localhost:8080/helloWebSocket?id=" + id + "&name=" + thisnameStr);

            function usercklick() {
                $(".userafterlick").addClass("userbeforclick");
                $(".userafterlick").removeClass("userafterlick");
                $(this).removeClass("userbeforclick");
                $(this).addClass("userafterclick");
                $(".mseeagelistafterclick").addClass("mseeagelistbeforclick");
                $(".mseeagelistafterclick").removeClass("mseeagelistafterclick");
                $($(this).attr("uid")).addClass("mseeagelistafterclick");
                $($(this).attr("uid")).removeClass("mseeagelistbeforclick");
                $(this).children("span").remove();
            }

            websoket.onmessage = function (e) {
                var messagebody = JSON.parse(e.data);
                var formid = messagebody.form.toString();
                var toid = messagebody.to.toString();
                var nameStr = messagebody.name.toString();
                var messagestr = messagebody.message.toString();
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
                $(div).text(messagestr);
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
                if (formid == id) {
                    $(message).addClass("messageform");
                    $(embed).attr("src", "/svg/messagebubblefrom.svg");
                } else {
                    $(message).addClass("messageto");
                    $(embed).attr("src", "/svg/messagebubbleto.svg");
                }
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
                    $(usermessage).addClass("message");
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
            });
            $(".user").click(usercklick);
        }
    }
)