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
  
  
  <script type="text/javascript" src="/js/member/changePw.js"></script>
  <!-- Custom styles for this template -->
	<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
  
  
  <style>
.login-form {
    width: 340px;
    margin: 50px auto;
  	font-size: 15px;
}
.login-form form {
    margin-bottom: 15px;
    background: #f7f7f7;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    padding: 30px;
}
.login-form h2 {
    margin: 0 0 15px;
}
.form-control, .btn {
    min-height: 38px;
    border-radius: 2px;
}
.btn {        
    font-size: 15px;
    font-weight: bold;
}
</style>

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
		
		<!-- 비밀번호변경 -->
		<section class="content container-fluid">
				<div style="background-color: white; width:80%; padding: 5% 5%;">
					<form id="changePwForm" method="post" action="/member/changePw">
						<div class="form-group">
							<input type= "hidden" name="mbs_id" value="${sessionScope.user.mbs_id}" />
							<input type="password" class="form-control" id="mbs_pw" class="form-control"
								placeholder="현재 비밀번호를 입력해주세요" style="max-width: 630px;">
							<input type="password" class="form-control" id="mbs_pw_change" name="mbs_pw" class="form-control"
								placeholder="변경할 비밀번호를 입력해주세요" style="max-width: 630px; margin: 7px 0px;">
							<input type="password" class="form-control" id="mbs_pw_check" class="form-control"
								placeholder="변경할 비밀번호를 다시 입력해주세요" style="max-width: 630px;">
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

