package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuVO {
	public MenuVO(String menuURL, String menuText) {
		super();
		MenuURL = menuURL;
		this.menuText = menuText;
	}
	private String MenuURL;
	private String menuText;
	private String menuId;
	private String menuColor;
}
