<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
//初始化datepicker对象
$('.datepicker').datepicker({
    format: 'yyyy/mm/dd',
    autoclose: true//选中自动关闭
})
</script>
<form class="form-horizontal" id="editFormId">
	<div class="form-group">
		<div class="col-sm-3 control-label"><font color="red">*</font>管理员名称：</div>
		<div class="col-sm-9">
			<input type="text" name="projectName" id="manageName" placeholder="请输入管理员名称" class="form-control dynamicClear required">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-3 control-label"><font color="red">*</font>账号名称：</div>
		<div class="col-sm-9">
			<input type="text" name="projectName" id="adminName" placeholder="请输入登录账号" class="form-control dynamicClear required">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-3 control-label"><font color="red">*</font>账号密码：</div>
		<div class="col-sm-9">
			<input type="text" name="projectName" id="pwd" placeholder="请输入登录密码" class="form-control dynamicClear required">
		</div>
	</div>
    <div class="form-group">
        <div class="col-sm-3 control-label"><font color="red">*</font>手机号：</div>
        <div class="col-sm-9">
            <input type="text" name="projectName" id="telNo" placeholder="请输入手机号" class="form-control dynamicClear required">
        </div>
    </div>



</form>
<script type="text/javascript" src="${basePath}/js/manage_edit.js"></script>