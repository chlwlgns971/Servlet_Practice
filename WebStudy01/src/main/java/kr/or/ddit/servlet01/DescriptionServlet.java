package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 단축키 : Alt + Shift + J
 *  서블릿 : 웹상의 요청을 받고, 그에 대한 응답을 생성할 수 있는 객체가 가져야하는 조건들에 대한 명세서(Spec) -> HttpServlet (명세를 상세화 해놓은 캡슐과 같다.)
 *	개발 단계
 *	1. HttpServlet 스펙 상속
 *	2. Complie -> classpath에 배치
 *	3. 서블릿 컨테이너에 등록
 *		1) servlet 2.x : web.xml에 servlet 엘리먼트로 등록
 *		2) servlet 3.x : @WebServlet으로 등록 (어노테이션)
 *	4. 서블릿 매핑 등록
 *		1) servlet 2.x : web.xml에 servlet-mapping으로 등록
 *		2) servlet 3.x : @WebServlet(urlPatterns) 등록
 *	5. Container 재구동
 *	Servlet Container ? 서블릿의 생명주기 관리자 (생명주기 내에서 호출되는 callback을 정의함)
 *	callback 함수 ? 특정 이벤트가 발생했을 때 시스템 내부적으로 자동 호출되는 구조 
 * 	ex)$('#btn').on('click', function(){}) -> function(){}은 개발자가 따로 호출하는 것이 아니고 제이쿼리에 의해서 자동으로 호출되는 callback 함수이다.
 * 	생성 : init, 특별한 설정(load-on-startup)이 없는 한 해당 서블릿을 대상으로 최초의 요청이 발생하면 객체가 생성됨.
 * 				생성 시점에 parameter(String) 넘길 수 있는 구조를 가짐.
 * 				서블릿을 싱글턴(singleton)의 형태로 관리함.
 * 	요청 : - service : 현재 요청의 http method를 판단하고 해당 method callback을 호출함.
 * 		  - doXXX : service에 의해 호출되는 hook 메소드로, 특정 http method 하나를 처리하는 메소드이다.
 * 	소멸 : destroy 
 * 
 * 	서블릿 스펙에서 제공되는 객체
 * 	1. HttpServlet : 서블릿 스펙의 코드화.
 * 	2. HttpServletReuqest : 하나의 요청에 대한 정보를 캡슐화한 객체. 응답 데이터가 나가면 서버 안에서 객체가 소멸된다. (Stateless 방식)
 * 	3. HttpServletResponse : 응답으로 전송될 정보를 캡슐화한 객체.
 * 	4. HttpSession : 한 클라이언트가 하나의 브라우저를 이용한 경우 생성되는 세션에 대한 정보를 캡슐화한 객체.
 * 	5. ServletContext : 서버(WAS, Servlet Container)와 특정 context의 정보를 캡슐화한 객체.
 * 						하나의 context(application, Project) 당 싱글턴 객체의 형태로 관리됨.
 * 	6. ServletConfig : 하나의 서블릿 객체당 하나씩 운영되는 객체로, 해당 서블릿이 등록될때 설정 정보를 가진 객체.
 */
@SuppressWarnings("serial")
public class DescriptionServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String paramValue = config.getInitParameter("param1"); 
		/*
		 * web.xml에서 parameter 설정할 수 있다. 
		 * <param-name>param1</param-name> 
		 * <param-value>value1</param-value>
		 */
		System.out.printf("%s 객체 생성 및 초기화, 전달 파라미터 : %s \n", this.getClass().getName(), paramValue);
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("super.service 호출 전");
		super.service(req, res);
		System.out.println("super.service 호출 후");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 호출");
		System.out.println(req.getServletContext().hashCode());
		System.out.println(req.getSession().getServletContext());
		System.out.println(getServletContext().hashCode());
	}

	private void destory() {
		System.out.printf("%s 객체 소멸 \n", this.getClass().getName());
	}
}
