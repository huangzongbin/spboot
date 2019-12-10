<#assign basePath=request.contextPath />

<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.1</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
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
        <form class="layui-form layui-form-pane" id="adviserAddform">
            <div class="layui-form-item">
                <label class="layui-form-label">所属</label>
                <div class="layui-input-block">
                    <input type="text" name="department" id="department"  lay-verify="required" placeholder="所属" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">学校</label>
                <div class="layui-input-block">
                    <input type="text" name="university" id="university" autocomplete="off" placeholder="请输入学校名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">专业</label>
                <div class="layui-input-block">
                    <input type="text" name="profession" id="profession" autocomplete="off" placeholder="专业班级"
                           class="layui-input">
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label">电话号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" id="phone" placeholder="电话号码" width="200px" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">qq/微信</label>
                <div class="layui-input-inline">
                    <input type="text" name="qq_wc" id="qq_wc" placeholder="qq/微信" width="200px" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" id="name" placeholder="姓名" width="200px" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上报时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="time"   width="200px" id="inputTime" autocomplete="off"  class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" name="remark" class="layui-textarea" ></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    增加
                </button>

            </div>
        </form>
    </div>
    <script>
        layui.use(['form', 'layer','laydate','layedit'], function () {
            $ = layui.jquery;
            var form = layui.form
                , layedit = layui.layedit
                ,laydate = layui.laydate
                , layer = layui.layer;

            laydate.render({
                elem: '#inputTime'
                ,type: 'date'
            });

            layedit.build('textareas', {
                height: 180 //设置编辑器高度
            }); //建立编辑器




            //监听提交
            form.on('submit(add)', function (data) {
                console.log(data);
                //发异步，把数据提交给p

                $.ajax({
                    type: "POST",
                    url: "${basePath}/system/adviserAdd",
                    data:$('#adviserAddform').serialize(),
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
        });
    </script>

</body>

</html>