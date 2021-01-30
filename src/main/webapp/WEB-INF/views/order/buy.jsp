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

   <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  
  <script src="/js/order/buy.js"></script>
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
				<div class="box" style="border: none;">
					<form id="orderForm" method="post" action="/order/order">
						<div class="box-body" style="padding:30px 10px 100px 10px;">
							<%-- 주문내역 상단 버튼 --%>
							<div class="orderList" style="padding: 0px 40px;">
								<div style="width:100%;">
									<span style="display: inline-block; float: left; margin:20px 10px 5px 0px;">[주문내역]</span>
									<div class="btn-container" style="display: inline-block; float: right; margin:20px 10px 5px 5px;">
										<button id="btn_delete_check"  class="btn btn-danger" type="button">선택 상품 삭제</button>
									</div>
								</div>
								<%-- 주문내역 테이블 --%>
								<table class="table table-striped text-center" id="ordertbl">
									<thead id="thead">
										<tr>
											<th><input type="checkbox" id="checkAll" checked="checked"/></th>
											<th>IMAGE</th>
											<th>NAME</th>
											<th>PRICE</th>
											<th>DISCOUNT</th>
											<th>AMOUNT</th>
											<th>TOTAL</th>
										</tr>
										
										<%-- 상품이 존재하지 않는 경우 --%>
										<tr>
											<c:if test="${empty productList}">
												<span>구매할 상품이 존재하지 않습니다.</span>
											</c:if>
										</tr>
									<thead>
									
									<%-- 상품이 존재하는 경우,  리스트 출력 --%>
									<tbody>
									<c:forEach items="${productList}" var="productVO" varStatus="i">
										<tr id="productVO_${productVO.pro_num}" class="productRow">
											<td>
												<input type="checkbox" name="check" class="check" value="${productVO.pro_num}" checked="checked" >
												<input type="hidden" id="amount_${productVO.pro_num}" name="orderDetailList[${i.index}].ord_amount" value="${amountList[i.index]}" />
												<input type="hidden" name="orderDetailList[${i.index}].pro_num" value="${productVO.pro_num}" />
												<input type="hidden" name="orderDetailList[${i.index}].ord_price" value="${productVO.pro_price-productVO.pro_discount}" />
											</td>
											<td class="col-md-2">
												<a href="/product/read?pro_num=${productVO.pro_num}&cate_code=${cate_code}">
													<img src="/product/displayFile?fileName=${productVO.pro_img}" style="width:100px;">
												</a>
											</td>
											<td class="col-md-2">
												<a href="/product/read?pro_num=${productVO.pro_num}&cate_code=${cate_code}"
													style="color: black;"> ${productVO.pro_name} </a>
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${productVO.pro_price}" pattern="###,###,###" />원</p>
												<input type="hidden" name="price" value="${productVO.pro_price}" />
												<!-- 
												<input type="hidden" id="price_${productVO.pro_num}" value="${productVO.pro_price}"  />  -->
											<td class="col-md-1">
												<p><fmt:formatNumber value="${productVO.pro_discount}" pattern="###,###,###" />원</p>
												<input type="hidden" name="discount" value="${productVO.pro_discount}" /> 
											<td class="col-md-1">
												<p>${amountList[i.index]}</p>
												<input type="hidden" name="amount" value="${amountList[i.index]}" /> 
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${productVO.pro_price*amountList[i.index]-productVO.pro_discount*amountList[i.index]}"  pattern="###,###,###" />원</p>
												<input type="hidden" name="pay" value="${productVO.pro_price-productVO.pro_discount}" /> 
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
										<div class="form-group" style="width:100%; margin-top:5px;">
											<input type="hidden" class="form-control" id="mbs_id" name="mbs_id" value="${user.mbs_id}">
										</div>
										<div class="form-group">
											<label for="inputName">* 이름</label> <input type="text"
												class="form-control" id="ord_name" name="ord_name" value="${user.mbs_name}">
										</div>
										<div class="form-group">
											<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
												class="form-control" id="ord_phone" name="ord_phone" value="${user.mbs_phone}">
										</div>
										<div class="form-group">
											<label for="inputAddr">* 주소</label> <br />
											<input type="text" id="sample2_postcode" name="ord_zipcode" class="form-control" 
												value = "${user.mbs_zipcode}"
												style="width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
											<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" class="btn btn-default" value="우편번호 찾기"><br>
											<input type="text" id="sample2_address" name="ord_addr" class="form-control" 
												value = "${user.mbs_addr}"
												placeholder="주소" style=" margin:3px 0px;" readonly>
											<input type="text" id="sample2_detailAddress" name="ord_deaddr" class="form-control" 
												value = "${user.mbs_deaddr}"
												placeholder="상세주소" >
											<input type="hidden" id="sample2_extraAddress" class="form-control" 
												placeholder="참고항목">
										</div>
										<div class="form-group">
											<label for="inputMobile">* 적립 포인트</label> <input type="number"
												class="form-control" id="mbs_point" name="mbs_point" value="${productVO._price}" readonly>
										</div>
									</div>
								</div>
								
								<%-- 결제 방식 선택  및 주문 금액 확인 --%>
								<div class="orderConfirm" style="display:inline-block; width:20%; margin: 0px 5%;">
								<br>
									<%-- 결제 방식 --%>
									<div>
										<span>[결제 방식]</span><br>
										<div class="payment" style="margin-top:10px;">
											<input type="radio" name="payment" value="card" checked="checked">카드 결제
											<input type="radio" name="payment" value="tcash">실시간 계좌이체<br>
											<input type="radio" name="payment" value="phone">휴대폰 결제
											<input type="radio" name="payment" value="cash">무통장 입금
										</div>
									</div>
									<br><br><br>
									
									<%-- 주문 금액 --%>
									<div style="width: 400px;">
										<span>[결제 금액]</span>
										<table class="table text-center" style="margin-top:15px;" >
											<tr>
												<td class="col-md-1">총 상품금액</td>
												<td class="col-md-1" style="height:30px; text-align: center;"><p id="totalPrice">0</p></td>
											</tr>
											<tr>
												<td class="col-md-1">총 할인금액</td>
												<td class="col-md-1" style="height:30px; text-align: center;"><p id="totalDiscount">0</p></td>
											</tr>
											<tr>
												<td class="col-md-1">총 결제금액</td>
												<td class="col-md-1" style="height:30px; text-align: center;"><label><p id="totalPay">0</p></label>
												<input type="hidden" id="ord_totalprice" name="ord_totalprice" value="0"/>
												</td>
												
											</tr>
											<tr>
											<tr>
												<td class="col-md-1" colspan="2" >
													<button id="btn_submit" class="btn btn-flat" type="button" style="padding: 10px 40px; background-color: grey; color:white;">결제하기</button>
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
	  </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

 
</body>

</html>

