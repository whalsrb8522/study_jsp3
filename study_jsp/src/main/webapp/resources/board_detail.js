// 미리 보낼 데이터를 만들어서 함수로 전달 commentData
document.getElementById('commentBtn').addEventListener('click', () => {
	const commentContent = document.getElementById('commentContent').value;
	console.log(commentContent);
	if (commentContent == null || commentContent == "") {
		alert('댓글을 입력해주세요.');
		return false;
	} else {
		let commentData = {
			bno: bnoVal,
			writer: document.getElementById('commentWriter').value,
			content: commentContent
		};
		postCommentToServer(commentData).then(result => {
			if (result > 0) {
				alert('댓글 등록 정상');
				document.getElementById('commentContent').value = '';
				printCommentLlist(bnoVal);
			}
		});
	}
});

// 화면에서 등록한 댓글내용을 컨트롤러로 전송 -> DB저장
async function postCommentToServer(commentData) {
	try {
		const uri = "/cmt/post";
		const config = {
			method: 'post',
			headers: {
				'content-Type': 'application/json; charset=UTF-8'
			},
			body: JSON.stringify(commentData)
		};
		const resp = await fetch(uri, config);
		const result = await resp.text();	// isOk
		return result;
	} catch (error) {
		console.log(error);
	}
}

async function getCommentListFromServer(bno) {
	try {
		// 데이터가 controller로 이동한 후 컨트롤러 처리를 하고 응답 데이터를 가져와서 resp에 저장
		const resp = await fetch('/cmt/list/' + bno);
		const cmtList = await resp.json();	// 댓글 객체가 리턴
		return cmtList;
	} catch (error) {
		console.log(error);
	}
}

function printCommentLlist(bno) {
	getCommentListFromServer(bno).then(result => {
		console.log(result);
		
		if(result.length > 0) {		// 현재 등록된 댓글이 있음을 위미
			spreadCommentList(result);		
		} else {
			let div = document.getElementById('accordionExample');
			div.innerText = "댓글이 없습니다.";		
		}				
	});
}

function spreadCommentList(result) {
	console.log(result);
	
	let div = document.getElementById('accordionExample');
	div.innerHTML = "";
	
	for(let i = 0; i < result.length; i++) {
		let html  = `<div class="accordion-item">`;
			html += 	`<h2 class="accordion-header" id="heading${i}">`;
			html += 		`<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
			html += 			`${result[i].cno}. ${result[i].writer}`;
			html +=			`</button>`;
			html += 	`</h2>`;
			html += 	`<div id="collapse${i}" class="accordion-collapse collapse show" aria-labelledby="heading${i}">`;
			html += 		`<div class="accordion-body">`;
			html +=				`<button type="button" data-cno="${result[i].cno}" data-writer=${result[i].writer} class="btn btn-sm btn-outline-warning cmtModBtn">%</button>`;
			html +=				`<button type="button" data-cno="${result[i].cno}" class="btn btn-sm btn-outline-danger cmtDelBtn">X</button>`;
			html +=				`<input type="text" class="form-control" id="commentContent1" value="${result[i].content}">`;
			html += 			`${result[i].regdate}`;
			html +=			`</div>`;
			html +=		`</div>`;
			html += `</div>`;
		div.innerHTML += html;	// 누적해서 담기
	}
}

async function removeCommentFromServer(cnoVal) {
	try {
		const uri = '/cmt/remove?cnoVal=' + cnoVal;
		const config = {
			method : 'post'
		}
		const resp = await fetch(uri, config);
		const result = await resp.text();
		return result;
	} catch(error) {
		console.log(error);
	}
}

async function updateCommentFromServer(cnoVal, commentContent1, writer) {
	try {
		const uri  = "/cmt/modify";
		const config = {
			method : "post",
			headers : {
				'Context-Type' : 'application/json; charset=UTF-8'
			},
			body : JSON.stringify({cno : cnoVal, content : commentContent1, writer : writer})
		}
		const resp = await fetch(uri, config);
		const result = await resp.text();
		return result;
	} catch(error) {
		console.log(error);
	}
}

document.addEventListener('click', e => {	// e : 내가 클릭한 객체
	if(e.target.classList.contains('cmtModBtn')) {
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		
		// 기존 위치에서 값을 읽얻르여 내용을 DB에 저장한 후 변경
		// 현재 수정하고자 하는 값 input box의 value값을 찾기위한 작업
		let div = e.target.closest('div');		// closest : 타겟을 기준으로 가장 가까운 값 찾기
		let commentContent1 = div.querySelector('#commentContent1').value;
		let writer = e.target.writer;
		
		updateCommentFromServer(cnoVal, commentContent1, writer).then(result => {
			console.clear(result);
			if(result > 0) {
				alert('댓글 수정 완료');
				printCommentLlist(bnoVal);
			} else {
				alert('댓글 수정 실패');
			}
		});
	}
	if(e.target.classList.contains('cmtDelBtn')) {
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		
		removeCommentFromServer(cnoVal).then(result => {
			if(result > 0) {
				alert('댓글 삭제 완료');
				printCommentLlist(bnoVal);
				console.log(bnoVal);
			} else {
				alert('댓글 삭제 실패');
			}
		});	
	}	
});