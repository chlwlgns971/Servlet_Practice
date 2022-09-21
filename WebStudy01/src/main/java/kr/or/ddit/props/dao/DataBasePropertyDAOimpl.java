package kr.or.ddit.props.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.vo.PropertyVO;

public class DataBasePropertyDAOimpl implements PropertyDAO {

   @Override
   public PropertyVO selectProperty(String propertyName) {
      // TODO Auto-generated method stub
         String sql = "SELECT PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES"
               + " WHERE PROPERTY_NAME = ?";
         
         PropertyVO propertyVO = null;
         String[] headers = null;
         
         
         try(
            Connection oracleConn = ConnectionFactory.getConnection();
            PreparedStatement oracleStmt = oracleConn.prepareStatement(sql);
         ) {
        	oracleStmt.setString(1, propertyName);
            ResultSet rs = oracleStmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            headers = new String[count];
            for(int i = 1; i <= count; i++){
               headers[i-1] = rsmd.getColumnName(i);
            }
            if(rs.next()){
            	propertyVO = new PropertyVO();
                propertyVO.setPropertyName(rs.getString("PROPERTY_NAME"));
                propertyVO.setPropertyValue(rs.getString("property_value"));
                propertyVO.setDescription(rs.getString("DESCRIPTION"));
            }
            return propertyVO;
         } catch (SQLException e) {
            e.printStackTrace();
            //예외가 발생한 경우에, 여기선 지금 return이 없는데, 
//            return null 이라고 하면, 발생한 예외 정보 사라지고, 어디서 null생긴지 모름
            
//            interface의 예외의 종류를 unchecker로 바꿔 주어야 함
            throw new RuntimeException(e);
         } 
      
      
      
   }

   @Override
   public List<PropertyVO> selectProperties() {
      String sql = "SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES";
      
      List<PropertyVO> dataList = new ArrayList<>();
      String[] headers = null;
      
      
      try(
         Connection oracleConn = ConnectionFactory.getConnection();
         Statement oracleStmt = oracleConn.createStatement();
      ) {
         ResultSet rs = oracleStmt.executeQuery(sql);
         ResultSetMetaData rsmd = rs.getMetaData();
         int count = rsmd.getColumnCount();
         headers = new String[count];
         for(int i = 1; i <= count; i++){
            headers[i-1] = rsmd.getColumnName(i);
         }
         while(rs.next()){
            PropertyVO vo = new PropertyVO();
            dataList.add(vo);
            vo.setPropertyName(rs.getString("PROPERTY_NAME"));
            vo.setPropertyValue(rs.getString("property_value"));
            vo.setDescription(rs.getString("DESCRIPTION"));
         }
         return dataList;
      } catch (SQLException e) {
         e.printStackTrace();
         //예외가 발생한 경우에, 여기선 지금 return이 없는데, 
//         return null 이라고 하면, 발생한 예외 정보 사라지고, 어디서 null생긴지 모름
         
//         interface의 예외의 종류를 unchecker로 바꿔 주어야 함
         throw new RuntimeException(e);
      }
      
   }

   @Override
   public void insertProperty(PropertyVO propertyVo) {
      if(1==1) throw new RuntimeException("해당 뷰는 insert 할 수 없습니다.");

   }

}
