package kr.or.ddit.object.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.reflect.ReflectionTest;
import kr.or.ddit.vo.MemberVO;

/**
 * 리플렉션 ? 
 * 	-> 객체로부터 타입, property, method 등 해당 객체의 구조를 추측해 가는 과정. java.lang.reflect패키지의 API들로 지원.
 * 		Class, Field, Method, Parameter...
 *
 */

public class ReflectionDesc {
	public static void main(String[] args) {
		Object retValue = ReflectionTest.getObject();
		System.out.println(retValue);
		
		String setterName = "setMem_hp";
		Class clz = retValue.getClass();
		
		try {
//			Method setter = clz.getDeclaredMethod(setterName, String.class);
//			setter.invoke(retValue, "010-8511-4526");
//			
//			String getterName = "getMem_hp";
//			Method getter = clz.getDeclaredMethod(getterName);
//			Object fldValue = getter.invoke(retValue);
//			System.out.println(fldValue);
			
			PropertyDescriptor pd = new PropertyDescriptor("mem_hp", clz);
			Method setter = pd.getWriteMethod();
			setter.invoke(retValue, "010-8511-4526");
			Method getter = pd.getReadMethod();
			Object fldValue = getter.invoke(retValue);
			System.out.println(fldValue);
		} catch (SecurityException |IllegalArgumentException | IntrospectionException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
