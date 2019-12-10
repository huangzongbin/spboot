<#assign basePath=request.contextPath>
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

<body class="">
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ刷新</i></a>
</div>
<div class="x-body">
<div class="demoTable">
       VIP用户：
    <div class="layui-inline">
        <input class="layui-input" name="insertName" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>


    <table class="layui-hide" id="tableids" lay-filter="adviserTable"></table>

</div>

<!--状态-->
<script type="text/html" id="statustp">
    {{# if(d.status==0){  }}<!--未完成-->
    <input type="checkbox" name="open" id="{{ d.id }}" lay-skin="switch" lay-filter="status" lay-text="失败|待审批">
    {{# }else{ }} <!--已完成-->
    <input type="checkbox" checked="" disabled name="status" lay-skin="switch" lay-filter="status" lay-text="失败|待审批">
    {{# } }}
</script>

<!--操作工具栏-->
<script type="text/html" id="barDemo">
    {{# if(d.status==0){  }}<!--未完成-->
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    {{# } }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<!--头工具栏-->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>

        <button class="layui-btn" onclick="x_admin_show('新增上报','${basePath}/system/adviserAdd',900,700)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
    </div>
</script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });
        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });
</script>
<script>
    layui.use(['layer','table', 'form', 'element','jquery'], function(){
        var table = layui.table;

        //方法级渲染
        table.render({
            elem: '#tableids'
            ,url: '${basePath}/system/adviserlist'
            ,toolbar: '#toolbarDemo'
            ,cols: [[
                {field:'id', width:80, sort: true,type:'checkbox', fixed: true}
                ,{field:'department',title:'所属',align:'center', width:160}
                ,{field:'university',title:'学校',align:'center', width:160, sort: true}
                ,{field:'profession',title:'专业',align:'center', width:160}
                ,{field:'name', title:'姓名',align:'center',width:130}
                ,{field:'phone',title:'电话',align:'center', width:160}
                ,{field:'qq_wc',title:'qq/微信',align:'center', width:180}
                ,{field:'status',title:'状态',align:'center', templet:'#statustp',width:120 }
                ,{field:'time',title:'时间',align:'center', width:180,sort: true}
                ,{field:'insertName',title:'VIP用户',align:'center', width:120}
                ,{field:'remark',title:'备注',align:'center', width:220}
                ,{fixed: 'right', title:'操作',width:220, align:'center', toolbar: '#barDemo'}
            ]]
            ,id: 'tableid'
            ,page: true
            ,height: 500
            ,method:'post'
        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');

                //执行重载
                table.reload('tableid', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                            insertName: demoReload.val()

                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
<script>
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form =layui.form;

        //监听单选状态
        form.on('switch(status)',function(obj){
            var id=obj.elem.id;
            if(obj.elem.checked){
                layer.confirm("确定审批成功？",function(index){//确定按钮
                    //ajax更新状态
                    $.ajax({
                        async: false,    //表示请求是否异步处理 false同步
                        type: "post",    //请求类型
                        url: "${basePath}/system/adviserStatus",//请求的 URL地址
                        data: {id:id},
                        dataType: "json",//返回的数据类型
                        success: function (data) {
                            layer.msg("修改成功");
                            location.replace(location.href);
                        },
                        error: function (data) {
                            alert("服务器错误");
                        }
                    });
                    layer.close(index);
                },function(index){//取消按钮
                    obj.elem.checked=false;
                    form.render();
                    layer.close(index);
                });
            }
            // console.log(obj.elem.checked);//true 开 false 关
        });

        //监听操作工具条
        table.on('tool(adviserTable)',function(obj){
            var data=obj.data; //获得当前行数据
            var layEvent=obj.event;//获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'edit'){ //编辑
                x_admin_show('编辑','${basePath}/system/adviserEdit?id='+data.id);
            }else if (layEvent === 'del') {
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        type:"post",
                        url:"${basePath}/system/adviserDelet",
                        data:{"id":data.id},
                        dataType: "json",//返回的数据类型
                        success:function (rb) {
                            layer.msg(rb.msg);  //根据返回的信息提示
                            location.replace(location.href);
                        }
                    });
                    layer.close(index);

                });
            }
        });
    });
</script>
</body>
</html>