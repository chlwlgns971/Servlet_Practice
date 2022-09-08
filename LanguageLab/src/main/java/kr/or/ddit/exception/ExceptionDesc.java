package kr.or.ddit.exception;

import java.io.IOException;

/**
 * 예외(exception, throwable object)
 * Throwable 최상위 트리구조 형성.
 *	-Error
 *	-Exception : 비정상적인 상황이되, 직접 처리가 가능한 예외적 상황을 표현하는 객체.
 *		ckecked exception : 예외가 발생할 것 같은 상황에서 반드시 처리해야 하는 예외. 처리하지 않으면 compile error
 *			ex) IOException, SQLException
 *		unChecked exception : 직접 처리하지 않더라도, 예외 제어권이 메서드 호출구조로 전달되는 예외.
 *			ex) NullPointerException, IllegalArgumentException
 *
 * 처리방법
 * 	-직접 처리 : try-catch-finally, try-multi catch, try with resource
 * 	-위험 처리 : throws 
 * 
 * Custom Exception 정의
 * 	-Exceop
 */

public class ExceptionDesc {
	public static void main(String[] args) {
//		try {
//			method1();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//			throw new RuntimeException(e);
//		}
		int number = 0;
		try {
			String text = "a23";
			number = method2(text);
		} catch (Exception e) {
			number=-1;
			throw new RuntimeException(e);
		}
		finally {
			System.out.println(number);
		}
	}
	private static int method2(String text) {
		try {
			return Integer.parseInt(text);
		} catch (RuntimeException e) {
//			e.printStackTrace();
//			return -1;
			throw new CustomException(e);
		}
	}
	
	private static void method1() throws IOException {
		if(1==1) throw new IOException("강제 발생 예외");
		System.out.println("method1 호출");
	}
}
