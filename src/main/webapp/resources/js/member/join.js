$(document).ready(function() {
	
	var form = $("#joinForm");
	//아이디 중복체크 기능 사용여부확인 check
	var CheckId = "false";
	//이메일 인증 확인 여부
	var CheckEmail = "false";

	//회원가입 버튼 클릭
	$("#btn_submit").on("click", function() {
	
		var mbs_id = $("#mbs_id");
		var mbs_pw = $("#mbs_pw");
		var mbs_pw_check = $("#mbs_pw_check");
		var mbs_name = $("#mbs_name");
		var mbs_nick = $("#mbs_nick");
		var mbs_email = $("#mbs_email");
		var mbs_authcode = $("#mbs_authcode");
		var mbs_phone = $("#mbs_phone");
		var mbs_zipcode = $("input[name='mbs_zipcode']");
		var mbs_addr = $("input[name='mbs_addr']");
		var mbs_deaddr = $("input[name='mbs_deaddr']");
		var mbs_accept_e =$("#mbs_accept_e");
		
		//유효성 검사
		if(mbs_id.val()== null || mbs_id.val()=="") {
			alert("아이디를 입력해주세요.");
			mbs_id.focus();
				
		} else if(CheckId =="false") {
			alert("아이디 중복 체크를 해주세요.");
			$("#btn_checkId").focus();
				
		} else if(mbs_pw.val()== null || mbs_pw.val()=="") {
			alert("비밀번호를 입력해주세요.");
			mbs_pw.focus();
			
		} else if(mbs_pw_check.val()== null || mbs_pw_check.val()=="") {
			alert("비밀번호 확인을 입력해주세요.");
			mbs_pw_check.focus();
			
		} else if(mbs_pw.val() != mbs_pw_check.val()) {
		 	alert("입력하신 비밀번호가 다릅니다. \n비밀번호를 다시 확인해주세요.");
		 	mbs_pw_check.focus();
		 	
		} else if(mbs_name.val()== null || mbs_name.val()=="") {
		 	alert("이름을 입력해주세요.");
		 	mbs_name.focus();
		 	
		} else if(mbs_nick.val()== null || mbs_nick.val()=="") {
			alert("닉네임을 입력해주세요.");
			mbs_nick.focus();
			
		} else if(mbs_email.val()== null || mbs_email.val()=="") {
			alert("이메일을 입력해주세요.");
			mbs_email.focus();
			
		} else if(mbs_authcode.val()== null || mbs_authcode.val()=="") {
			alert("이메일 인증 확인 후 인증코드를 입력해주세요.");
			mbs_authcode.focus();
			
		} else if(CheckEmail=="false") {
			alert("이메일 인증을 해주세요.");
			$("#btn_sendAuthCode").focus();
			
		} else if(mbs_phone.val()== null || mbs_phone.val()=="") {
			alert("휴대폰 번호를 입력해주세요.");
			mbs_phone.focus();
			
		} else if(mbs_zipcode.val()== null || mbs_zipcode.val()=="") {
			alert("우편번호를 입력해주세요.");
			$("#btn_postCode").focus();
			
		} else if(mbs_addr.val()== null || mbs_addr.val()=="") {
			alert("주소를 입력해주세요.");
			$("#btn_postCode").focus();
			
		} else if(mbs_deaddr.val()== null || mbs_deaddr.val()=="") {
			alert("상세 주소를 입력해주세요.");
			mbs_deaddr.focus();
			
		} else {
			form.submit();
		}
	});
	
	//회원가입 취소 버튼 클릭
	$("#btn_cancle").on("click", function() {
	
		var result = confirm("가입을 취소하시겠습니까?");
		if(result) {
			location.href="/";
		} else{}
	});		

	
	//아이디 중복체크 클릭
	$("#btn_checkId").on("click", function() {
	
		if($("#mbs_id").val()== null || $("#mbs_id").val()=="") {
			$("#id_availability").html("아이디를 입력해주세요.");
			return;
		}
		
		var mbs_id = $("#mbs_id").val();
		
		$.ajax({
			url: '/member/checkIdOverlap',
			type: 'post',
			dataType: 'text',
			data: {mbs_id : mbs_id},
			success: function(data){
				if(data== 'SUCCESS'){
					//사용 가능한 아이디표시
					$("#id_availability").css("color","blue");
					$("#id_availability").html("사용가능한 아이디입니다.");
					
					CheckId = "true";
				}else {
					// 사용 불가능 아이디 -존재하는 아이디
					$("#id_availability").css("color","red");
					$("#id_availability").html("이미 존재하는 아이디입니다. \n다른 아이디로 다시 시도해주세요.");
				}
			}
		});
	});
	
	//이메일 인증 클릭
	$("#btn_sendAuthCode").on("click", function() {
		var receiveMail = $("#mbs_email").val();
		
		if($("#mbs_email").val()== null || $("#mbs_email").val()==""){
			$("#authcode_status").html("이메일을 먼저 입력해주세요.");
			return;
		}
		$("#authcode_status").css("color","black");
		$("#authcode_status").html('인증코드를 메일에 전송중입니다. 잠시만 기다려주세요.');
		
		$.ajax({
			url: '/email/send',
			type: 'post',
			dataType: 'text',
			data: {receiveMail : receiveMail},
			success: function(data) {
				$("#email_authcode").show();
				$("#authcode_status").css("color","black");
				$("#authcode_status").html('메일 전송이 완료되었습니다. 입력하신 이메일 주소에서 인증코드 확인 후 입력해주세요.');
			}
		});
	});
	
	// 인증코드 입력 후 확인 클릭
	$("#btn_checkAuthCode").on("click", function() {
		var code = $("#mbs_authcode").val();
		
		$.ajax({
			url: '/member/checkAuthcode',
			type: 'post',
			dataType: 'text',
			data: {code : code},
			success : function(data) {
				if(data == 'SUCCESS') {
					$("#email_authcode").hide();
					$("#authcode_status").css("color", "blue");
					$("#authcode_status").html('인증 성공에 성공하였습니다.');
					$("#mbs_email").attr("readonly",true);
					$("#btn_sendAuthCode").attr("disabled", true);
					CheckEmail = "true";
					return;	
				
				} else {
					$("#email_authcode").hide();
					$("#mbs_authcode").empty();
					$("#authcode_status").css("color", "red");
					$("#authcode_status").html('인증 실패에 실패하였습니다. 다시 시도해주세요.');
					return;	
				}
			}
		});
	});
	
});		 