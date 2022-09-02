package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS('+', new RealOperator() {
		@Override
		public int realOperate(int leftOp, int rightOp) {
			return leftOp+rightOp;
		}
	}), 
	MINUS('-', new RealOperator() {
		@Override
		public int realOperate(int leftOp, int rightOp) {
			return leftOp-rightOp;
		}
	}), 
	MULTIPLY('*',(leftOp,rightOp)->{return leftOp*rightOp;}), 
	DIVIDE('/',(leftOp,rightOp)->leftOp/rightOp);
	
	private OperatorType(char sign, RealOperator operator) {
		this.sign = sign;
		this.operator = operator;
	}
	private char sign;
	private RealOperator operator;
	public char getSign() {
		return sign;
	}
	
	public int operate(int leftOp, int rightOp) {
		return operator.realOperate(leftOp, rightOp);
	}
	
	private static final String PATTERN = "%d %s %d = %d";
	
	public String getExpression(int leftOp, int rightOp) {
		return String.format(PATTERN, leftOp, sign, rightOp, operate(leftOp,rightOp));
	}
}
