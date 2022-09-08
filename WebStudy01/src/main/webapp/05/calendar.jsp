<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="static java.util.Calendar.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Locale locale = request.getLocale();
	String yearParam = request.getParameter("year");
	String monthParam = request.getParameter("month");
	String language = request.getParameter("language");

	Calendar calendar = getInstance();
	if(yearParam!=null && yearParam.matches("\\d{4}") && monthParam!=null && monthParam.matches("^1[0-1]$|^[0-9]$")){
		calendar.set(YEAR, Integer.parseInt(yearParam));
		calendar.set(MONTH, Integer.parseInt(monthParam));
	}
	if(language!=null && !language.isEmpty()){
		locale = Locale.forLanguageTag(language);
	}
	
	String title = String.format(locale, "%1$tY, %1$tB", calendar);
	
	calendar.add(MONTH,-1);
	int beforeYear = calendar.get(YEAR);
	int beforeMonth = calendar.get(MONTH);
	calendar.add(MONTH,2);
	int nextYear = calendar.get(YEAR);
	int nextMonth = calendar.get(MONTH);
	
	calendar.add(MONTH,-1);
	int year = calendar.get(YEAR);
	int month = calendar.get(MONTH);
	
	calendar.set(DAY_OF_MONTH, 1);
	int dayOfWeek1st = calendar.get(DAY_OF_WEEK);
	int offset = dayOfWeek1st -1;
	calendar.add(DAY_OF_MONTH, -offset);
	 
	DateFormatSymbols dfs = new DateFormatSymbols(locale); //서버가 한국이어서 자동으로 한글로 잡아준다. -> Locale.KOREAN 생략 가능
														//여기선 서버언어를 자동으로 잡아주기 위해 첫줄에 getLocale로 값을 가져옴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/calendar.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h4>
	<a href="?year=<%=beforeYear %>&month=<%=beforeMonth%>" class="changeBtn" data-month="<%=beforeMonth%>">&lt;&lt;&lt;</a>
	<%=title %>
	<a href="?year=<%=nextYear %>&month=<%=nextMonth%>" class="changeBtn" data-month="<%=nextMonth%>">&gt;&gt;&gt;</a>
</h4>
<form name="calForm" method="post">
	<input type="text" name="year" pattern="\d{4}" placeholder="2022" value="<%=year%>">
	<select name="month">
		<%
			String[] months = dfs.getMonths();
			for(int idx=JANUARY; idx<=DECEMBER; idx++){
				out.println(String.format("<option value='%d'>%s</option>", idx, months[idx]));
			}
		%>
	</select>
	<select name="Language">
		<%
			Locale[] locales = Locale.getAvailableLocales();
			for(Locale tmp : locales){
				String display = tmp.getDisplayLanguage(tmp);
				if(display.isEmpty()) continue;
				out.println(String.format("<option value='%s'>%s</option>",tmp.toLanguageTag(),display));
			}
		%>
		<option value="ko-KR">한국어</option>
		<option>영어</option>
	</select>
	<select name="timeZoneId">
		<%
			
		%>
	</select>
</form>
<table>
	<thead>
		<tr>
		<%	
			String[] weekDays = dfs.getShortWeekdays();
			for(int col=SUNDAY; col<=SATURDAY; col++){
				out.println(String.format("<th>%s</td>",weekDays[col]));
			}
		%>
		</tr>
	</thead>
	<tbody>
	<%
	for(int row=1; row<=6; row++){
		out.println("<tr>");
		for(int col=SUNDAY; col<=SATURDAY; col++){
			out.println(String.format("<td>%te</td>",calendar));
			calendar.add(DAY_OF_MONTH, 1);
		}
		out.println("</tr>");
	}
	%>
	</tbody>
</table>
<script type="text/javascript">
	let yearTag = $("[name=year]").val("<%=year%>")
	let monthTag = $("[name=month]").val("<%=month%>")
	let languageTag = $("[name=language]").val("<%=language%>")

	
	$(".changeBtn").on("click",function(event){
		event.preventDefault();
		let year = $(this).data("year");
		let month = $(this).data("month");
		yearTag.val(year);
		monthTag.val(month);
		calForm.submit();
		return false;
	})
	
	$(document.calForm).on("change",":input[name]",function(event){ //form태그 안에 name속성을 가진 태그들이 변할때마다 실행
		//this.form.submit(); //submit 이벤트는 발생하지 않는다.
		this.form.requestSubmit(); //submit 이벤트 발생
		
	})
</script>
</body>
</html>