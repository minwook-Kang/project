$(document).ready(function() {
	
	var form = $("#registerForm");
	
	/* 상품 등록 버튼 클릭 시 */
	$("#btn_submit").on("click", function(){
		var result = confirm("상품을 등록하시겠습니까?");
		
		if(result){
			// 유효성 검사
			var mainCategory = $("#mainCategory option:selected");
			var subCategory = $("#subCategory option:selected");
			var pro_name = $("#pro_name");
			var pro_company = $("#pro_company");
			var pro_price = $("#pro_price");
			var pro_discount = $("#pro_discount");
			var ckeditor = CKEDITOR.instances['pro_contents'];
			var file1 = $("#file1");
			var pro_amount = $("#pro_amount");
			var pro_buy = $("#pro_buy");
			var fileSize = file1.size;
			var fileFormat  = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
			var maxSize = 5 * 1024 * 1024; // 5MB
			
			if(mainCategory.val()==null || mainCategory.val()=="default"){
				alert("1차 카테고리를 선택해주세요.");
				mainCategory.focus();
				return;
				
			} else if(subCategory.val()==null || subCategory.val()=="default"){
				alert("2차 카테고리를 선택해주세요.");
				subCategory.focus();
				return;
				
			} else if(pro_name.val()== null || pro_name.val()==""){
				alert("상품명을 입력해주세요.");
				pro_name.focus();
				return;
				
			} else if(pro_company.val()== null || pro_company.val()==""){
				alert("제조사를 입력해주세요.");
				pro_company.focus();
				return;
				
			}else if(pro_price.val()== null || pro_price.val()==""){
				alert("상품 가격을 입력해주세요.");
				pro_price.focus();
				return;
				
			}else if(pro_discount.val()== null || pro_discount.val()==""){
				alert("할인율을 입력해주세요.");
				pro_discount.focus();
				return;
				
			}else if(ckeditor.getData()== null || ckeditor.getData()==""){
				alert("상품 내용을 입력해주세요.");
				ckeditor.focus();	
				return;
				
			}else if(file1.val()== null || file1.val()==""){
				alert("이미지 파일을 추가해주세요.");
				file1.focus();
				return;
				
			}else if(!file1.val().match(fileFormat)){
				alert("이미지 파일만 업로드 가능합니다.");
				file1.focus();
				return;
				
			}else if(fileSize > maxSize){
				alert("파일 사이즈는 5MB까지만 가능합니다.");
				file1.focus();
				return;
				
			}else if(pro_amount.val()== null || pro_amount.val()==""){
				alert("상품 수량을 입력해주세요.");
				pro_amount.focus();
				return;
				
			}else {
				form.submit();
			}
		} else {}
		
	});
	
});

