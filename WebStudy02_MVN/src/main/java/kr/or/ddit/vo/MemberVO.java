package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 회원관리(Domain Layer)
 *
 */
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
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemRegno1() {
		return memRegno1;
	}

	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}

	public String getMemRegno2() {
		return memRegno2;
	}

	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}

	public String getMemBir() {
		return memBir;
	}

	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}

	public String getMemZip() {
		return memZip;
	}

	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemHometel() {
		return memHometel;
	}

	public void setMemHometel(String memHometel) {
		this.memHometel = memHometel;
	}

	public String getMemComtel() {
		return memComtel;
	}

	public void setMemComtel(String memComtel) {
		this.memComtel = memComtel;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemJob() {
		return memJob;
	}

	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}

	public String getMemLike() {
		return memLike;
	}

	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}

	public String getMemMemorial() {
		return memMemorial;
	}

	public void setMemMemorial(String memMemorial) {
		this.memMemorial = memMemorial;
	}

	public String getMemMemorialday() {
		return memMemorialday;
	}

	public void setMemMemorialday(String memMemorialday) {
		this.memMemorialday = memMemorialday;
	}

	public Integer getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(Integer memMileage) {
		this.memMileage = memMileage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memAdd1 == null) ? 0 : memAdd1.hashCode());
		result = prime * result + ((memAdd2 == null) ? 0 : memAdd2.hashCode());
		result = prime * result + ((memBir == null) ? 0 : memBir.hashCode());
		result = prime * result + ((memComtel == null) ? 0 : memComtel.hashCode());
		result = prime * result + ((memHometel == null) ? 0 : memHometel.hashCode());
		result = prime * result + ((memHp == null) ? 0 : memHp.hashCode());
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		result = prime * result + ((memJob == null) ? 0 : memJob.hashCode());
		result = prime * result + ((memLike == null) ? 0 : memLike.hashCode());
		result = prime * result + ((memMail == null) ? 0 : memMail.hashCode());
		result = prime * result + ((memMemorial == null) ? 0 : memMemorial.hashCode());
		result = prime * result + ((memMemorialday == null) ? 0 : memMemorialday.hashCode());
		result = prime * result + ((memMileage == null) ? 0 : memMileage.hashCode());
		result = prime * result + ((memName == null) ? 0 : memName.hashCode());
		result = prime * result + ((memPass == null) ? 0 : memPass.hashCode());
		result = prime * result + ((memRegno1 == null) ? 0 : memRegno1.hashCode());
		result = prime * result + ((memRegno2 == null) ? 0 : memRegno2.hashCode());
		result = prime * result + ((memZip == null) ? 0 : memZip.hashCode());
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
		MemberVO other = (MemberVO) obj;
		if (memAdd1 == null) {
			if (other.memAdd1 != null)
				return false;
		} else if (!memAdd1.equals(other.memAdd1))
			return false;
		if (memAdd2 == null) {
			if (other.memAdd2 != null)
				return false;
		} else if (!memAdd2.equals(other.memAdd2))
			return false;
		if (memBir == null) {
			if (other.memBir != null)
				return false;
		} else if (!memBir.equals(other.memBir))
			return false;
		if (memComtel == null) {
			if (other.memComtel != null)
				return false;
		} else if (!memComtel.equals(other.memComtel))
			return false;
		if (memHometel == null) {
			if (other.memHometel != null)
				return false;
		} else if (!memHometel.equals(other.memHometel))
			return false;
		if (memHp == null) {
			if (other.memHp != null)
				return false;
		} else if (!memHp.equals(other.memHp))
			return false;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		if (memJob == null) {
			if (other.memJob != null)
				return false;
		} else if (!memJob.equals(other.memJob))
			return false;
		if (memLike == null) {
			if (other.memLike != null)
				return false;
		} else if (!memLike.equals(other.memLike))
			return false;
		if (memMail == null) {
			if (other.memMail != null)
				return false;
		} else if (!memMail.equals(other.memMail))
			return false;
		if (memMemorial == null) {
			if (other.memMemorial != null)
				return false;
		} else if (!memMemorial.equals(other.memMemorial))
			return false;
		if (memMemorialday == null) {
			if (other.memMemorialday != null)
				return false;
		} else if (!memMemorialday.equals(other.memMemorialday))
			return false;
		if (memMileage == null) {
			if (other.memMileage != null)
				return false;
		} else if (!memMileage.equals(other.memMileage))
			return false;
		if (memName == null) {
			if (other.memName != null)
				return false;
		} else if (!memName.equals(other.memName))
			return false;
		if (memPass == null) {
			if (other.memPass != null)
				return false;
		} else if (!memPass.equals(other.memPass))
			return false;
		if (memRegno1 == null) {
			if (other.memRegno1 != null)
				return false;
		} else if (!memRegno1.equals(other.memRegno1))
			return false;
		if (memRegno2 == null) {
			if (other.memRegno2 != null)
				return false;
		} else if (!memRegno2.equals(other.memRegno2))
			return false;
		if (memZip == null) {
			if (other.memZip != null)
				return false;
		} else if (!memZip.equals(other.memZip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memBir=" + memBir + ", memZip=" + memZip
				+ ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memHometel=" + memHometel + ", memComtel="
				+ memComtel + ", memHp=" + memHp + ", memMail=" + memMail + ", memJob=" + memJob + ", memLike="
				+ memLike + ", memMemorial=" + memMemorial + ", memMemorialday=" + memMemorialday + ", memMileage="
				+ memMileage + "]";
	}
}
