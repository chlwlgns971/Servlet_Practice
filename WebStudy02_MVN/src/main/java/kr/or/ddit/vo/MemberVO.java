package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 회원관리(Domain Layer)
 *
 * 한 사람의 회원에 대한 정보(구매기록이 포함)
 * 	has 관계
 * 	has A(1:1)
 * 	has Many(1:N)
 * 
 * 2개 이상의 테이블을 조인하고 결과 바인딩하는 방법.
 * 1. 대상 테이발 간의 관계 파악.
 *      MEMBER(1) : PROD(N)
 *      PROD(1) :  BUYER(1)
 * 2. 각 테이블로부터 데이터를 바인딩할 VO정의
 *      해당 VO 간의 관계를 형성.
 *      MemberVO has Many ProdVO
 * 3. 조인 쿼리 작성
 * 4. resultMap을 사용하여 결과 바인딩(수동)
 *      has Many -> collection
 *      has A -> association
 */
@Data
@EqualsAndHashCode(of = "memId")
@ToString(exclude = {"memPass", "memRegno1", "memRegno2"})
public class MemberVO implements Serializable {
	private String memId;
	@JsonIgnore
	private transient String memPass;
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	
	private List<ProdVO> prodList; //has Many
	
	public String getMemTest() {
		return "테스트";
	}
}
