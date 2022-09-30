package kr.or.ddit;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateValidatorTest {
	
	private static Validator validator;
	
	@BeforeClass
	public static void setUpClass() {
		 validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(
		                        new PlatformResourceBundleLocator( "kr.or.ddit.msgs.message" )
		                )
		        )
		        .buildValidatorFactory()
		        .getValidator();
	}
	
	@Test
	public void test() {
		MemberVO target = new MemberVO();
		//target.setMemId("a001");
		target.setMemPass("abc");
		target.setMemMail("as.com");
		target.setMemMileage(-1);
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target, UpdateGroup.class);
		for(ConstraintViolation violation : violations) {
			String propertyName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			log.info("{} : {}", propertyName, message);
		}
		
	}

}
