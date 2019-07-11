<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>订单管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a id="load-project-id"><i class="fa fa-circle-o"></i>用户订单信息</a></li>

          </ul>
        </li>

          <li class="treeview">
              <a href="#">
                  <i class="fa fa-dashboard"></i> <span>项目管理</span>
                  <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
              </a>
              <ul class="treeview-menu">
                  <li><a id="load-team-id"><i class="fa fa-circle-o"></i>项目信息</a></li>
              </ul>
          </li>

          <li class="treeview">
              <a href="#">
                  <i class="fa fa-files-o"></i>
                  <span>系统管理</span>
                  <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
              </a>
              <ul class="treeview-menu">
                  <li><a id="load-user-id"><i class="fa fa-circle-o"></i>用户管理</a></li>
              </ul>
          </li>


      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
<script type="text/javascript">
$('#load-project-id').click(function(){
	var url="/userlistUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-team-id').click(function(){
	var url="/prodectListUI?t="+Math.random(1000);
	$(".content").load(url);
})

$('#load-user-id').click(function(){
    var url="/manageListUI?t="+Math.random(1000);
    $(".content").load(url);
})

</script>