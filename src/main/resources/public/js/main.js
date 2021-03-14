layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function() {
      layer.confirm("Confirm Exit?", {
        icon: 3,
        title: "Hint",
        btn: ["Yes", "Nope"]
      }, function(index) {
        layer.close(index);
        $.removeCookie("encryptedUserId", {domain: "localhost", path: "/crm"});
        $.removeCookie("userName", {domain: "localhost", path: "/crm"});
        $.removeCookie("trueName", {domain: "localhost", path: "/crm"});
        window.location.href = ctx + "/index";
      });
    });
});