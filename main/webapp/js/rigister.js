$().ready(function(){
    $("#form").validate({
        errorPlacement: function(error, element) {
			// Append error within linked label
			$( element ).parent().siblings("td[class='alert']").append( error );
		},
        messages: {
            name: "用户名不能为空",
            password:"密码不能为空",
            comfirmpassword:"密码不相同"
        }
    }
    )
});
// $.validator.setDefaults({
//     submitHandler: function() {
//       alert("提交事件!");
//     }
// });
// $().ready(function() {
//     $("#commentForm").validate();
// });

// function vail1(){
//     if($("form").find("input").val()==""||$.trim($("form").find("input").val()).length==0){ 
//         $("#forForm").html("*注册表格不能为空");
//         event.preventDefault();
//     }
// }
// $("input[type='submit']").click(vail1);