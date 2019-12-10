<#assign basePath=request.contextPath />

<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.1</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${basePath}/Statics/css/font.css">
    <link rel="stylesheet" href="${basePath}/Statics/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/Statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/Statics/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/Statics/js/cookie.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form layui-form-pane" id="useaddform">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" value="${map.name}" placeholder="请输入"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="username" value="${map.username}" autocomplete="off"
                           placeholder="请输入标题"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请认真填写用户名，作为账号登入</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="text" autocomplete="off" value="xxxxxxx" placeholder="请输入密码" lay-verify="password"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="男" title="男" checked="">
                        <input type="radio" name="sex" value="女" title="女">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">年龄</label>
                    <div class="layui-input-inline">
                        <input type="text" name="age" autocomplete="off" class="layui-input" value="${map.age}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="address" id="address" placeholder="请输入地址" value="${map.address}"
                           width="200px" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" placeholder="电话号码" width="200px" value="${map.phone}"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-block">
                    <select name="roleId" lay-filter="aihao">
                        <option value="${map.role_id}">${map.remark!}</option>
                        <option value="1">管理员</option>
                        <option value="2">总经理</option>
                        <option value="3">部长</option>
                        <option value="4">员工</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">文本域</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" name="remark" class="layui-textarea" id="textareas"></textarea>
                </div>
            </div>

            <div class="layui-form-item">

                <button class="layui-btn" lay-filter="add" lay-submit="">
                    确定修改
                </button>
                <button class="layui-btn" lay-filter="back" lay-submit="">
                    取消编辑
                </button>

            </div>
            <input type="hidden" name="id" value="${map.id}">
        </form>
    </div>
    <script>
        layui.use(['form', 'layer','layedit'], function () {
            $ = layui.jquery;
            var form = layui.form
                , layedit = layui.layedit
                , layer = layui.layer;
            layedit.build('textareas', {
                height: 180 //设置编辑器高度
            }); //建立编辑器

            //监听提交
            form.on('submit(add)', function (data) {
                console.log(data);
                //发异步，把数据提交给php

                $.ajax({
                    type: "POST",
                    url: "${basePath}/system/useredit",
                    data: $('#useaddform').serialize(),
                    dataType: "json",
                    async: true,
                    success: function (rb) {
                        layer.alert(rb.msg, {icon: 6}, function () {
                            //关闭当前frame
                            x_admin_close();
                            // 可以对父窗口进行刷新
                            x_admin_father_reload();
                        });
                    }
                });

                return false;
            });

            form.on('submit(back)', function (data) {
                console.log(data);
                //发异步，把数据提交给php
                        layer.alert("取消编辑", {icon: 6}, function () {
                            //关闭当前frame
                            x_admin_close();
                            // 可以对父窗口进行刷新
                            x_admin_father_reload();

                });
                return false;
            });
        });
    </script>

</div>
</body>

</html>