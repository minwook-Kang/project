<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.bgGradient{
	background: linear-gradient(90deg, #2BC0E4,  #EAECC6) fixed;
}
#modify_dropdown {  
	display:none; /* 평상시에는 서브메뉴가 안보이게 하기 */ 
	height:auto; 
	padding:10px 0px; 
	margin:0px; 
	border:0px; 
	position:absolute; 
	width:130px; 
	z-index:200; 
}

#modify:hover #modify_dropdown{
	display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
	color : yellow;
}


#order_dropdown {  
	display:none; /* 평상시에는 서브메뉴가 안보이게 하기 */ 
	height:auto; 
	padding:10px 0px; 
	margin:0px; 
	border:0px; 
	position:absolute; 
	width:130px; 
	z-index:200; 
}

#order:hover #order_dropdown{
	display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
	color: red;
}

</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">MIN MALL</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <!-- User Account Menu -->
		  <%-- 로그인 안 한 상태 --%>
		  <c:if test="${sessionScope.user == null}">
          	  <li class="nav-item">	
            	<a class="nav-link" href="/member/login">
					<span class="hidden-xs">로그인</span>
				</a>
          	</li>
          	<li class="nav-item">
            	<a class="nav-link" href="/member/join">
            		<span class="hidden-xs">회원가입</span>
            	</a>
            </li>
          </c:if>
          <%-- 로그인 한 상태 --%>
		  <c:if test="${sessionScope.user != null}"> 
			<li class="nav-item">
			<p class="hidden-xs" style="color:white; margin-top:8px; margin-left:15px; margin-right:15px;">
				${sessionScope.user.mbs_name}님 
			</p>
			</li>
            <li class="dropdown user user-menu" id="modify">	
            	<a class="nav-link" href="#">	
					<span>회원정보 관리</span>
				</a>
				<ul class="dropdown-menu" id= "modify_dropdown">
					<li><a class="nav-link" href="/member/checkPw?url=modify" style="color:gray;">회원정보 수정</a></li>
					<li><a class="nav-link" href="/member/checkPw?url=changePw" style="color:gray;">비밀번호 변경</a></li>
					<li><a class="nav-link" href="/member/checkPw?url=delete" style="color:gray;">회원 탈퇴</a></li>
				</ul>
			</li>
			<li class="dropdown user user-menu" id="order">	
            	<a class="nav-link" href="#">	
					<span>주문정보 관리</span>
				</a>
				<ul class="dropdown-menu" id= "order_dropdown">
					<li><a class="nav-link" href="/cart/list" style="color:gray;">장바구니</a></li>
					<li><a class="nav-link" href="/order/list" style="color:gray;">주문조회</a></li>
				</ul>
			</li>		       
			<li class="nav-item">
            	<a class="nav-link" href="/member/logout">로그아웃</a>
            </li>
          	</c:if>
        </ul>
      </div>
    </div>
  </nav>