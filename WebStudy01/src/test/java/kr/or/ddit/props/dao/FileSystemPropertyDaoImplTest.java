package kr.or.ddit.props.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.props.vo.PropertyVO;

public class FileSystemPropertyDaoImplTest {
	
	PropertyDAO dao = new FileSystemPropertyDaoImpl();

	@Test
	public void testSelectProperty() {
		PropertyVO vo = dao.selectProperty("prop1");
		assertNotNull(vo);
	}

	@Test
	public void testSelectProperties() {
		List<PropertyVO> dataList = dao.selectProperties();
		assertNotNull(dataList);
		assertNotEquals(0, dataList.size());
		assertEquals(3, dataList.size());
	}

	@Test
	public void testInsertProperty() {
		PropertyVO vo = new PropertyVO();
		vo.setPropertyName("prop3");
		vo.setPropertyValue("value3");
		dao.insertProperty(vo);
	}

}
