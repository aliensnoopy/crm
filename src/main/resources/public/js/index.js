layui.use(['form', 'jquery', 'jquery_cookie'], function () {
  var form = layui.form,
      layer = layui.layer,
      $ = layui.jquery,
      $ = layui.jquery_cookie($);

  form.on('submit(login)', function (data) {
    $.ajax({
      url: ctx + "/user/login",
      type: "POST",
      data: {
        userName: data.field.username,
        userPwd: data.field.password
      },
      success: function(result) {
        if (result.code == 200) {
          let userModel = result.result;
          layer.msg("Login success!", function() {
            $.cookie("encryptedUserId", userModel.encryptedUserId);
            $.cookie("userName", userModel.userName);
            $.cookie("trueName", userModel.trueName);
            window.location.href = ctx + "/main";
          });
        } else {
          layer.msg(result.msg, {icon: 5});
        }
      },
      error: function(result) {
        console.log("Server failed to respond.");
        console.log(result);
      }
    });
    return false;
  });

});