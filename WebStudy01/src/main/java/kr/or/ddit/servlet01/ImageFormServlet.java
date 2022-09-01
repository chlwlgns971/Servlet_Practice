package kr.or.ddit.servlet01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


//@WebServlet(value="/imageForm", loadOnStartup = 1, initParams = {@WebInitParam(name="imageFolderPath", value="D:/contents/images")})
public class ImageFormServlet extends HttpServlet{
	
	private ServletContext application;
	private String imageFolderPath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		imageFolderPath = application.getInitParameter("imageFolderPath");
		//imageFolderPath = config.getInitParameter("imageFolderPath");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File imageFolder = new File(imageFolderPath);
		String[] children = imageFolder.list((file, name)->{
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image");
		});
		
		String pattern = "<option>%s</option>";
		StringBuffer options = new StringBuffer();
		for(String image : children) {
			options.append(String.format(pattern, image));
		}
		
		StringBuffer html = new StringBuffer();
		html.append("<html>                              ");
		html.append("	<body>                           ");
		html.append("	<h4> 이미지 파일 선택 </h4>      ");
		html.append("	<form action = '"+req.getContextPath()+"/image'>  ");
		html.append("	   <select name = 'name'>        ");
		html.append(options);
		html.append("	   </select>                     ");
		html.append("<input type='submit' value='전송' />");
		html.append("	</form>                          ");
		html.append("	</body>                          ");
		html.append("</html>                             ");
		
		resp.setContentType("text/html;charset=UTF-8"); // 꼭 !! Mime 설정은 출력 스트림을 열기 전에 한다.
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.println(html);
		}finally {
			if(out != null) out.close();
		}
	}
}
