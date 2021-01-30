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
				var ord_delivery = $("ord_delivery:selected").val();

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
					Admin Page <small>Order Read</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 주문 관리</a></li>
					<li class="active">주문현황</li>
				</ol>
			</section>

			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<div class="row">
					<!-- left column -->
					<div class="box" style="border: none;">
						<form id="orderForm" method="post" action="/order/buy">
							<div class="box-body" style="padding:30px 10px 100px 10px;">
								<%-- 주문내역 상단 버튼 --%>
								<div class="orderList" style="padding: 0px 40px;">
									<%-- 주문내역 테이블 --%>
									<table class="table  text-center" id="ordertbl">
										<thead id="thead">
											<tr style="background-color: aliceBlue;" >
												<td colspan="7" style="text-align:left;">
													<b>주문날짜: <fmt:formatDate value="${order.ord_regdate}" pattern="yyyy/MM/dd HH:mm:ss"/>
													(주문번호: ${order.ord_code} )</b>
												</td>
											<tr>
											<tr style="background-color: whitesmoke;">
												<td>IMAGE</td>
												<td>NAME</td>
												<td>PRICE</td>
												<td>AMOUNT</td>	
												<td>TOTAL</td>
											</tr>
											<%-- 상품이 존재하지 않는 경우 --%>
											<tr>
												<c:if test="${empty orderList}">
													<span>구매한 상품이 존재하지 않습니다.</span>
												</c:if>
											</tr>
										<thead>
										
										<%-- 상품이 존재하는 경우,  리스트 출력 --%>
										<tbody>
										<c:forEach items="${orderList}" var="product" varStatus="status">
										<c:set var="totalPrice" value="${totalPrice + orderList[status.index].pro_price * orderList[status.index].ord_amount}"></c:set>
											<tr id="row">
												<td class="col-md-2">									
													<img src="/product/displayFile?fileName=${product.pro_img}" style="width:100px;">
												</td>
												<td class="col-md-2">
													${product.pro_name}
												</td>
												<td class="col-md-1">
													<p><fmt:formatNumber value="${product.ord_price}" pattern="###,###,###" />원</p>
												</td>
												<td class="col-md-1">
													<p>${product.ord_amount}</p>
												</td>
												<td class="col-md-1">
													<p ><fmt:formatNumber value="${product.ord_price * product.ord_amount}"  pattern="###,###,###" />원</p>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
									<br><br><br>
								</div>
								<hr><br>
								
								<%-- 주문 정보 --%>
								<div class="orderInfo" style="min-width:1000px;" > 
									<div class="userInfo" style="display:inline-block; float:left; width:60%; padding: 0% 5%;">
										<div class="container" style="width:100%;">
											<span>[주문 정보]</span>
											<div class="form-group">
												<label for="inputName">* 이름</label> <input type="text"
													class="form-control" value="${order.ord_name}" readonly>
											</div>
											<div class="form-group">
												<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
													class="form-control" value="${order.ord_phone}" readonly>
											</div>
											<div class="form-group">
												<label for="inputAddr">* 주소</label> <br />
												<input type="text" id="sample2_postcode" name="ord_zipcode" class="form-control" 
													value = "${order.ord_zipcode}" 
													style="width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
												<input type="text" id="sample2_address" name="ord_addr" class="form-control" 
													value = "${order.ord_addr}" 
													placeholder="주소" style=" margin:3px 0px;" readonly>
												<input type="text" id="sample2_detailAddress" name="ord_deaddr" class="form-control" 
													value = "${order.ord_deaddr}"
													placeholder="상세주소" readonly >
												<input type="hidden" id="sample2_extraAddress" class="form-control" 
													placeholder="참고항목">
											</div>
										</div>
									</div>
									
									<%-- 주문 금액 확인 --%>
									<div class="orderConfirm" style="display:inline-block; width:20%; margin: 0px 5%;">
									<br>
										<%-- 주문 금액 --%>
										<div style="width: 400px;">
											<span>[결제 금액]</span>
											<table class="table text-center" style="margin-top:15px;" >
												<tr>
													<td class="col-md-1"><label>총결제 금액</label></td>
													<td class="col-md-1" style="height:30px; text-align: center;">
														<label><fmt:formatNumber value="${order.ord_totalprice}" pattern="###,###,###" />원</label>
													</td>
												</tr>
											</table>
									
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
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