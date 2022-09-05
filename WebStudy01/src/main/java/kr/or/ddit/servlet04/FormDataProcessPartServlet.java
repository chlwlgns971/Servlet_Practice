package kr.or.ddit.servlet04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Multipart request 처리 방법
 * 
 * MultipartConfig를 통해 업로드 정책 설정 -> Part API 활성화
 * *** 일반 문자데이터는 parameterMap으로 처리 가능해짐.
 * 
 */
@WebServlet(value="/formDataProcess_Part", loadOnStartup = 1)
@MultipartConfig(location = "d:/temp", maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*10*5, fileSizeThreshold = 1024*10)
public class FormDataProcessPartServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Part filePart = request.getPart("filePart");
		File saveFolder = new File("D:\\contents");
		String fileName = filePart.getSubmittedFileName();
		File saveFile = new File(saveFolder,fileName);
		
		try(
			InputStream is =filePart.getInputStream();
			FileOutputStream fos = new FileOutputStream(saveFile);
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
		){
			int tmp = -1;
			while((tmp=bis.read()) != -1) {
				bos.write(tmp);
			}
		}
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String paramName = entry.getKey();
			String[] paramValues = entry.getValue();
			System.out.printf("%s : %s \n ",paramName,paramValues);
		}
	}
}
