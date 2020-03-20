function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return decodeURIComponent(r[2]); return null;
}
var id=getUrlParam("u_id");
var thisnameStr=getUrlParam("u_name");
var to=getUrlParam("id");
function Message(to, text) {
    this.to = to;
    this.message = text;
    this.date = null;
    this.form = null;
    this.id = null;
    this.name = null;
}
var websoket = new WebSocket("ws://localhost:8080/helloWebSocket?id="+id +"&name="+thisnameStr.toString());
function f(){
    // alert('aaa')
    var message=new Message(to,$(".inputfield>input").val());
    var messagestr =JSON.stringify(message);
    websoket.send(messagestr);
}

websoket.onmessage= function (e){
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
    if (formid == id) {
        $(message).addClass("messageform");
        $(embed).attr("src", "/svg/messagebubblefrom.svg");
    } else {
        $(message).addClass("messageto");
        $(embed).attr("src", "/svg/messagebubbleto.svg");
    }
    $("#message").append(message);
    var mseeagelist=document.getElementById("message");
    mseeagelist.scrollTop = mseeagelist.scrollHeight - $("#message").height();
}
onunload=function () {
    alert("close")
    // websoket.close();
}
