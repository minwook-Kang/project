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

   <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  
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
      <div class="col-lg-3">


       <%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-7">
        <!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
		<%-- MAIN CONTENT --%> 
		<section class="content container-fluid">

				<div class="row">
					<!-- left column -->
					<div class="box" style="border: none; padding: 10px 30px;">
						<div class="box-body">
							<table class="table text-center">
								<%-- 상품이 존재하지 않는 경우 --%>
								<c:if test="${empty orderList}">
									<tr>
										<td colspan="10"> 
											<p style="padding:50px 0px; text-align: center;">주문하신 상품이 없습니다.</p>
										</td>
									<tr>
								</c:if>
								
								<%-- 상품이 존재하는 경우,  리스트 출력 --%>
								<c:forEach items="${orderList}" var="orderVO" varStatus="status">
									<c:if test="${status.index==0 || orderVO.ord_code != code}">
									<tr style="background-color: aliceBlue;" >
										<td colspan="6" style="text-align:left;">
											<b>주문날짜: <fmt:formatDate value="${orderVO.ord_regdate}" pattern="yyyy/MM/dd HH:mm:ss"/>
											(주문번호: ${orderVO.ord_code} ) </b>
											<button class="btn btn-primary" onclick="location.href='/order/read?ord_code=${orderVO.ord_code}';">상세보기</button> 
											<p>배송현황 : <a style="color:red;">${orderVO.ord_delivery}</a></p>
										</td>
									<tr>
									<tr style="background-color: whitesmoke;">
										<td>IMAGE</td>
										<td>NAME</td>
										<td>PRICE</td>
										<td>AMOUNT</td>
										<td>TOTAL</td>
										<td>REVIEW</td>
									</tr>
									</c:if>
									<c:set var="code" value="${orderVO.ord_code}">	</c:set>
									<tr>
										<td>
											<a href="/product/read?pro_num=${orderVO.pro_num}">
												<img src="/product/displayFile?fileName=${orderVO.pro_img}" style="width:100px;">
											</a>
										</td>
										<td>
											<a href="/product/read?pro_num=${orderVO.pro_num}"
												style="color: black;"> ${orderVO.pro_name} </a>
										</td>
										<td>
											<p><fmt:formatNumber value="${orderVO.ord_price}" pattern="###,###,###" />원</p>
											
										<td>
											<p>${orderVO.ord_amount}</p>
										</td>
										<td>
											<p><fmt:formatNumber value="${orderVO.ord_price * orderVO.ord_amount}" pattern="###,###,###" />원</p>
										</td>
										<td>
											<button type="button" class="btn btn-info" 
												onclick="location.href='/product/read?pro_num=${orderVO.pro_num}';" value="${orderVO.pro_num}" >review</button>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<!--/.col (left) -->
			</section>
	  </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

 
</body>

</html>

