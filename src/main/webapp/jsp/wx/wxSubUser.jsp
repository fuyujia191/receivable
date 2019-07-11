<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<HTML>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title></title>
    <link rel="stylesheet" href="${basePath}/weui/css/weui.css"/>
    <link rel="stylesheet" href="${basePath}/weui/css/weuix.css">
    <script src="${basePath}/weui/js/zepto.min.js"></script>
    <script src="${basePath}/weui/js/zepto.weui.js"></script>

    <script>
        $(function () {
            //性别列表
            $("#sex").select({
                title: "性别",
                items: ['男', '女']
            });

            //夏令营项目列表
            $.ajax({
                //url: '/springboot/wxAffectiveProject',
                url: '/wxAffectiveProject',
                datatype: "json",
                success: function (result) {
                    datas = result.data;
                    if (datas) {
                        var datalist = new Array;
                        for (var i = 0; i < datas.length; i++) {
                            var info = {
                                "title": datas[i].projectName,
                                "value": datas[i].id,
                                "description": datas[i].projectPrice
                            };
                            datalist.push(info);
                        }
                        $("#project").select({
                            title: "夏令营列表",
                            items: datalist,
                            input: '请选择一个夏令营',
                            onChange: function (data) {//选中触发事件
                                for (var i in data.origins) {
                                    //     alert(data.origins[i].checked);//选中的选项值
                                    //     alert(data.origins[i].value);//选中的选项值
                                    //     alert(data.origins[i].title);//选中的选项值
                                    $("#price").val(data.origins[i].description);//选中的选项值
                                    $("#projectid").attr("data-action", data.origins[i].value);
                                }
                            }
                        });
                    }
                }
            });

            $(document).on("click", "#btn", function () {
                var user = $("#name").val().trim();
                var sex = $("#sex").val().trim();
                var idcard = $("#idcards").val().trim();
                var phone = $("#phone").val().trim();
                var code = $("#code").val().trim();
                var project = $("#projectid").attr("data-action").trim();
                //var idcardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
                var phoneReg = /^1(3|4|5|6|7|8|9)\d{9}$/;
                if (user == "") {
                    $.toptip("请输入用户名");
                    return false;
                }
                if (sex == "") {
                    $.toptip("请选择性别");
                    return false;
                }
                if (project == "") {
                    $.toptip("请选择夏令营项目");
                    return false;
                }
                if (idcard == "") {
                    $.toptip("请输入身份证号码");
                    return false;
                } else if (idcardReg()) {
                    $.toptip("身份证输入不合法");
                    return false;
                }
                if (phone == "") {
                    $.toptip("请输入手机号");
                    return false;
                }
                else if (!phoneReg.test(phone)) {
                    $.toptip("请输入正确的手机号码");
                    return false;
                }
                checkValideCode(code,phone);

                $.toptip("提交成功", 'success');
                console.log(project);


            });

            function  checkValideCode(code,phone) {
                $.ajax({
                    //url: '/springboot/checkVCode',
                    url: '/checkVCode',
                    datatype: "json",
                    data: { code: code ,telNo:phone},
                    success: function (result) {
                        if (result.state == 1) {

                        }
                        else {
                            $.toptip(result.message, 'error');
                        }
                    },
                    error: function (result) {
                        $.toptip('未知异常导致请求失败,请重试.', 'error');
                    }
                });
            }

            //验证身份证号码
            function idcardReg() {
                var value = $("#idcards").val().trim();
                isValid = true;
                var cityCode = {
                    11: "北京",
                    12: "天津",
                    13: "河北",
                    14: "山西",
                    15: "内蒙古",
                    21: "辽宁",
                    22: "吉林",
                    23: "黑龙江 ",
                    31: "上海",
                    32: "江苏",
                    33: "浙江",
                    34: "安徽",
                    35: "福建",
                    36: "江西",
                    37: "山东",
                    41: "河南",
                    42: "湖北 ",
                    43: "湖南",
                    44: "广东",
                    45: "广西",
                    46: "海南",
                    50: "重庆",
                    51: "四川",
                    52: "贵州",
                    53: "云南",
                    54: "西藏 ",
                    61: "陕西",
                    62: "甘肃",
                    63: "青海",
                    64: "宁夏",
                    65: "新疆",
                    71: "台湾",
                    81: "香港",
                    82: "澳门",
                    91: "国外 "
                };
                /* 15位校验规则： (dddddd yymmdd xx g)    g奇数为男，偶数为女
                 * 18位校验规则： (dddddd yyyymmdd xxx p) xxx奇数为男，偶数为女，p校验位
                    校验位公式：C17 = C[ MOD( ∑(Ci*Wi), 11) ]
                        i----表示号码字符从由至左包括校验码在内的位置序号
                        Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
                        Ci 1 0 X 9 8 7 6 5 4 3 2
                 */
                var rFormat = /^\d{6}(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$|^\d{6}\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}$/;    // 格式验证
                if (!rFormat.test(value) || !cityCode[value.substr(0, 2)]) {
                    isValid = false;
                }
                // 18位身份证需要验证最后一位校验位
                else if (value.length === 18) {
                    var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];    // 加权因子
                    var Ci = "10X98765432"; // 校验字符
                    // 加权求和
                    var sum = 0;
                    for (var i = 0; i < 17; i++) {
                        sum += value.charAt(i) * Wi[i];
                    }
                    // 计算校验值
                    var C17 = Ci.charAt(sum % 11);
                    // 与校验位比对
                    if (C17 !== value.charAt(17)) {
                        isValid = false;
                    }
                }
            }
        })


        function getCode(obj) {
            if (checkPhone()) {//验证手机号码
                var phone= $("#phone").val().trim();
                $.ajax({
                    //url: '/springboot/getVCode',
                    url: '/getVCode',
                    datatype: "json",
                    data: { Tel: phone },
                    success: function (result) {
                        if (result.state == 1) {

                        }
                        else {
                            $.toptip(result.message, 'error');
                        }
                    },
                    error: function (result) {
                        $.toptip('未知异常导致请求失败,请重试.', 'error');
                    }
                });
                settime(obj); //倒计时
            }
            else {
                $("input[name='phone']").focus();
                return;
            }
        }
        //验证手机号码
        function checkPhone() {
            var phone = $("#phone").val().trim();
            var pattern = /^1(3|4|5|6|7|8|9)\d{9}$/;
            if (phone.length == 0) {
                $.toptip("请输入手机号码", 'error');
                return false;
            }
            if (!pattern.test(phone)) {
                $.toptip("手机号格式错误", 'error');
                return false;
            }
            return true;
        }
        var countdown = 60;
        function settime(obj) {
            if (countdown == 0) {
                $(obj).removeAttr("disabled");
                $(obj).text("获取验证码");
                countdown = 60;
                return;
            } else {
                $(obj).attr("disabled", true);
                $(obj).text(countdown +'s'+'后重发');
                countdown--;
            }
            setTimeout(function () {
                settime(obj)
            }, 1000)
        }
    </script>
    <style>
        #slide1{height:8rem;}
    </style>
</head>

<body ontouchstart>
<input id="projectid" style="display: none" data-action="">
<div class="page-hd">
        <div class="slide" id="slide1">
            <ul>
                <li>
                    <a href="javascript:;">
                        <%--<img src='/springboot/images/banner.jpg' alt="">--%>
                        <img src='/images/banner.jpg' alt="">
                    </a>
                    <%--<div class="slide-desc">happy day</div>--%>
                </li>
            </ul>
            <%--<div class="dot">--%>
                <%--<span></span>--%>
            <%--</div>--%>
        </div>
</div>
<div class="weui-cells weui-cells_form">

    <div class="weui-cell">
        <div class="weui-cell__hd"><label for="name" class="weui-label">姓名 :</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="name" type="text" placeholder="请输入姓名" value="">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label for="sex" class="weui-label">性别 :</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="sex" type="text" value="男">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">身份证号 :</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" placeholder="请输入身份证号" type="text" id="idcards">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd">
            <label class="weui-label">手机号 :</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" placeholder="请输入手机号" type="tel" id="phone">
        </div>

    </div>

    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">验证码</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="code" placeholder="验证码" type="number">
        </div>
        <div class="weui-cell__ft">
            <button  class="weui-vcode-btn" onclick="getCode(this)">获取验证码</button>
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd"><label for="project" class="weui-label">营期 :</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="project" type="text" value="">
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd">
            <label class="weui-label">价格 :</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" readonly="true" type="text" id="price">
        </div>

    </div>
</div>

<div class="weui-cell"></div>

<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="btn">确定支付</a>
</div>

<br>

</body>
</html>



