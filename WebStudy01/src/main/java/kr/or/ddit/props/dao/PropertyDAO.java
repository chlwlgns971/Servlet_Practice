package kr.or.ddit.props.dao;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

/**
 * Property 관리를 위한 Persistence Layer
 *
 */
public interface PropertyDAO {
	public PropertyVO selectProperty(String propertyName);
	public List<PropertyVO> selectProperties();
	public void insertProperty(PropertyVO propertyVO);
}
