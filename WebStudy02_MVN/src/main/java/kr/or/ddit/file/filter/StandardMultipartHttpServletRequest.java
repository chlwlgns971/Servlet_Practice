package kr.or.ddit.file.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.file.StandardMutipartFile;

/**
 * 원본 request가 가진 Part -> MultipartFile 객체로 wrapping.
 *
 */
public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper{
	private Map<String, List<MultipartFile>> files;

	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		parseRequest(request);
	}
	
	private void parseRequest(HttpServletRequest req) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		files = new LinkedHashMap<>();
		Iterator<Part> it = parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			String contentType = part.getContentType();
			if(contentType==null) continue;
			MultipartFile file = new StandardMutipartFile(part);
			String partName = file.getName();
			List<MultipartFile> already = files.get(partName);
			if(already==null) {
				already = new ArrayList<>();
				files.put(partName, already);
			}
			already.add(file);
		}
	}

	
	public MultipartFile getFile(String partName){
		List<MultipartFile> fileList = files.get(partName);
		if(fileList!=null && !fileList.isEmpty()) {
			return fileList.get(0);
		}else {
			return null;
		}
	}
	
	public List<MultipartFile> getFiles(String partName){
		return files.get(partName);
	}
}
