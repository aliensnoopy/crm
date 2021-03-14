layui.use(['form', 'jquery', 'jquery_cookie'], function() {
  var form = layui.form,
      layer = layui.layer,
      $ = layui.jquery,
      $ = layui.jquery_cookie($);

  form.on('submit(saveBtn)', function(data) {
    $.ajax({
      url: ctx + "/user/updatepwd",
      type: "POST",
      data: {
        oldPwd: data.field.old_password,
        newPwd: data.field.new_password,
        repeatPwd: data.field.again_password
      },
      success: function(result) {
        if (result.code == 200) {
          layer.msg("Update password successfully, will redirect to login page",
              function() {
            $.removeCookie(
                'encryptedUserId', {domain: 'localhost', path: '/crm'});
            $.removeCookie('userName', {domain: 'localhost', path: '/crm'});
            $.removeCookie('trueName', {domain: 'localhost', path: '/crm'});
            window.parent.location.href = ctx + "/index";
          });
        } else {
          layer.msg(result.msg, {icon: 5});
        }

      },
      error: function(result) {
        layer.msg("Failed to update password", {icon: 5});
        console.log(result);
      }
    });
  });
});