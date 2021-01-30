<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<header class="main-header">
			<!-- Logo -->
			<a href="/admin/main" class="logo">
				 <span class="logo-mini"></span>
				 	<span class="logo-lg">
					<b>MIN MALL  </b>ADMIN LTE
					</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- User Account Menu -->
						<c:choose>
					<c:when test="${sessionScope.admin == null}"> <!-- 로그인 안 한 경우 -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button -->
							<p class="hidden-xs" style="color:white; margin-top:14px; margin-left:15px; margin-right:15px;">
								로그인 해주세요.
							</p>
						</li>
						<li class="dropdown user user-menu">
							<a href="/admin/main"> 
								<span class="hidden-xs">로그인</span>
							</a>
						</li>	
					</c:when>
					<c:when test="${sessionScope.admin != null}"> <!-- 로그인 한 경우 -->
						<li class="dropdown user user-menu"> 
							<p class="hidden-xs" style="color:white; margin-top:14px;">
								최근 접속 시간: 
								<fmt:formatDate value="${sessionScope.admin.admin_lastdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
						</li>
						<li class="dropdown user user-menu"> 
							<p class="hidden-xs" style="color:white; margin-top:14px; margin-left:15px; margin-right:15px;">
								${sessionScope.admin.admin_name}님
							</p>
						</li>
						<li class="dropdown user user-menu">
							<a href="/admin/logout" onclick="return confirm('로그아웃하시겠습니까?');"> 
								<span class="hidden-xs">로그아웃</span>
							</a>
						</li>		
					</c:when>
				</c:choose>
						<!-- Control Sidebar Toggle Button -->
					</ul>
				</div>
			</nav>
		</header>