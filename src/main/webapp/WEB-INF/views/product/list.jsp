<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>
  
   <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  <script src="/js/product/list.js"></script>
  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>


</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

	  <!-- 카테고리 메뉴 -->
      <div class="col-lgp-3">


       <%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-7">
     	<!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
				<%-- 상품 목록 표시 --%>
				<div class="content-header">
				            <h1>상품 리스트  </h1>
				            <ol class="breadcrumb">
				               <li>${cate_name}
				               <i class="fa fa-dashboard"></i> 
				               </li>
				            </ol>
				      	</div>
				        <div class="row">
						<%-- 상품이 존재하지 않는 경우 --%>
						<c:if test="${empty productList}">
							<span>등록된 상품이 존재하지 않습니다.</span>
						</c:if>
						
						<%-- 상품이 존재하는 경우 --%>
						<c:forEach items="${productList}" var="productVO" >
							<div class="col-lg-4 col-md-6 mb-4">
                			<div class="card h-100">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&pro_num=${productVO.pro_num}&cate_code=${cate_code}">
									<img width="180" height="230" src="/product/displayFile?fileName=${productVO.pro_img}" >
								</a>
							<div class="card-body">
                     		<h4 class="card-title">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&pro_num=${productVO.pro_num}&cate_code=${cate_code}" >${productVO.pro_name}</a>
							</h4>
								<p><span style="font-size:16px;color:red;font-weight:bold;text-decoration:line-through;">가격: <fmt:formatNumber value="${productVO.pro_price}" pattern="###,###,###" />원</span><br>
								 	<span style="font-size:16px;color:#008fd6;font-weight:bold;text-decoration:line-through;">할인 금액: <fmt:formatNumber value="${productVO.pro_discount}" pattern="###,###,###" />원</span><br>
								 	<span style="font-weight: bold;">할인가: <fmt:formatNumber value="${productVO.pro_price-productVO.pro_discount}" pattern="###,###,###" />원</span></p>
							<div class="btnContainer">
								<button class="btn btn-primary" id="btn_cart" type="button" 
									onclick="cart_click(${productVO.pro_num})">장바구니</button>
							</div>
							</div>
							</div>
							</div>
						</c:forEach>
				</div>				
				<%-- 페이지 표시 --%>
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination justify-content-center" style="display:center-block">	<c:if test="${pm.prev}">
								<li><a class="page-link" href="list${pm.makeQuery(pm.startPage-1)}&cate_code=${cate_code}">&laquo;</a>
								</li>
							</c:if>

							<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
								var="idx">
								<li class="page-item" <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
									<a class="page-link" href="list${pm.makeQuery(idx)}&cate_code=${cate_code}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pm.next && pm.endPage > 0}">
								<li><a class="page-link" href="list${pm.makeQuery(pm.endPage +1)}&cate_code=${cate_code}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
				</div>
  </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

 
</body>

</html>

