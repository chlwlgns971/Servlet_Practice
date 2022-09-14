package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.props.dao.FileSystemPropertyDaoImpl;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService {
	private PropertyDAO dao = new FileSystemPropertyDaoImpl(); //정상적인 의존구조 -> 결합력을 높게 발생시킨다. / 단일책임을 권고해야하는 이유? -> 응집력을 높이고 결합도를 낮춘다.
	
	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO vo = dao.selectProperty(propertyName);
		if(vo == null) throw new PKNotFoundException();
		return vo;
	}

	@Override
	public List<PropertyVO> readProperties() {
		List<PropertyVO> dataList = dao.selectProperties();
		dataList.forEach((vo) -> {
			if(vo.getDescription()==null) vo.setDescription("라볶이 만들었음.");
		});
		/*
		 * if(dataList.size() != 0) { boolean checkedValue =
		 * dataList.contains("description"); if(!checkedValue) { PropertyVO vo =new
		 * PropertyVO(); vo.setPropertyName("description");
		 * vo.setPropertyValue("라볶이 먹었음."); dao.insertProperty(vo); dataList.add(vo); }
		 * 
		 * }
		 */
		return dataList;
	}

	@Override
	public void createProperty(PropertyVO vo) {
		dao.insertProperty(vo);
	}

}
