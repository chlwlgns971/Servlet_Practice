package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StandardMutipartFile implements MultipartFile {
	private Part filePart;

	public StandardMutipartFile(Part filePart) {
		this.filePart = filePart;
	}

	@Override
	public byte[] getBytles() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		return filePart.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return filePart.getInputStream();
	}

	@Override
	public String getName() {
		return filePart.getName();
	}

	@Override
	public String getOriginalFilename() {
		return filePart.getSubmittedFileName();
	}

	@Override
	public long getSize() {
		return filePart.getSize();
	}

	@Override
	public boolean isEmpty() {
		return filePart.getSize()==0 || StringUtils.isBlank(getOriginalFilename());
	}

	@Override
	public void transferTo(File dest) throws IOException {
		filePart.write(dest.getCanonicalPath());

	}

}
