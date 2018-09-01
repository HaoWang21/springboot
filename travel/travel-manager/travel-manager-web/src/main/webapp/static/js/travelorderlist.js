//引入admin.js(webapp/static/js/admin.js)
//itemlist.js(webapp/static/js/itemlist.js)
layui.extend({
    admin: '{/}../../static/js/admin'
});
//按需加载admin.js

layui.use(['admin', 'jquery', 'table','layer','form','laytpl','util'], function () {
    //初始化变量
    var admin = layui.admin,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;
        layer = layui.layer;
        laytpl = layui.laytpl;

    //对表格进行渲染
    table.render({
        //什么是表格属性：page,elem,url,cols
        //什么是列属性：type,field,title
        //开启分页
        page: true,
        //渲染的容器是谁
        elem: '#travelOrderList',
        //异步提交请求给后台返回JSON
        url: '../../travelOrders',
        //要显示的表头是什么
        cols: [[
            {type: 'checkbox'},
            {field: 'orderId', title: '订单编号'},
            {field: 'payment', title: '实付金额'},
            {field: 'paymentType', title: '支付类型'},
            {field: 'status', title: '订单状态', templet: '#myTpl'},
            {field: 'createTime', title: '创建时间',templet:'<div>{{ layui.util.toDateString(d.createTime,\'yyyy-MM-dd HH:mm:ss\') }}</div>'
                },
            {field: 'updateTime', title: '更新时间',templet:'<div>{{ layui.util.toDateString(d.updateTime,\'yyyy-MM-dd HH:mm:ss\') }}</div>'
                },
            {field: 'paymentTime', title: '付款时间',templet:'<div>{{ layui.util.toDateString(d.paymentTime,\'yyyy-MM-dd HH:mm:ss\') }}</div>'
                },
            {field: 'consignTime', title: '生效时间',templet:'<div>{{ layui.util.toDateString(d.consignTime,\'yyyy-MM-dd HH:mm:ss\') }}</div>'
                },
            {field: 'endTime', title: '交易完成时间',templet:'<div>{{ layui.util.toDateString(d.endTime,\'yyyy-MM-dd HH:mm:ss\') }}</div>'
                },
            {field: 'closeTime', title: '交易关闭时间',templet:'<div>{{ layui.util.toDateString(d.closeTime,\'yyyy-MM-dd HH:mm:ss\') }}</div>'
                },
            {field: 'userId', title: '用户id'},
            {field: 'buyerMessage', title: '买家留言'},
            {field: 'buyerNick', title: '买家昵称'},
            {field: 'buyerRate', title: '是否评价'}
]]
    });

    /*var active={
        reload:function () {
            var title = $("#title").val();
            if($.trim(title).length>0){
                table.reload("articleList",{
                    page:{curr:1},
                    where:{title:title}
                });
            }
        }
    }*/

    /*form.on('switch(test)', function(obj){
        //当前元素
        console.log("成功");
    });*/

    //点击"批量删除"按钮触发的事件
    $(".demoTable .layui-btn-danger").on('click',function(){
        //先判断是否选中行
        //若有 将选中的id拼成数组,提交给后台
        //若没有 提示用户 至少要选中一行才能批量删除
        var data = table.checkStatus("travelOrderList").data;
        if(data.length > 0){
            var ids = [];
            for (var i = 0;i<data.length;i++){
                ids.push(data[i].orderId);
            }
            $.post(
                //url 这次异步请求提交给谁处理 string
                '../../travelorder/batch',
                //data 提交什么给后台处理 object
                {'ids[]':ids},
                //success 成功的回调函数 function
                function (data) {
                    //提示删除
                    layer.confirm('确定要删除选中的记录吗?', {icon: 3, title:'提示'}, function(data){
                        //确认删除
                        if(data>0){
                            //停留在原来页面刷新
                            $('.layui-laypage-btn').click();
                            layer.msg("恭喜,删除成功!",{icon:1});
                        }
                        layer.close(data);
                    });
                },
                //datatype 返回类型 string
                'json'
            );
        }else{
            layer.msg("请至少选中一行再批量删除!",{icon:2});
        }
    });


    /*//点击"模糊查询"按钮触发的事件
    $(".weadmin-body .layui-btn").on('click',function () {
        var type = $(this).data("type");//reload
        active[type]?active[type].call(this):'';
    });*/

   /* //点击"模糊查询"按钮触发的事件
    $(".weadmin-body .layui-btn").on('click', function () {
        //为了获取按钮的data-type属性
        var type = $(this).data("type");//reload
        //判断active变量中是否具有reload属性
        active[type] ? active[type].call(this) : '';
    });*/


});
