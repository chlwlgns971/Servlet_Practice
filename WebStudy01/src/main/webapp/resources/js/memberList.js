 let viewModal = $("#exampleModal").on("hidden.bs.modal",function(event){
      
      $(this).find(".modal-body").empty();
      viewForm.get(0).reset();
   }).on("show.bs.modal", function(event){
      //모달이 뜨는 상황
      let dataTr = event.relatedTarget;
      let who = $(dataTr).data('who');
      viewForm.find('[name=who]').val(who);
      viewForm.submit();
   });
   let viewForm = $("#viewForm").on("submit", function(event){
      event.preventDefault();
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize();    //ajaxForm적용
      $.ajax({
         url : url,
         method : method,
         data : data,
         dataType : "html",
         success : function(resp) {
            viewModal.find(".modal-body").html(resp);
            
         },
         error : function(errorResp) {
            console.log(errorResp.status);
            viewModal.find(".modal-body").html(errorResp.responseText);
            
         }
      });
      
      return false;
   });