// 페이지가 첫번째로 실행 되었을 때도 autocomplet되게 만들기
$(function() {
	myAjaxAutoComplete()
})
// select 태그의 값이 변경되었을 때, 이벤트 처리
// $("#sel1").on("change", myAjaxAutoComplete) --> 이렇게 나타낼수도 있음
$("#sel1").on("change", function() {
	myAjaxAutoComplete()

})




///// 비동기 통신을 사용하여 작성자 리스트를 전부 조회/////////////////////////
function myAjaxAutoComplete() {
	$.ajax({
		url: `${cpath}/autocomplete`,
		// ?type=작성자 형태로 가게 됨
		data: { type: $("#sel1").val() },
		dataType: "json",
		success: function(res) {
			console.log("받아온 데이터 >> ", res);
			// 객체 배열 --> 문자열 배열
			// 1. 비어있는 배열 생성
			var sourceList = [];
			// 2. res의 길이만큼 반복하면서 안쪽에 있는 writer 데이터만 꺼내서
			//	  비어있는 배열에 추가
			// 반복문 $.each 형태도 있음(?)
			for (var i = 0; i < res.length; i++) {

				if (res[i].writer != null) {
					sourceList.push(res[i].writer);
				} else {
					sourceList.push(res[i].title);
				}
			}

			myAuto(sourceList);


		},
		error: function(e) {
			console.log(e);
		}


	})
}
/////////////////////////////////////////////////////////////////////


function myAuto(sourceList) {
	// jquery-ui를 사용해서 input 태그 아래쪽에 글자 자동완성 기능 넣어보기
	$("#auto_complete").autocomplete({
		// 1. 자동완성하고 싶은 목록
		source: sourceList,
		// 2. 최소글자
		minlength: 1,
		// 3. 딜레이 시간 0.1초
		delay: 100,
		// 4. 해당 요소에 focusing 되었을 때 작동할 함수
		focus: function(e, ui) {
			// e -> event
			// ui -> 화면 구성
			// focus가 발생됐을때 화면구성을 바꿀 수 있음
			return false;
		}

	})
}




// 1. searchBtn 클릭했을 때 이벤트 등록
$("#searchBtn").on("click", function() {
	// 2. id값이 searchForm 안에 있는 데이터 가져오기
	// 병렬 데이터로 만들어져 있는 데이터를 하나로 묶는작업
	var data = $("#searchForm").serialize();
	// 3. 비동기 통신을 사용하여 데이터 전송 (/search)
	$.ajax({
		// js파일로 만들면 url이 문자가 아닌 변수로 읽어야 하므로 ""가 아닌 ``로 읽기
		url: `${cpath}/search`,
		data: data,
		dataType: "json",
		success: function(result) {
			console.log(result);
			// 화면구성을 바꾸는 코드 작성

			// 1. column이름을 제외한 나머지 행들을 비워주기
			// tr태그의 첫번째 제외한 나머지, 빈칸은 자손을 뜻함
			$("#myTable tr:first-child~tr").empty();

			// 2. result에 담긴 데이터 개수만큼 반복
			// for문으로 해도됨
			$.each(result, function(i, board) {
				// 3. tr태그를 추가
				var tr = `
							<tr>
								// .jsp -> .java -> .class -> .html(js)
								// .java -> .class에 읽히는데 \${board.idx}처럼 \를 써주면 이과정이 무시되고 .html(js)과정에서 읽힘
								// js 파일로 만들면 \ 안써도 됨
								<td>${board.idx}</td>
								<td><a href='${cpath}/boardContent/${board.idx}'>\${board.title}</a></td>
								<td>${board.writer}</td>
								<td>${board.indate}</td>
							</tr>
							`
				$("#myTr").after(tr);
			})



		},
		error: function(e) {
			console.log(e);
		}

	})
})