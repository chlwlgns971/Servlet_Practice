<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<pre>
- 이항 연산자로 4칙 연산 처리하기
1. 동기 처리
2. 비동기 처리
</pre>
<form id="calcForm" action="<%=request.getContextPath()%>/calculate2" method="get">
   <input  type="number" name="leftOp" />
   <select name="operator">
      <%
         for(OperatorType single :OperatorType.values()){
            %>
               <option value="<%=single.name()%>"><%=single.getSign()%></option>
            <%
         }
      %>
   </select>
   <input type="number" name="rightOp" />
   <input type="submit" value="=">
</form>
<%-- ${leftOp} ${op} ${rightOp} = ${res} --%>
<h4><span id="resultArea"></span></h4>

   <script type="text/javascript">
    /*    const PATTERN = "%LO %OP %RO = %RE";
      
        $(document).on("submit", "#calcForm", function(event) {
         event.preventDefault();
         let url = this.action;
         let method = this.method;
         let data = $(this).serialize(); // query string 생성
         
         $.ajax({
            url : url,
            method : method,
            data : data,
            dataType : "json",
            success : function(resp) {
               $("#resultArea")
                     .html(
                        PATTERN.replace("%LO", resp.leftOp)
                           .replace("%OP", resp.op)
                           .replace("%RO", resp.rightOp)
                           .replace("%RE", resp.res)
                     );
               
            },
            error : function(errorResp) {
               console.log(errorResp.status);
            }
         });         
         return false;
      });    */
   </script>
</body>
</html>