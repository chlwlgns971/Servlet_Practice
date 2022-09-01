package kr.or.ddit.servlet01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value="/imageForm", loadOnStartup = 1, initParams = {@WebInitParam(name="imageFolderPath", value="D:/contents/images")})
public class ImageFormServlet_version3 extends HttpServlet{
	
	private ServletContext application;
	private String imageFolderPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext(); // Container와 Context의 정보를 가지는 객체이다.
		imageFolderPath = application.getInitParameter("imageFolderPath");
		//imageFolderPath = config.getInitParameter("imageFolderPath");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File imageFolder = new File(imageFolderPath);
		String[] children = imageFolder.list((file, name)->{
			String mime = application.getMimeType(name); // Tomcat XML 파일에 다 정해져있다.
			return mime != null && mime.startsWith("image");
		});
		
		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(String image : children) {
			options.append(String.format(pattern, image));
		}
		
		req.setAttribute("cPath", req.getContextPath());
		req.setAttribute("options", options);
		req.getRequestDispatcher("/02/imageForm.jsp").forward(req, resp); // TMPLServlet
		
	}
}
