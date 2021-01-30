<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
  <script src="/js/product/read.js"></script>
  
  <%-- 템플릿: 상품목록 --%>
  <script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rew_num={{rew_num}}>
        	<i class="fa fa-comments bg-blue"></i>
            <div class="timeline-item" >
                <span class="time">
                	<i class="fa fa-clock-o"></i>{{prettifyDate rew_regdate}}
                </span>
                <h3 class="timeline-header">
					<strong>{{checkRating rew_score}} <p class='rew_score' style="display:inline-block;">{{rew_score}}</p></strong> 
					</h3>
                <div class="timeline-body">
					NUM: {{rew_num}} <p style="float:right;">작성자: {{mbs_id}}</p> <br>
					<p id='rew_contents'>{{rew_contents}}</p> </div>
				<div class="timeline-footer" style="float:right;">
					{{eqReplyer mbs_id rew_num}}
				</div>
	         </div>			
         </li>
	{{/each}}
</script>

<%-- 버튼 클릭 이벤트 메소드/ 핸들바 사용자 정의 헬퍼 --%>
<script>
	$(document).ready(function(){

		/* 상품 목록 버튼 클릭 시 */
		$("#btn_list").on("click", function(){
			location.href="/product/list${pm.makeQuery(pm.cri.page)}&cate_code=${vo.cate_code}";
		});

		/* 
		 * 사용자 정의 헬퍼(prettifyDate)
		 * : 매개변수로 받은 timeValue를 원하는 날짜 형태로 바꿔준다.
		 */ 
		Handlebars.registerHelper("prettifyDate", function(timeValue) {
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			return year + "/" + month + "/" + date;
		});

		/* 
		 * 사용자 정의 헬퍼(checkRating)
		 * : 매개변수로 받은 리뷰 평점을 별표로 출력
		 */ 
		Handlebars.registerHelper("checkRating", function(rating) {
			var stars = "";
			switch(rating){
				case 1:
					 stars="★☆☆☆☆";
					 break;
				case 2:
					 stars="★★☆☆☆";
					 break;
				case 3:
					 stars="★★★☆☆";
					 break;
				case 4:
					 stars="★★★★☆";
					 break;
				case 5:
					 stars="★★★★★";
					 break;
				default:
					stars="☆☆☆☆☆";
			}
			return stars;
		});

		/* 
		 * 사용자 정의 헬퍼(eqReplyer)
		 * : 로그인 한 아이디와 리뷰의 아이디 확인 후, 수정/삭제 버튼 활성화 
		 */ 
		Handlebars.registerHelper("eqReplyer", function(replyer, rew_num) {
			var btnHtml = '';
			var mbs_id = "${sessionScope.user.mbs_id}";
			if (replyer == "${user.mbs_id}") {
				btnHtml = "<a class='btn btn-primary btn-xs' data-toggle='modal' data-target='#modifyModal'>"
					  + "리뷰 수정</a>"
					  + "<button class='btn btn-danger btn-xs' style='margin-left:5px;'" 
					  + "onclick='deleteReview("+rew_num+");'"
					  + "type='button' >리뷰 삭제</button>"; 
			}
			return new Handlebars.SafeString(btnHtml);
			

		});
				
	});
</script>

<%-- 스타일 --%>
<style>
     #star_grade a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade a.on{
        color: black;
    }
    
    #star_grade_modal a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade_modal a.on{
        color: black;
    }
    
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
</style>
  
  
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
					<section class="content container-fluid">

				<!-- 상품등록 폼 -->
				<div class="row">
					<!-- left column -->
					<div class="col-md-12">
						<!-- general form elements -->
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">상품 상세</h3>
							</div>
							<!-- /.box-header -->
							
							<%-- 상품 상세 정보 출력 --%>
							<div class="box-body">
								<div class="form-group container" style="margin:30px 0px; height:350px;" >
									<div style="float:left; width:30%; height:100%;">
										<img src="/product/displayFile?fileName=${vo.pro_img}" style="display: inline; width:90%;">
									</div>
									<div style="display: inline-block; margin-left:20px;">
										<label for="exampleInputEmail1">상품 이름</label><br>
										<span>${vo.pro_name}</span><br><br>
										
										<label for="exampleInputEmail1">제조사</label><br>
										<span>${vo.pro_company}</span><br><br>
										
										<div>
											<label for="exampleInputEmail1" style="width:100px; margin-right:10px;">상품 가격</label> 
											<label for="exampleInputEmail1" style="width:100px; margin-right:10px;">할인 금액</label> 
											<label for="exampleInputEmail1" style="width:100px;">할인가</label> <br>
											<span style="width:100px; margin-right:50px; display:inline-block font-size:16px;color:red;font-weight:bold;text-decoration:line-through;">
												<fmt:formatNumber value="${vo.pro_price}" pattern="###,###,###" />원
											</span>
											<span style="width:100px; display:inline-block; font-size:16px;color:#008fd6;font-weight:bold;text-decoration:line-through;">
												<fmt:formatNumber value="${vo.pro_discount}" pattern="###,###,###" />원
											</span>
											<span style="width:100px; display:inline-block; font-weight: bold;">
												<fmt:formatNumber value="${vo.pro_price-vo.pro_discount}" pattern="###,###,###" />원
											</span>
										</div>
										<br>
										
										<div>
											<form method="get" action="/order/buy" >
												<label for="exampleInputEmail1">상품 수량</label><br>
												<input type="number" id="ord_amount" name="ord_amount" min="1" value="1" /><br><br>
												<input type="hidden" id="pro_num" name="pro_num" value="${vo.pro_num}" />
												<button type="submit" id="btn_buy" class="btn btn-light">구매하기</button>
												<!-- 장바구니 기능으로 진행 -->
												<button type="button" id="btn_cart" class="btn btn-primary">장바구니</button>
											</form>
										</div>
										
									</div>
								</div>
								<!-- 상품 상세 -->
								<label for="detail">상품 내용</label><br>
								<div contenteditable="false" style="border: 1px solid grey; padding: 20px;">
									${vo.pro_contents}
								</div>
								<br>
								
								<%-- 상품 리뷰 --%>
								<div class='popup back' style="display:none;"></div>
							    <div id="popup_front" class='popup front' style="display:none;">
							     	<img id="popup_img">
							    </div>
						    	<form role="form" action="modifyPage" method="post">
									<input type='hidden' name='bno' value="${boardVO.bno}">
									<input type='hidden' name='page' value="${cri.page}"> 
									<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
									<%-- 
									<input type='hidden' name='searchType' value="${cri.searchType}">
									<input type='hidden' name='keyword' value="${cri.keyword}">
									 --%>
								</form>
								
								<div>
									<!-- 상품리뷰쓰기 부분 -->
									 <div>	
										<label for="review">상품 리뷰</label><br>
										<div class="rating">
											<p id="star_grade">
										        <a href="#">★</a>
										        <a href="#">★</a>
										        <a href="#">★</a>
										        <a href="#">★</a>
										        <a href="#">★</a>
											</p>
										</div>
										<textarea id="reviewContents" rows="3" style="width:100%;"></textarea><br>
									
										<!-- 상품 리뷰 리스트 -->
									 	<ul class="timeline">
				 							 <!-- timeline time label -->
											<li class="time-label" id="repliesDiv">
												<span class="btn btn-default">
											    	상품리뷰 보기 <small id='replycntSmall'> [ ${totalReview} ] </small>
											    </span>
											    <button class="btn btn-primary" id="btn_write_review" type="button">상품리뷰쓰기</button>
											</li>
											<li class="noReview" style="display:none;">
												<i class="fa fa-comments bg-blue"></i>
												<div class="timeline-item" >
													 <h3 class="timeline-header">
														상품리뷰가 존재하지 않습니다.<br>
													 </h3>
												</div>
											</li>
											 
										</ul>
										<!-- 상품 리뷰 리스트 페이지부분 -->  
										<div class='text-center'>
											<ul id="pagination" class="pagination pagination-sm no-margin "></ul>
									 	</div>
									 </div>
									 
									 
									 <%-- Modal : 상품리뷰 수정/삭제 팝업 --%>
									<div id="modifyModal" class="modal modal-primary fade" role="dialog">
									  <div class="modal-dialog">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header" >
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <div class="modal-title">
												<p id="star_grade_modal">
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
												</p>
									        </div>
									      </div>
									      <div class="modal-body" data-rew_num>
									        <p><input type="text" id="replytext" class="form-control"></p>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-info" id="btn_modal_modify">수정하기</button>
									        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
									      </div>
									    </div>
									  </div>
									</div>      
							</div>
							
							
							<!-- /.box-body -->

							<div class="box-footer">
								<div>
									<hr>
								</div>

								<ul class="mailbox-attachments clearfix uploadedList">
								</ul>

								<button id="btn_list" type="button" class="btn btn-primary" >리스트로</button>
							</div>
						
						</div>
						<!-- /.box -->	
					</div>
					<!--/.col (left) -->

				</div>
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

