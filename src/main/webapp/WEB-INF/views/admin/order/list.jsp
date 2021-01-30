<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>
<%@include file="/WEB-INF/views/admin/include/plugins.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="canonical"
	href="https://getbootstrap.com/docs/3.4/examples/signin/">

<title>ADMIN SIGNIN</title>
<%-- 버튼 클릭 이벤트 메소드 --%>
<script>
	$(document).ready(function(){
		// 검색 버튼 클릭 시 
		$("#btn_search").on("click", function(){
			self.location = "list"
				+ '${pm.makeQuery(1)}'
				+ "&searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + $('#keyword').val();
		});
		
		//배송현황 변경 버튼 클릭 시
		$("button[name=btn_modify]").on(
			"click",
			function() {
				var ord_code = $(this).val();  
				var ord_delivery = $("select[name='delivery_"+ord_code+"']").val();
				
				$.ajax({
					url : "/admin/order/modify",
					type : "post",
					dataType : "text",
					data : {
						ord_code : ord_code,
						ord_delivery : ord_delivery
					},
					success : function(data) {
						alert("배송현황이 변경되었습니다.");
						location.href="/admin/order/list${pm.makeSearch(pm.cri.page)}";
					}
				});

			});
	});
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<%@include file="/WEB-INF/views/admin/include/main-header.jsp" %>
		<!-- Left side column. contains the logo and sidebar -->
		<!-- 관리자기능 메뉴 포함 -->
		<%@include file="/WEB-INF/views/admin/include/main-sidebar.jsp" %>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Admin Page <small>Order List</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="/admin/order/list"><i class="fa fa-dashboard"></i> 주문 관리</a></li>
					<li class="active">주문현황</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">

				<!--------------------------
		        | Your Page Content Here |
		        -------------------------->

				<div class="row">
					<!-- left column -->
					<%-- 검색 조건 설정 및 페이지 이동에도 해당 값 유지 --%>
					<div class="col-md-12">
						<div class="row text-center">
							<div style="display: inline-block; float: left; margin-left:15px;">
							<select name="searchType" style="width:180px; height:26px;">
								<option value="null"
									<c:out value="${cri.searchType == null?'selected':''}"/>>검색조건 선택</option>
								<option value="code"
									<c:out value="${cri.searchType eq 'code'?'selected':''}"/>>주문번호</option>
								<option value="id"
									<c:out value="${cri.searchType eq 'id'?'selected':''}"/>>아이디</option>
								<option value="delivery"
									<c:out value="${cri.searchType eq 'delivery'?'selected':''}"/>>배송현황</option>
								<option value="all"
									<c:out value="${cri.searchType eq 'all'?'selected':''}"/>>주문번호+아이디+배송현황</option>
							</select> 
								<input type="text" name='keyword' id="keyword" style="width:250px; padding-left:5px;" value='${cri.keyword}' />
								<button id="btn_search" class="btn btn-default">검색</button>
							</div>	
						</div>
						<br>

						<div class="box" style="border: none;">
							<div class="box-body">
								<table class="table table-striped text-center">
									<tr>
										<th>주문번호</th>
										<th>아이디</th>
										<th>이름</th>
										<th>총가격</th>
										<th>주문날짜</th>
										<th>배송현황</th>
										<th>현황변경</th>
									</tr>
									
									<%-- 주문 리스트 출력 --%>
									<c:forEach items="${orderList}" var="orderVO">
										<tr>
											<td class="col-md-2">${orderVO.ord_code}<br><a	href="/admin/order/read${pm.makeSearch(pm.cri.page)}&ord_code=${orderVO.ord_code}"
												style="color: black;"> [${orderVO.ord_code} 상세보기]</a></td>
											<td class="col-md-1">${orderVO.mbs_id}</td>
											<td class="col-md-1">${orderVO.ord_name}</td>
											<td class="col-md-1"><p><fmt:formatNumber value="${orderVO.ord_totalprice}" pattern="###,###,###" />원</p></td>
											<td class="col-md-2">${orderVO.ord_regdate}</td>
											<td class="col-md-2">${orderVO.ord_delivery}</td>
											<td>
											<!-- 배송 현황(보임/숨김)기능 -->
												<select class="form-control" name="delivery_${orderVO.ord_code}" style="width: 120px; display: inline-block;">
												  <option>배송준비중</option>
												  <option>배송중</option>
												  <option>배송완료</option>
												</select>
												<button type="button" name="btn_modify" class="btn btn-primary" value="${orderVO.ord_code}" >변경</button>
											</td>
											<td class="col-md-1"><input name="delivery_${orderVO.ord_code}" type="hidden" style="width:80px; height:34px; padding-left:5px;" value="${orderVO.ord_delivery}" /></td>
										</tr>

									</c:forEach>
								</table>
							</div>
							<!-- /.box-body -->


							<!-- 페이징 기능 -->
							<div class="box-footer">

								<div class="text-center">
									<ul class="pagination">
										<!-- 이전표시 여부  [이전] -->
										<c:if test="${pm.prev}">
											<li><a href="list${pm.makeSearch(pm.startPage-1)}">&laquo;</a>
											</li>
										</c:if>
										<!-- 페이지목록번호 :  1  2  3  4  5  -->
										<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
											var="idx">
											<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
												<a href="list${pm.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>
										<!-- 다음표시 여부  [다음]-->
										<c:if test="${pm.next && pm.endPage > 0}">
											<li><a href="list${pm.makeSearch(pm.endPage +1)}">&raquo;</a>
											</li>
										</c:if>

									</ul>
								</div>

							</div>
							<!-- /.box-footer-->
						</div>
					</div>
					<!--/.col (left) -->

				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@ include file="/WEB-INF/views/admin/include/main-footer.jsp" %>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active">
					<a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a>
				</li>
				<li>
					<a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;"> <i class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design
									<span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading">
								Report panel usage
								<input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

</body>
</html>