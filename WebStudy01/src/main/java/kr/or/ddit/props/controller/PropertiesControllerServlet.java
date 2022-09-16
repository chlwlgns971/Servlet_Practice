package kr.or.ddit.props.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

@WebServlet({ "/properties", "/property" })
public class PropertiesControllerServlet extends HttpServlet {
	private PropertyService service = new PropertyServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 모든 컨트롤러에서 이 작업이 제일 첫번째로 수행되어야함
		String servletPath = req.getServletPath();
		Object model = null;
		int statusCode = 200;
		if ("/properties".equals(servletPath)) {
			model = service.readProperties(); // 라면땅 모델!
		} else {

			String name = req.getParameter("name");
			// 라면값이 시세에 맞는지 검증해야함
			if (name == null || name.isEmpty())
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
			else
				model = service.readProperty(name);
			String message = (String) req.getSession().getAttribute("message");
			req.getSession().removeAttribute("message"); // flush attribute
			System.out.println(message);
		}
		if (statusCode == 200) {

			req.setAttribute("model", model);
			String viewName = "/jsonView.do"; // 항상 마지막에는 뷰로 보내주고있음
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			resp.sendError(statusCode);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		PropertyVO newProp = new PropertyVO();
//      newProp.setPropertyName(req.getParameter("propertyName"));

		try {
			BeanUtils.populate(newProp, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}

		boolean valid = validate(newProp);
		if (valid) {

			service.createProperty(newProp);

			String message = "성공";
			req.getSession().setAttribute("message", message);

			String viewName = "/property?name=newProp" + newProp.getPropertyName(); // 이미 위에 property 마샬링하는 애가 있으니까 안만들고
																					// 저기로 보내는것임(name가지고)
			resp.sendRedirect(req.getContextPath() + viewName); // 위에 do get으로 보냄 첫번째else로

		} else {
			resp.sendError(400);
		}
	}

	// 검증만 하게 모듈화!
	private boolean validate(PropertyVO newProp) {
		boolean valid = true;
		if (newProp.getPropertyName() == null) {
			valid = false;
		}
		if (newProp.getPropertyValue() == null) {
			valid = false;
		}
		return valid;
	}

}