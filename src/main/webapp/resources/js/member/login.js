$(document).ready(function() {

	var form = $("#loginForm");
	
	//로그인 버튼 클릭
	$("#btn_login").on("click", function(){
	
		var mbs_id = $("#mbs_id");
		var mbs_pw = $("#mbs_pw");
		
		if(mbs_id.val()==null || mbs_id.val()==""){
			alert("아이디를 입력해주세요.");
			mbs_id.focus();
		} else if(mbs_pw.val()==null || mbs_pw.val()==""){
			alert("비밀번호를 입력해주세요.");
			mbs_pw.focus();
		} else{
			form.submit();
		}
	});
});