<#assign basePath=request.contextPath />


<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>后台登录-X-admin2.1</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <script type="text/javascript" src="${basePath}/Statics/js2/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/Statics/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/Statics/js/cookie.js"></script>
    <link rel="stylesheet" href="${basePath}/Statics/css/font.css">
    <link rel="stylesheet" href="${basePath}/Statics/css/xadmin.css">
    <script src="${basePath}/Statics/lib/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">系统管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" id="Hzbform" >
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" id="usernam" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input" id="password">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>


<!-- 底部结束 -->
<script>

        layui.use(['form','jquery','layer'], function(){
            var form = layui.form;
            var $=layui.jquery;
            var layer=layui.layer;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交

            form.on('submit(login)', function(data){
                $.ajax({
                    url: "${basePath}/login",
                    type: "POST",
                    data:  {"username": $("#usernam").val(), "password": $("#password").val()},// 要提交的表单
                    success: function (rb) {
                            if (rb.code==200){
                                location.href = "${basePath}/index";
                            } else {
                                layer.msg("登入失败");
                            }

                            }
                });
                return false;
            });
        });

</script>

</body>
</html>