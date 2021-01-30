// 장바구니 버튼 클릭 이벤트 
	var cart_click = function(pro_num){
		$.ajax({
			url: "/cart/add",
			type: "post",
			dataType: "text",
			data: {pro_num: pro_num},
			success: function(data){
				if(data == 'SUCCESS') {
					var result = confirm("장바구니에 추가되었습니다.\n지금 확인하시겠습니까?");
					if(result){
						location.href="/cart/list";
					} else{}
				}else if(data == 'FAIL') {
					alert("로그인을 해주세요.");
					location.href="/member/login";
				}else{}
			}
		});
	}
