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
 
 <script>
if("${msg}" == "CHECK_PW_FAIL"){
	alert("비밀번호가 다릅니다.");
}
</script>
<script>
$(document).ready(function(){
	$("#btn_submit").on("click", function(){
		if($("#mbs_pw").val()== null || $("#mbs_pw").val()==""){
			alert("비밀번호를 입력해주세요.");
		} else {
			$("#checkPwForm").submit();
		}
	});
});
</script> 
  
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
		<!-- main-carousel end -->
		
		
        <div class="row">
		
		<!-- 비밀번호체크 -->
		<section class="content container-fluid">
				<div style="background-color: white; width:70%; padding: 5% 5%;">
					<form id="checkPwForm" method="post" action="checkPw">
						<div class="form-group">
							<input type="hidden" name="url" value="${url}" />
							<input type="password" class="form-control" id="mbs_pw" name="mbs_pw" class="form-control"
								placeholder="비밀번호를 입력해주세요" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<input type="button" id="btn_submit" class="btn btn-primary" value="확인">
						</div>
					</form>
				</div>
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

