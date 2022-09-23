/**
 * 
 */
window.addEventListener("DOMContentLoaded", function(event) {
   $.fn.sessionTimer = function(timeout, msgModal) {
      //파라미터 검증하는 코드 들어와야 함, timeout이 없다 == timeout이 0, null, undefined 일 떄
      // timeout의 type의 number가 아니거나
      //timeout이 0보다 크거나 작다
      if (!timeout || typeof timeout != 'number' || timeout <= 0)
         //에러나 예외를 발생시켜서 중단시키겠다
         throw Error("타이머 처리 불가");

      //timeout parameter는 검증했는데 msgModal은검증을...
      //해오세요.....?... msgModal....없을 때는 Error를 발생하는게 아니라 없으면 만들어주세요
      //있으면 있는데로 있는거 쓰세요
      if (!msgModal)

         const SPEED = 100;
      let timer = timeout;
      let timerJob = null;
      let msgJob = null;
      let timerArea = this;
      let msgArea = $(msgModal).on("click", ".ctrlBtn", function() {
         let command = $(this).text();
         if ('YES' == command) {
            init();
            $.ajax({
               method: "head"
            });
         }
         msgArea.modal('hide');
      });

      let init = function() {
         timer = timeout;
         if (timerJob == null)
            timerJob = setInterval(function() {
               timerArea.html(moment(--timer * 1000).format('mm:ss'));
               if (timer <= 0)
                  clearInterval(timerJob);
            }, 1 * SPEED);
         if (msgJob != null)
            clearTimeout(msgJob);
         msgJob = setTimeout(function() {
            msgArea.modal('show');
         }, (timeout - 60) * SPEED);
      }

      init();
   }

});