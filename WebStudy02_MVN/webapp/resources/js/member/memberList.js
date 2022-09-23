/**
 * 
 */
let deleteForm = $("#deleteForm");
let deleteBtn = $("#deleteBtn").on("click", function() {
	let who = $(this).data("who");
	if (!who) return false;   //누구라고 특정이 안됨, 할일이 없는거니까 false 반환, 정지
	if (confirm("정말로 삭제하시겠습니까?")) {      //동작한다 == YES 눌렀다.
		deleteForm.get(0).who.value = who;
		deleteForm.submit();
	}

});

let updateForm = $("#updateForm");
let updateBtn = $("#updateBtn").on("click", function() {
	let who = $(this).data("who");
	if (!who) return false;   //누구라고 특정이 안됨, 할일이 없는거니까 false 반환, 정지
	updateForm.get(0).who.value = who;
	updateForm.submit();

});

let viewModal = $("#exampleModal").on("hidden.bs.modal", function(event) {

	//안에 들은 inner들을 다 지우라....
	$(this).find(".modal-body").empty();
	//event를 잡아서 특정 handler 안에서 처리중임 이 방식이 EDD, 이벤트 주도형....
	viewForm.get(0).reset();
	deleteBtn.data("who","");
	updateBtn.data("who","");


}).on("show.bs.modal", function(event) {
	let dataTr = event.relatedTarget;
	$(dataTr).data('who');

	let who = $(dataTr).data('who');
	deleteBtn.data("who",who);
	updateBtn.data("who",who);
	viewForm.find('[name=who]').val(who);
	viewForm.submit();

});
let viewForm = $("#viewForm").on("submit", function(event) {
	event.preventDefault(); //동기요청 취소

	//들어가는 데이터가 form에서 다 가져와야 함
	let url = this.action;
	let method = this.method;
	let data = $(this).serialize();      //ajaxForm 적용, --미션1번
	$.ajax({
		url: url,
		method: method,
		data: data,
		dataType: "html",
		success: function(resp) {
			viewModal.find(".modal-body").html(resp);
		},
		//경우에따라 404나 500 에러 날 수도 있음. modal로 띄워보자 
		error: function(errorResp) {
			console.log(errorResp.status);
			viewModal.find(".modal-body").html(errorResp.responseText);
		}
	});
	return false;
});