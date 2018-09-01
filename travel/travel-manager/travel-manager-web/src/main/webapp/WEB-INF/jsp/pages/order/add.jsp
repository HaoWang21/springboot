<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>添加订单-后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="weadmin-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="orderId" class="layui-form-label">
                <span class="we-red">*</span>订单编号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="orderId" name="orderId" required="" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="payment" class="layui-form-label">
                <span class="we-red">*</span>实付金额
            </label>
            <div class="layui-input-inline">
                <input type="text" id="payment" name="payment" required="" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="status" class="layui-form-label">
                <span class="we-red">*</span>订单状态
            </label>
            <div class="layui-input-inline">
                <select id="status" name="status" class="valid">
                    <option value="1">未付款</option>
                    <option value="2">已付款</option>
                    <option value="3">未消费</option>
                    <option value="4">已消费</option>
                    <option value="5">交易成功</option>
                    <option value="6">交易关闭</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="paymentType" class="layui-form-label">
                <span class="we-red">*</span>支付类型
            </label>
            <div class="layui-input-inline">
                <select id="paymentType" name="paymentType">
                    <option value="1">支付宝</option>
                    <option value="2">微信</option>
                    <option value="3">货到付款</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="userId" class="layui-form-label">
                <span class="we-red">*</span>用户id
            </label>
            <div class="layui-input-inline">
                <input type="text" id="userId" name="userId" required="" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="buyerMessage" class="layui-form-label"><span class="we-red">*</span>买家留言</label>
            <div class="layui-input-inline">
                <textarea placeholder="请输入内容" id="buyerMessage" name="buyerMessage" class="layui-textarea"></textarea>
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="buyerNick" class="layui-form-label"><span class="we-red">*</span>买家昵称</label>
            <div class="layui-input-inline">
                <input type="text" id="buyerNick" name="buyerNick" required="" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="buyerRate" class="layui-form-label"><span class="we-red">*</span>是否评价</label>
            <div class="layui-input-inline">
                <select id="buyerRate" name="buyerRate">
                    <option value="1">已评价</option>
                    <option value="2">未评价</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="add" class="layui-form-label">
            </label>
            <button id="add" class="layui-btn" lay-filter="add" lay-submit="">增加</button>
        </div>
    </form>
</div>
<script>
    layui.extend({
        admin: '{/}../../static/js/admin'
    });
    layui.use(['form', 'admin', 'jquery', 'table', 'layer'], function() {
        var form = layui.form,
            admin = layui.admin,
            $ = layui.jquery,
            table = layui.table,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            buyerNick: function(value) {
                if(value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            }
        });

            //监听提交
            form.on('submit(add)', function (data) {
                layui.use('jquery',function(){
                    var $=layui.$;
                    $.ajax({
                        type: 'post',
                        url: '../../travelorder/add', // ajax请求路径
                        data: {
                            orderId:data.field.orderId,
                            payment:data.field.payment,
                            status:data.field.status,
                            paymentType:data.field.paymentType,
                            userId:data.field.userId,
                            buyerMessage:data.field.buyerMessage,
                            buyerNick:data.field.buyerNick,
                            buyerRate:data.field.buyerRate
                        },
                        success: function(data){
                            if(data>0){
                                layer.alert("添加成功", {
                                    icon: 6
                                }, function () {
                                    // 获得frame索引
                                    var index = parent.layer.getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                });
                                // layer.msg('添加成功');
                            }else if(data<=0){
                                $('.layui-laypage-btn').click();
                                layer.msg("对不起,添加失败!",{icon:1});
                                // layer.msg('添加失败');
                            }
                        }
                    });
                });
                return false;//禁止跳转，否则会提交两次，且页面会刷新
            });

    });
</script>
</body>

</html>