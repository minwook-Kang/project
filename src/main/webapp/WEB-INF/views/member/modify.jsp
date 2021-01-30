<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  
  
  <script type="text/javascript" src="/js/member/modify.js"></script>
  
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
		
		
        <div class="row">
		
		<!-- 비밀번호변경 -->
		<section class="content container-fluid">
				<div class="container" style="width: 70%; min-width: 900px; background-color: white; font-size: 14px;" >
					<form id="modifyForm" action="/member/modify" method="post">
						<div class="container" style="width: 800px; padding: 10% 5%;">
							<h4>회원정보수정</h4>
							* 수정하고자하는 정보를 수정해주세요.<br><br><br>
							<div class="form-group" style="width:100%;">
								<input type="text" class="form-control" id="mbs_id" name="mbs_id" value="${vo.mbs_id}"
									style="max-width:630px;" readonly>
							</div>
							<div class="form-group">
								<label for="inputPasswordCheck">* 비밀번호 확인</label> <input
									type="password" class="form-control" id="mbs_pw" name="mbs_pw" placeholder="현재 비밀번호를 입력해주세요."
									style="max-width: 630px;" >
							</div>
							<div class="form-group">
								<label for="inputName">* 이름</label> <input type="text"
									class="form-control" id="mbs_name" name="mbs_name" value="${vo.mbs_name}"
									 style="max-width: 630px;">
							</div>
							<div class="form-group">
								<label for="inputNickName">* 닉네임</label> <input type="text"
									class="form-control" id="mbs_nick" name="mbs_nick" value="${vo.mbs_nick}"
									style="max-width: 630px;">
							</div>
							<div class="form-group">
								<label for="InputEmail">* 이메일 주소</label><br /> <input type="email"
									class="form-control" id="mbs_email" name="mbs_email" value="${vo.mbs_email}"
									style="max-width: 526px; width:calc(100% - 115px); margin-right: 5px; display: inline-block;">
								<button id="btn_sendAuthCode" class="btn btn-default" type="button" style="display:none;">이메일 인증</button>
								<p id="authcode_status" style="color: red;"></p>
							</div>
							<div id="email_authcode" class="form-group" style="display: none;">
								<label for="inputAuthCode">* 이메일 인증코드</label><br /> 
								<input type="text"
									class="form-control" id="mbs_authcode" 
									style="max-width: 570px; width:calc(100% - 70px); margin-right: 5px; display: inline-block;" />
								<button id="btn_checkAuthCode" class="btn btn-default" type="button" >확인</button>
							</div>
							<div class="form-group">
								<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
									class="form-control" id="mbs_phone" name="mbs_phone" value="${vo.mbs_phone}"
									style="max-width: 630px;">
							</div>
							<div class="form-group">
								<label for="inputAddr">* 주소</label> <br />
								<input type="text" id="sample2_postcode" name="mbs_zipcode" class="form-control" 
									value = "${vo.mbs_zipcode}"
									style="max-width: 510px; width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
								<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" class="btn btn-default" value="우편번호 찾기"><br>
								<input type="text" id="sample2_address" name="mbs_addr" class="form-control" 
									value = "${vo.mbs_addr}"
									placeholder="주소" style="max-width: 630px; margin:3px 0px;" readonly>
								<input type="text" id="sample2_detailAddress" name="mbs_deaddr" class="form-control" 
									value = "${vo.mbs_deaddr}"
									placeholder="상세주소" style="max-width: 630px;">
								<input type="hidden" id="sample2_extraAddress" class="form-control" 
									placeholder="참고항목">
								
							</div>
							<div class="form-group">
								<label>* 수신 동의</label><br /> 
								이벤트 등 프로모션 메일 알림 수신에 동의합니다.
								<label><input type="radio" name="mbs_accept_e" value="Y" style="margin-left: 20px;" <c:out value="${vo.mbs_accept_e == 'Y'?'checked':''}"/>> 예</label>
	      						<label><input type="radio" name="mbs_accept_e" value="N" style="margin-left: 20px;" <c:out value="${vo.mbs_accept_e == 'N'?'checked':''}"/>> 아니오</label>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" id="btn_submit" class="btn btn-primary">
								확인 <i class="fa fa-check spaceLeft"></i>
							</button>
							<button type="button" id="btn_cancle" class="btn btn-light">
								취소 <i class="fa fa-times spaceLeft"></i>
							</button>
						</div>
						<br><br><br><br>
					</form>
				</div>
				
				<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
				<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
				<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
				</div>
				
				<%-- 우편번호API 동작코드 --%>
				<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
				<script type="text/javascript" src="/js/member/postCode.js"></script>
			</section>
			
		
		
		

        </div>
        <!-- /.row -->

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

