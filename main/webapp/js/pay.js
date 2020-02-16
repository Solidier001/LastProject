var count = parseFloat($(".td").children("td:eq(4)").text());
var pricestr = $(".td").children("td:eq(1)").text();
var price = parseInt(pricestr);
var sum = price * count;
$(".price").text(sum);
$("#submitorder").click(function () {
    document.getElementById("myDialog").showModal();
    $(".thispagebox").removeClass("thispagebox");
    $(".stepbox").eq(1).addClass("thispagebox");
});
$("#myDialog button").click(function () {
    var input=document.createElement("input");
    $(input).attr("type", "hidden");
    $(input).attr("name", "paymethod");
    $(input).val($("select").val());
    $("form").append(input);
    $("form").submit();
    document.getElementById("myDialog").close();
});
$(".fa-times").click(function(){
    setTimeout(function(){
        document.getElementById("myDialog").close();
    },2);
    $(".thispagebox").removeClass("thispagebox");
    $(".stepbox").eq(0).addClass("thispagebox");
});

