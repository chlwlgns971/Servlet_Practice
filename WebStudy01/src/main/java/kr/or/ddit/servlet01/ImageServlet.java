package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageServlet extends HttpServlet{
	
		public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException
		{	
		
			req.setCharacterEncoding("utf-8");
			
			//String imageName = "cat1.jpg";
			String imageName = req.getParameter("name");
			
			if(imageName == null || imageName.isEmpty()){
				resp.sendError(400, "필수 파라미터 누락, 이미지명 없음.");
				return;
			}
			
			//String mime = "image/jpeg";
			//resp.setContentType(mime);
			String mime = getServletContext().getMimeType(imageName);
			resp.setContentType(mime);
			
			String imageFloder = getServletContext().getInitParameter("imageFolderPath");
			File imageFile = new File(imageFloder, imageName);
			
			if(!imageFile.exists()){
				resp.sendError(404, imageName+" 파일이 없음.");
				return;
			}
			
			byte[] buffer = new byte[1024];
			int length = -1;
			OutputStream os = resp.getOutputStream();
			FileInputStream fis = new FileInputStream(imageFile);
			while((length = fis.read(buffer)) != -1){
				os.write(buffer, 0, length); // stream copy
			}
			fis.close();
			os.close();
							
		}	
}