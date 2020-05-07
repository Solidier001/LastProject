function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

$.post("/project/good/reviewlist",
    {id: getUrlParam("id")},
    function (data) {
        var reviews = JSON.parse(data);
        for (var i = 0; i < reviews.list.length; i++) {
            var reviewitem = document.createElement("div");
            var user = document.createElement("div");
            var review = document.createElement("div");
            $(reviewitem).addClass("reviewitem");
            $(user).addClass("user");
            $(review).addClass("review");
            $(reviewitem).append(user);
            $(reviewitem).append(review);
            $.ajax({
                url: reviews.root + reviews.list[i],
                type: 'post',
                dataType: 'xml',
                success: function (data) {
                    $(review).html($(data).find("text").text())
                }
            })
            $('body').append(reviewitem);
        }
    })
