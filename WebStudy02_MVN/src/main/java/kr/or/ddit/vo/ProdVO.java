package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//lombok을 쓰면 필드가 삭제되거나 수정하더라도 자동으로 변경해줌
@ToString(exclude = {"prodDetail"}) //exclude를 이용하여 출력했을 때 제외되는 필드를 설정가능
@EqualsAndHashCode(of = {"prodId"}) //of를 사용하면 다른 변수 상관없이 괄호 안에 것만 같으면 같은 객체로 판단
@Data//애 하나만 써도 JavaBean을 만족하는 VO가 만들어짐 but 조건을 붙이기 위해 위에 항목들 추가
@AllArgsConstructor
@NoArgsConstructor//기본생성자 생성
public class ProdVO implements Serializable{
	private String prodId;
	private String prodName;
	private String prodLgu;
	
	private String lprodNm;
	
	private String prodBuyer;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	@JsonIgnore
	private transient String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private BuyerVO buyer; // has A
	private List<MemberVO> memberList;
	private Integer memCount;
}
