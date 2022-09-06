package kr.or.ddit.vo;

import java.io.Serializable;

import kr.or.ddit.enumpkg.OperatorType;

public class CalculateVO implements Serializable{
	private int leftOp;
	private int rightOp;
	private OperatorType operateor;
	
	public String getExpression() {
		return operateor.getExpression(leftOp, rightOp);
	}
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}
	public OperatorType getOperateor() {
		return operateor;
	}
	public void setOperateor(OperatorType operateor) {
		this.operateor = operateor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + leftOp;
		result = prime * result + ((operateor == null) ? 0 : operateor.hashCode());
		result = prime * result + rightOp;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculateVO other = (CalculateVO) obj;
		if (leftOp != other.leftOp)
			return false;
		if (operateor != other.operateor)
			return false;
		if (rightOp != other.rightOp)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CalculateVO [leftOp=" + leftOp + ", rightOp=" + rightOp + ", operateor=" + operateor + "]";
	}
	
}
