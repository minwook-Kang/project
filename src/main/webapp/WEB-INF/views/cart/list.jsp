<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


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
  
  <!-- 버튼 클릭시 이벤트 메소드 -->
  <script src="/js/cart/list.js"></script>
  
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
		<!-- Main content -->
			<section class="content container-fluid">
			    <div class="row">
		   			<div class="box" style="border: none;">
						<form method="post" action="/order/buyFromCart">
						<div class="btn-container" style="display: inline-block; float: right; margin:20px 10px 5px 5px;">
							<button id="btn_buy_check"  class="btn btn-primary" type="submit" >선택 상품 구매</button>
							<button id="btn_delete_check"  class="btn btn-danger" >선택 상품 삭제</button>
						</div>
						<div class="box-body">
                 			<table class="table table-striped text-center">
								<tr>
									<th><input type="checkbox" id="checkAll" checked="checked"/></th>
									<th>No</th>
									<th>IMAGE</th>
									<th>NAME</th>
									<th>PRICE</th>
									<th>DISCOUNT</th>
									<th>AMOUNT</th>
									<th>ORDER/DELETE</th>
								</tr>
								
								<%-- 상품이 존재하지 않는 경우 --%>
								<c:if test="${empty cartProductList}">
									<tr>
										<td colspan="6"> 
											<p style="padding:50px 0px; text-align: center;">장바구니에 담긴 상품이 없습니다.</p>
										</td>
									<tr>
								</c:if>
								
								<%-- 상품이 존재하는 경우,  리스트 출력 --%>
								<%--JSTL 변수 선언 --%>
								<c:set var="i" value="${fn:length(cartProductList)}" />
								<c:forEach items="${cartProductList}" var="cartProductVO">
									<tr>
										<td class="col-md-1">
											<input type="checkbox" name="check" class="check" value="${cartProductVO.cart_code}" checked="checked" >
											<input type="hidden" id="pro_num_${cartProductVO.cart_code}" name="pro_num" value="${cartProductVO.pro_num}" >
											<input type="hidden" name="cart_amount" value="${cartProductVO.cart_amount}" >
											<input type="hidden" name="cart_code" value="${cartProductVO.cart_code}" >
										</td>
										<td class="col-md-1">${i}</td>
										<td class="col-md-2">
											<a href="/product/read?pro_num=${cartProductVO.pro_num}&cate_code=${cate_code}">
												<img src="/product/displayFile?fileName=${cartProductVO.pro_img}" style="width:100px;">
											</a>
										</td>
										<td class="col-md-2">
											<a href="/product/read?pro_num=${cartProductVO.pro_num}&cate_code=${cate_code}"
												style="color: black;"> ${cartProductVO.pro_name} </a>
										</td>
										<td class="col-md-1">
											<p><fmt:formatNumber value="${cartProductVO.pro_price}" pattern="###,###,###" />원</p>
											<input type="hidden" name="price_${cartProductVO.cart_code}" value="${cartProductVO.pro_price}" /></td>
										<td class="col-md-1">
											<p>${cartProductVO.pro_discount}</p>
											<input type="hidden" name="discount_${cartProductVO.cart_code}" value="${cartProductVO.pro_discount}" /></td>
										<td class="col-md-2">
											<input type="number" name="cart_amount_${cartProductVO.cart_code}"
												style="width:60px; height:34px; padding-left:5px;" min="1" value="${cartProductVO.cart_amount}" />
											<button type="button" name="btn_modify" class="btn btn-primary" value="${cartProductVO.cart_code}" >변경</button>
										</td>
										<td class="col-md-2">
											<button type="button" name="btn_buy" class="btn btn-primary" value="${cartProductVO.cart_code}"
												onclick="clickBuyBtn(${cartProductVO.pro_num}, ${cartProductVO.cart_code});">구매</button>
											<button type="button" name="btn_delete" class="btn btn-danger" value="${cartProductVO.cart_code}" >삭제</button>
										</td>
										<td class="col-md-1">	
											<input type="hidden" name="pay_${cartProductVO.cart_code}" value="${cartProductVO.pro_price - cartProductVO.pro_discount}" /></td>
										<c:set var="i" value="${i-1}" ></c:set>
									</tr>

								</c:forEach>
							</table>
						</div>
						</form>
							<table class="table table-striped text-center" >
								<tr>
									<td>총 상품금액</td>
									<td>총 할인금액</td>
									<td>총 결제금액</td>
								</tr>
								<tr >
									<td style="height:50px; text-align: center;"><p id="totalPrice">0</p></td>
									<td style="height:50px; text-align: center;"><p id="totalDiscount">0</p></td>
									<td style="height:50px; text-align: center;"><p id="totalPay">0</p></td>
								</tr>
							</table>
						</div>
					</div>
				<!--/.col (left) -->
			</section>
  		</div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

 
</body>

</html>

