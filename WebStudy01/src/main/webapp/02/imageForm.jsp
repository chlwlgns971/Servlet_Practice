<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
select {
   background-color: aqua;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
   integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
   crossorigin="anonymous"></script>
<script type="text/javascript">
   const PATTERN = "/WebStudy01/image?name=%V";
   $(document).ready(function() {
      $("form").on("submit", function(event) {
         event.preventDefault();
         //                 <img src="/WebStudy01/image?name=cat1.jpg"/>
         //                let name = this.name.value;
         let name = $(this).find("[name='name']").val();
         let newImg = $("<img>").attr("src", PATTERN.replace("%V", name));
         $("#imageArea").html(newImg);
         return false;
      });
   });
</script>
</head>
<body>
   <h4>이미지 파일 선택</h4>
   <form action='${cPath}/image'>
      <select name='name'>
      ${options}
      </select> <input type='submit' value='전송' />
   </form>
   <div id="imageArea"></div>
</body>
</html>