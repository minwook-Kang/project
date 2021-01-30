$(document).ready(function(){
	
	var form = $("#changePwForm");
	
	var mbs_pw = $("#mbs_pw");
	var mbs_pw_change = $("#mbs_pw_change");
	var mbs_pw_check = $("#mbs_pw_check");

	
	//확인버튼 클릭
	$("#btn_submit").on("click", function(){
		// 유효성 검사
		if(mbs_pw.val()==null || mbs_pw.val()==""){
			alert("현재 비밀번호를 입력해주세요.");
			mbs_pw.focus();
			return;
			
		} else if(mbs_pw_change.val() ==null || mbs_pw_change.val() ==""){
			alert("변경할 비밀번호를 입력해주세요.");
			mbs_pw_change.focus();
			return;
			
		} else if(mbs_pw_check.val() ==null || mbs_pw_check.val() ==""){
			alert("변경할 비밀번호 확인 란을 입력해주세요.");
			mbs_pw_check.focus();
			return;
			
		} else if(mbs_pw_change.val() != mbs_pw_check.val()){
			alert("변경할 비밀번호와 비밀번호 확인 란의 비밀번호가 다릅니다.");
			
			mbs_pw_change.val("");
			mbs_pw_check.val("");
			mbs_pw_change.focus();
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
					alert("현재 비밀번호가 다릅니다.");
					mbs_pw.val("");
					mbs_pw.focus();
				}
			} 
		});	
	
	
	});
});