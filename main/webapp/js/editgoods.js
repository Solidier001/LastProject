function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return unescape(r[2]); return null;
}
$.post("/project/page/onegood", {
    id: getUrlParam("id")
},
    function (data, textStatus, jqXHR) {
       var good=JSON.parse(data);
       $("input[name='id']").val(good.id);
       $("input[name='name']").val(good.name);
       $(".cntentbox>form textarea").text(good.detail);
       $("input[name='times']").val(good.times);
       $("input[name='price']").val(good.price);
    }
);