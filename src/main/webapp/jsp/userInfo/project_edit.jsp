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
		<div class="col-sm-3 control-label"><font color="red">*</font>项目名称：</div>
		<div class="col-sm-9">
			<input type="text" name="projectName" id="projectName" placeholder="请输入项目名称" class="form-control dynamicClear required">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-3 control-label"><font color="red">*</font>价格：</div>
		<div class="col-sm-9">
			<input type="text" name="price" id="price" placeholder="请输入金额" class="form-control dynamicClear required">
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-3 control-label"><font color="red">*</font>开始时间：</div>
		<div class="col-sm-9">
			<input type="text" class="form-control datepicker required" readonly="true" placeholder="请输入开始时间" name="code" id="beginDateId">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-3 control-label"><font color="red">*</font>结束时间：</div>
		<div class="col-sm-9">
			<input type="text" class="form-control datepicker required" readonly="true" placeholder="请输入结束时间"  name="code" id="endDateId">
		</div>
	</div>

</form>
<script type="text/javascript" src="${basePath}/js/project_edit.js?v=3"></script>