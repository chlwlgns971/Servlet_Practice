package kr.or.ddit.servlet04;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.vo.FormDataVO;

/**
 * 문자열의 형태로 전송되는 파라미터 핸들링.
 * 
 * 1. Request Line(GET)
 * 	->서버의 설정으로 특수문자 처리
 * 	ex) server.xml -> connector -> URIEncoding, useBodyEncodingForURI
 * 2. Request Body(POST, PUT/Patch)
 * 	-request.setCharacterEncoding(encoding)
 * 
 * **특수문자가 포함된 경우. -해당 데이터에 접근하기 전에 charset설정 필요.
 * 
 * String getParameter(name)
 * String[] getParameterValues(name)
 * Map<String, String[]> getParameterMap()
 * Enumeration<String> getParameterNames()
 */
@WebServlet("/formDataProcess")
public class FormDataProcessServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String paramIpt1=(String) request.getParameter("paramIpt1");
//		String paramIpt2=(String) request.getParameter("paramIpt2");
//		String paramIpt3=(String) request.getParameter("paramIpt3");
//		String paramIpt4=(String) request.getParameter("paramIpt4");
//		String paramIpt5=(String) request.getParameter("paramIpt5");
//		String paramIpt6=(String) request.getParameter("paramIpt6");
//		String paramIpt7=(String) request.getParameter("paramIpt7");
//		String paramIpt8=(String) request.getParameter("paramIpt8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String paramName = entry.getKey();
			String[] paramValues = entry.getValue();
			System.out.printf("%s : %s \n ",paramName,paramValues);
		}
		FormDataVO vo = new FormDataVO();
//		vo.setParamIpt1(request.getParameter("paramIpt1"));
//		vo.setParamIpt2(request.getParameter("paramIpt2"));
		try {
			BeanUtils.populate(vo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("vo",vo);
		
		String view="/WEB-INF/views/formDataView.jsp";
		request.getRequestDispatcher(view).forward(request,response);
	}
}
