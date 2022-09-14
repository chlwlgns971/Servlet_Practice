package kr.or.ddit.props.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.props.dao.FileSystemPropertyDaoImpl;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;

/**
 * Servlet implementation class GetAllPropertise
 */
@WebServlet("/getAllPropertise.do")
public class GetAllPropertise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PropertyDAO dao = new FileSystemPropertyDaoImpl();
		
		List<PropertyVO> dataList = dao.selectProperties();
	}

}
