<#assign  basePath=request.contextPath>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${basePath}/Statics/css/font.css">
    <link rel="stylesheet" href="${basePath}/Statics/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/Statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${basePath}/Statics/js/xadmin.js"></script>
    <script type="text/javascript" src="${basePath}/Statics/Statics/js/cookie.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">

                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>


                <table class="layui-hide" id="userlist" lay-filter="userlist"></table>


            </div>
        </div>
    </div>
</div>
</body>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
       <#-- <button class="layui-btn" type="button" id="useraddopen"><i class="layui-icon"></i>添加</button>-->
        <button class="layui-btn" onclick="x_admin_show('添加用户','${basePath}/system/adduser',900,700)"><i class="layui-icon"></i>添加
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs  layui-btn-xs" lay-event="status">更改状态</a>
</script>


<script>

    layui.use(['table','jquery'], function(){
        var table = layui.table;
        var $=layui.jquery;

        table.render({
            elem: '#userlist'
            ,url:'${basePath}/system/userlists'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,cols: [[
                 {field:'id', title:'ID',align:'center',type: 'checkbox', width:70, fixed: 'left', unresize: true, sort: true}
                ,{field:'username', title:'用户名',align:'center', width:150, edit: 'text'}
                ,{field:'name', title:'姓名',align:'center', width:150, edit: 'text', sort: true}
                ,{field:'remark', title:'角色', align:'center',width:150}
                ,{field:'age', title:'年龄',align:'center', width:150}
                ,{field:'phone',title:'电话',align:'center',width:150}
                ,{field:'address', title:'家庭地址',align:'center', edit: 'text' }
                ,{fixed: 'right', title:'操作', align:'center',toolbar: '#barDemo', width:290}
            ]]
            ,page: true
        });



        //监听行工具事件
        table.on('tool(userlist)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        type:"post",
                        url:"${basePath}/system/deletuser",
                        data:{"id":data.id},
                        dataType: "json",//返回的数据类型
                        success:function (rb) {
                            layer.msg(rb.msg);  //根据返回的信息提示
                            location.replace(location.href);
                        }
                    });
                    layer.close(index);

                });
            } else if(obj.event === 'edit'){
                   var id=data.id;
                   console.log("----------"+id);
                    x_admin_show('更改用户信息','${basePath}/system/edituser?id='+id,900,700)
            }
        });

    });


    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){

            if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

            }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }



    function delAll (argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？'+ids.toString(),function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
</html>