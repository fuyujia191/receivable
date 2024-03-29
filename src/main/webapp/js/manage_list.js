$(document).ready(function () {
    //在queryFormId对应对象的btn-search元素上注册click事件
    $("#queryFormId").on("click",
        ".btn-search", doQueryObjects);

    $("#queryFormId").on("click",
        ".btn-delete", dodeleteById);

    //在添加,修改按钮上注册点击事件
    $('#queryFormId')
        .on("click", ".btn-add,.btn-update", doShowEditDialog);
    //初始化页面数据
    doGetObjects();

});

/*点击添加按钮时执行一个动作
 *1)初始化index页面的模态框(bootstrap 框架提供)
 *2)在模态框内部显示project_edit.jsp
 * */
function doShowEditDialog() {
    var title;
    //1.定义模态框的标题
    if ($(this).hasClass("btn-add")) {
        title = "添加用户"
    }
    if ($(this).hasClass("btn-update")) {
        //模态框上绑定id值(在修改页面中要根据此值获取数据)
        //data(key,value)函数用于绑定数据
        //data(key)函数用于获取数据
        $("#modal-dialog")
            .data("id",
                $(this).parent().parent().data("id"));
        //定义修改时的标题
        title = "修改用户,id为"
            + $("#modal-dialog").data("id");
    }
    //2.启动模态框,并加载页面
    //在模态框对应位置异步加载url
    var url = "/manageEditUI";
    $("#modal-dialog .modal-body")
        .load(url, function () {//异步加载完成回调此函数
            //设置标题内容
            $(".modal-title").html(title);
            //显示模态框(index.jsp中)
            $("#modal-dialog").modal("show");
        });
}

/*执行删除操作
 * 1.获得数据(禁用或启动哪些项目信息)
 * a)id(选中的那个checkbox的值)
 * 2.发送异步请求,修改记录信息
 * a)url
 * b)params
 * c)post(url,params,functions(result){})
 */
function dodeleteById() {
    //debugger

    //1.获得选中的id值(可能1个也可能是多个)
    var checkedIds = getCheckedIds();
    console.log("checkedIds=" + checkedIds);
    if (checkedIds.length == 0) {
        alert("请至少选择一项");
        return;
    }
    //2.提交异步请求,更新对应记录的状态信息
    //2.1 定义url (对应控制器中的一个方法)
    var url = "/DeleteProject"
    //2.2 将数据封装为一个json对象
    var params = {"checkedIds": checkedIds};
    //2.3发起类型为post的ajax请求(类型为post)
    $.post(url, params, function (result) {//回调函数
        //debugger
        //alert("result="+JSON.stringify(result));
        if (result.state == 1) {//异步请求成功了
            alert(result.message);//ok
            doGetObjects();//重新查询
        } else {//请求过程出现异常
            alert(result.message);
        }
    });
}

function getCheckedIds() {//1,2,3,4,5
    var checkedIds = "";
    //1.遍历所有的checkbox,获得选中的值
    /*$('tbody input[name="checkedItem"]').
      each(function(){
         if($(this).is(":checked")){}//课后了解
      })
    */
    $('#tbodyId input[name="checkedItem"]').each(function () {//each函数用于迭代对象
        //判定当前对象是否是选中的
        if ($(this).prop("checked")) {
            if (checkedIds == "") {
                checkedIds += $(this).val();//id
            } else {
                checkedIds += "," + $(this).val();
            }
        }
    });
    //2.返回获得的数据
    return checkedIds;
}


/*执行表单查询*/
function doQueryObjects() {
    //1.修改当前页的值为1
    $("#pageId").data("pageCurrent", 1);
    //2.执行查询动作(重用doGetObjects方法)
    doGetObjects();
}

/*获得查询表单中的数据*/
function getQueryFormData() {
    //根据id获得具体对象的值,然后封装到JSON对象
    var params = {
        telNo: $("#telNo").val()
    };
    return params;
}

function doGetObjects() {
    //定义一个url(对应服务端控制器中的一个方法)
    var url = "/getManageList"
    //获取当前页的页码值,假如没有值,默认值设置为1
    var pageCurrent = $("#pageId").data("pageCurrent");
    if (!pageCurrent) {
        pageCurrent = 1;
    }
    //定义一个params对象
    var params = getQueryFormData();
    //动态的向params对象中添加key/value
    params.pageCurrent = pageCurrent;
    console.log(JSON.stringify(params))
    //底层发起ajax异步请求($.ajax({....}))
    //post方法是一个增强版的ajax方法
    $.post(url, params, function (result) {//result为一个json对象(JsonResult)
        //console.log("result="+result);
        //将json对象转换为json字符串输出
        //console.log(JSON.stringify(result));
        //将json对象中的数据,填充到table的tbody元素中
        if (result.state == 1) {//成功
            //alert(result.message);//假如有需要
            //显示记录信息
            setTableBodyRows(result.data.list);//data属性的值对应一个json对象
            //设置及显示分页信息
            //console.log(JSON.stringify(result.data.pageObject))
            setPagination(result.data.pageObject);
        } else {//失败了(获取数据时出现异常了)
            alert(result.message);
        }
    });

}

//定义函数将json对象中的数据取出来填充到表格中
function setTableBodyRows(result) {//result 为一个json对象
    //获得tbody对象(根据id获得)
    //在使用$函数获得某个id对应的对象一定要添加#
    var tBody = $("#tbodyId");
    //清空body中内容(假如方法不记得了怎么办)
    tBody.empty();//doc.tedu.cn
    //迭代json对象
    for (var i in result) {//循环一次取一行[{"name":"AAA",...},{},{}]
        //构建一个tr对象
        var tr = $("<tr></tr>");
        //在tr对象上绑定一个值
        tr.data("id", result[i].id);//key/value
        //tr对象中追加td字符串对象
        var firstTd = '<td><input type="checkbox" name="checkedItem" value="[id]"></td>';
        //将firstTd字符串中的[id]替换为一个具体指
        firstTd = firstTd.replace("[id]", result[i].id);
        tr.append(firstTd);
        tr.append("<td>" + result[i].manageName + "</td>");
        tr.append("<td>" + result[i].adminName + "</td>");
        tr.append("<td>" + result[i].telNo + "</td>");
        tr.append("<td>" + new Date(result[i].addDate).toLocaleDateString() + "</td>");
        tr.append("<td><button type='button' class='btn btn-default btn-update'>修改</button></td>");
        tBody.append(tr);//将每一行记录再追加到tbody中
    }

}

