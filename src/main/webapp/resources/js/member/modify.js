$(document).ready(function() {
	
	var form = $("#modifyForm");
	// 인증 확인 여부를 위한 변수
	var CheckEmail = "true";
	
	// 이메일 변경 시 이메일 인증 활성화 
	$("#mbs_email").on("change", function(){
		$("#btn_sendAuthCode").show();
		CheckEmail = "false";
	});
	
	// 이메일 인증 클릭
	$("#btn_sendAuthCode").on("click", function(){
		var receiveMail = $("#mbs_email").val();
		
		if($("#mbs_email").val()=="" || $("#mbs_email").val()== null){
			$("#authcode_status").html("이메일을 먼저 입력해주세요.");
			return;
		}
		
		$("#authcode_status").css("color", "black");
		$("#authcode_status").html('인증코드 메일을 전송중입니다.  잠시만 기다려주세요...');
		
		$.ajax({
			url: '/email/send',
			type: 'post',
			dataType: 'text',
			data: {receiveMail : receiveMail},
			success: function(data){
				$("#email_authcode").show();
				$("#authcode_status").css("color", "black");
				$("#authcode_status").html('메일이 전송되었습니다.  입력하신 이메일 주소에서 인증코드 확인 후 입력해주세요.');
			}
		});
	});
	
	// 인증코드 입력 후 확인 클릭 
	$("#btn_checkAuthCode").on("click", function(){
		var code = $("#mbs_authcode").val();
		
		$.ajax({
			url: '/member/checkAuthcode',
			type: 'post',
			dataType: 'text',
			data: {code : code},
			success: function(data){
				if(data == 'SUCCESS'){
					$("#email_authcode").hide();
					$("#authcode_status").css("color", "blue");
					$("#authcode_status").html('인증 성공.');
					$("#mem_email").attr("readonly",true);
					$("#btn_sendAuthCode").attr("disabled", true);
					CheckEmail = "true";
					return;
					
				} else {
					$("#email_authcode").hide();
					$("#authcode_status").css("color", "red");
					$("#authcode_status").html('인증 실패. 다시 시도해주세요.');
					return;
				}
			}
		});
	});
	
	
	// 회원수정 버튼 클릭 
	$("#btn_submit").on("click", function(){
		
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
		
		 // 유효성 검사 
		 if(mbs_pw.val()== null || mbs_pw.val()==""){
			alert("현재 비밀번호를 입력해주세요.");
			mbs_pw.focus();
			return;
			
		} else if(mbs_name.val()== null || mbs_name.val()==""){
			alert("이름을 입력해주세요.");
			mbs_name.focus();
			return;
		
		} else if(mbs_nick.val()== null || mbs_nick.val()==""){
			alert("닉네임을 입력해주세요.");
			mbs_nick.focus();
			return;
		
		} else if(mbs_email.val()== null || mbs_email.val()==""){
			alert("이메일을 입력해주세요.");
			mbs_email.focus();
			return;

		} else if(CheckEmail=="false" && 
					(mbs_authcode.val()== null || mbs_authcode.val()=="")){
			alert("이메일 인증 확인 후 인증 코드를 입력해주세요.");
			mbs_authcode.focus();
			return;

		} else if(CheckEmail=="false"){
			alert("이메일인증을 해주세요.");
			$("#btn_sendAuthCode").focus();
			return;
		
		} else if(mbs_phone.val()== null || mbs_phone.val()==""){
			alert("휴대폰 번호를 입력해주세요.");
			mbs_phone.focus();
			return;

		} else if(mbs_zipcode.val()== null || mbs_zipcode.val()==""){
			alert("우편번호를 입력해주세요.");
			$("#btn_postCode").focus();
			return;
			
		} else if(mbs_addr.val()== null || mbs_addr.val()==""){
			alert("주소를 입력해주세요.");
			$("#btn_postCode").focus();
			return;
			
		} else if(mbs_deaddr.val()== null || mbs_deaddr.val()==""){
			alert("상세 주소를 입력해주세요.");
			mbe_deaddr.focus();
			return;
		} 
		 
		// 현재 비밀번호 확인 검사
		var mbs_pw_val = mbs_pw.val();
		$.ajax({
			url: "/member/checkPwAjax",
			type: "post",
			datatype: "text",
			data: {mbs_pw : mbs_pw_val},
			success: function(data){
				if(data=='SUCCESS'){
					form.submit();
				} else{
					alert("비밀번호가 다릅니다.");
					mbs_pw.val("");
					mbs_pw.focus();
				}
			} 
		});
	});
	
	// 취소 버튼 클릭 
	$("#btn_cancle").on("click", function(){
		
		var result = confirm("회원 정보 수정을 취소하시겠습니까?");
		if(result){
			location.href="/"; 
		} else{}
	});
	
});

