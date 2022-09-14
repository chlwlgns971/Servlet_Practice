package kr.or.ddit.props.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import kr.or.ddit.props.vo.PropertyVO;

public class FileSystemPropertyDaoImpl implements PropertyDAO {
	private String path = "C:\\Users\\PC-21\\git\\Servlet_Practice\\WebStudy01\\src\\main\\resources\\kr\\or\\ddit\\props\\DataStore.properties";
	private Properties properties;
	public FileSystemPropertyDaoImpl() {
		properties = new Properties();
		//properties file load : InputStream 사용됨. --> IOException 처리
		try(
				FileInputStream fis = new FileInputStream(path);
		){
			properties.load(fis);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	//TDD(Test-Driven Development)
	@Override
	public PropertyVO selectProperty(String propertyName) {
		PropertyVO finded = null;
		String propertyValue = properties.getProperty(propertyName);
		if(propertyValue!=null) {
			finded = new PropertyVO();
			finded.setPropertyName(propertyName);
			finded.setPropertyValue(propertyValue);
		}
		return finded;
	}

	@Override
	public List<PropertyVO> selectProperties() {
		List<PropertyVO> dataList = new ArrayList<>();
		properties.forEach((k,v)->{
			PropertyVO vo =new PropertyVO();
			vo.setPropertyName(k.toString());
			vo.setPropertyValue(v.toString());
			dataList.add(vo);
		});
		return dataList;
	}

	@Override
	public void insertProperty(PropertyVO propertyVO) {
		properties.setProperty(propertyVO.getPropertyName(), propertyVO.getPropertyValue());
		
		try(
				FileOutputStream fos = new FileOutputStream(path);
		){
			properties.store(fos, String.format("'%tc'에 저장함.", Calendar.getInstance()));
		}catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
