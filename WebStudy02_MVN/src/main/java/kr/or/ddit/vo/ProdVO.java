package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Part;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.validate.UpdateGroup;
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
	private int rnum;
	@NotBlank(groups = {UpdateGroup.class})
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String prodLgu;
	
	private String lprodNm;
	@NotBlank
	private String prodBuyer;
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	@Size(max = 50)
	private String prodOutline;
	@JsonIgnore
	private transient String prodDetail;
	@NotBlank
	private String prodImg;
	
	private MultipartFile prodImage;
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage!=null && prodImage.isEmpty()) {
			this.prodImage = prodImage;
			this.prodImg = UUID.randomUUID().toString();
		}
	}
	public void saveTo(File saveFolder) throws IOException {
		if(prodImage == null) return;
		File saveFile = new File(saveFolder, this.prodImg);
		prodImage.transferTo(saveFile);
	}
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	@Size(max = 3)
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private BuyerVO buyer; // has A
	
	private Set<MemberVO> memberList; // has Many
	
	private Integer memCount;
}
