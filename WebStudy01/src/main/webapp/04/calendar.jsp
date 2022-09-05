<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>


<%@page import="java.util.Calendar"%>

<%
// ���� ��¥�� �޷� ���
Calendar currentCal = Calendar.getInstance();
Calendar todayCheck_currentCal = Calendar.getInstance();

// request ��ü�� ���� �Ѿ�� ���� ������ ó��
if (request.getParameter("year") != null) {
	currentCal.set(Calendar.YEAR, Integer.parseInt(request.getParameter("year")));
}
if (request.getParameter("month") != null) {
	currentCal.set(Calendar.MONTH, Integer.parseInt(request.getParameter("month")));
}

// ���� 1�Ϸ� ����
currentCal.set(Calendar.DATE, 1);

// 1���� '����'�� ���
int oneDayNum = currentCal.get(Calendar.DAY_OF_WEEK);
// ������� �ִ� �� ��
int monthMaxNum = currentCal.getActualMaximum(Calendar.DAY_OF_MONTH);
// ������� �� ��
int weekSize = currentCal.getActualMaximum(Calendar.WEEK_OF_MONTH);
// ���� '��'�� ���
int year = currentCal.get(Calendar.YEAR);
// ���� '��'�� ���(0�� ���� 11�� ����)
int month = currentCal.get(Calendar.MONTH);
// ���� '��'�� ���
int day = currentCal.get(Calendar.DATE);
//int day = currentCal.get(Calendar.DAY_OF_MONTH);
%>


<html>
<head>

<title>�޷�</title>
<script language="javascript">
	function goCalendar() {
		var form = document.calendarTextBoxForm;

		if ((form.year.value == "")) {
			alert("'��'�� �Է� �ּ���");
			return;
		}
		if (form.year.value < 1970) {
			alert("1970�� 1�� 1�� ���ķ� �˻��� �ּ���.");
			return;
		}
		if ((form.month.value == "")) {
			alert("'��'�� �Է� �ּ���");
			return;
		}
		if ((form.month.value < 1) || form.month.value > 12) {
			alert("'��'�� �ùٸ��� �Է� �ּ���");
			return;
		}
		form.month.value = form.month.value - 1;
		form.action = "calendar.jsp";
		form.target = "_self";
		form.submit();
	}

	function goMonth(month) {
		var form = document.calendarHiddenForm;

		if ((
<%=year%>
	<= 1970) && (month == -1)) {
			alert("1970�� 1�� 1�� ���ķ� �˻��� �ּ���.");
			return;
		}
		if (month == -1) {
			form.year.value =
<%=year - 1%>
	;
			form.month.value = 11;
		} else if (month == 12) {
			form.year.value =
<%=year + 1%>
	;
			form.month.value = 0;
		} else {
			form.year.value =
<%=year%>
	;
			form.month.value = month;
		}

		form.action = "calendar.jsp";
		form.target = "_self";
		form.submit();
	}
</script>
</head>
<body>

	<center>
		<table border="0">
			<tr height="30">
				<td width="30" align="center"><a
					href="javascript:goMonth(<%=month - 1%>);">��</a></td>
				<td width="150" align="center"><%=year%> �� <%=month + 1%> ��</td>
				<td width="30" align="center"><a
					href="javascript:goMonth(<%=month + 1%>);">��</a></td>
			</tr>
		</table>








		<table border="0" cellpadding="0" cellspacing="0">
			<tr height="30" bgcolor="#dcdcdc">
				<td width="30" align="center">��</td>
				<td width="30" align="center">��</td>
				<td width="30" align="center">ȭ</td>
				<td width="30" align="center">��</td>
				<td width="30" align="center">��</td>
				<td width="30" align="center">��</td>
				<td width="30" align="center">��</td>
			</tr>

			<%
			//int dayNum = 1;
			boolean dayCheck = false;

			// ���� ���� �� ����ū �ݺ�
			for (int i = 0; weekSize > i; i++) {
				out.println("<tr height='25'>");

				// �������� 7�� �̴� 7�� �ݺ�
				for (int j = 0; (7 > j); j++) {
					out.println("<td align='center'>");

					// ���� ���� 1���� ���Ͽ� �ش��ϴ� ������ ����ϱ�����
					if (oneDayNum == j + 1 || dayCheck) {
				// ���� ���� �ִ� �ϼ��ϰ��
				if (monthMaxNum >= day) {
					/// �Ͽ����� ��� ���ڻ� '����'
					if (currentCal.get(Calendar.DAY_OF_WEEK) == 1) {
						// �Ͽ����̸鼭 ������ ��� ���ڻ��� '����', ���� ���ϰ� �ƴϸ� ���ڻ��� '����'
						if (todayCheck_currentCal.equals(currentCal)) {
							out.println("<font color='red'><b>" + day + "</b></font>");
						} else {
							out.println("<font color='red'>" + day + "</font>");
						}
						currentCal.set(Calendar.DATE, ++day);
						dayCheck = true;
						// ������� ��� ���ڻ� '�Ķ�'
					} else if (currentCal.get(Calendar.DAY_OF_WEEK) == 7) {
						// ������̸鼭 ������ ��� ���ڻ��� '�Ķ�', ���� ���ϰ� �ƴϸ� ���ڻ��� '�Ķ�'
						if (todayCheck_currentCal.equals(currentCal)) {
							out.println("<font color='blue'><b>" + day + "</b></font>");
						} else {
							out.println("<font color='blue'>" + day + "</font>");
						}
						currentCal.set(Calendar.DATE, ++day);
						dayCheck = true;
						// �Ͽ��ϵ� �ƴϰ� ����ϵ� �ƴϸ� �⺻�Ӽ� ������� 
					} else {
						//  �Ͽ��ϵ� �ƴϰ� ����ϵ� �ƴѵ� �����̸� �������ϰ�
						if (todayCheck_currentCal.equals(currentCal)) {
							out.println("<b>" + day + "</b>");
						} else {
							out.println(day);
						}
						currentCal.set(Calendar.DATE, ++day);
						dayCheck = true;
					}

				} else {
					out.println("&nbsp;");
				}
					} else {
				out.println("&nbsp;");
					}

					out.println("</td>");

				}
				out.println("</tr>");
			}
			%>

		</table>

		<!-- �� / �� �� �ش��ϴ� �� -->
		<form name="calendarHiddenForm" method="post">
			<input type="hidden" name="year" value="" /> <input type="hidden"
				name="month" value="" />
		</form>

		<!-- TextBox �� �ش��ϴ� �� -->
		<form name="calendarTextBoxForm" method="post">
			<input type="text" name="year" size="5" value="" maxlength="4" /> ��
			<input type="text" name="month" size="3" value="" maxlength="2" /> ��
			<input type="button" onclick="javascript:goCalendar();" value="�̵�" />
		</form>

	</center>
</body>
</html>