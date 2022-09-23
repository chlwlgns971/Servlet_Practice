package kr.or.ddit.commons.exception;

public class PKNotFoundException extends RuntimeException {

	public PKNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PKNotFoundException(String pk, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(String.format("%s에 해당하는 데이터 없음.", pk), cause, enableSuppression, writableStackTrace);
	}

	public PKNotFoundException(String pk, Throwable cause) {
		super(String.format("%s에 해당하는 데이터 없음.", pk), cause);
	}

	public PKNotFoundException(String pk) {
		super(String.format("%s에 해당하는 데이터 없음.", pk));
	}

	public PKNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
