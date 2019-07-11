<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/js/userInfo_list.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
 <!-- 表单 -->
	<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li>产品管理</li>
				  <li>用户管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="queryFormId">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li><input type="text" id="searchNameId" class="form-control"placeholder="姓名"></li>

					<li><input type="text" id="searchPhone" class="form-control"placeholder="手机号"></li>

					<li class='O1'><button type="button" class="btn btn-primary btn-search" >查询</button></li>


					<%--<li class='O2'><button type="button" class="btn btn-primary btn-add">添加</button></li>--%>

					<li class='O3'><button type="button" class="btn btn-primary btn-valid">删除</button></li>
				</ul>
			  </div>
			</div>
			<!-- 列表显示内容 -->
			<div class="row col-md-12">
				<table class="table table-bordered">
					<thead>
						<tr>
						    <th>选择</th>
							<th>姓名</th>
							<th>性别</th>
							<th>身份证</th>
							<th>手机号</th>
							<th>参与营期</th>
							<th>操作</th>
						</tr>
					</thead>
					<!-- ajax异步获得,并将数据填充到tbody中 -->
					<tbody id="tbodyId">
					</tbody>
				</table>
          <%@include file="../page.jsp" %>
			</div>
		</form>
	</div>  