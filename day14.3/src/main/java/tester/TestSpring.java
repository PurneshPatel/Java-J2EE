package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATMImpl;

public class TestSpring {

	public static void main(String[] args) {
		// start SC , in standalone manner , using xml based meta data instructions ,
		// placed in run time class path
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml")) {
			System.out.println("SC booted!!!!!!!");
			// get located--loaded-instantiated--D.I --inited (rdy to use ) bean for
			// invoking B.L method
			// Inherited API of o.s.b.f.BeanFactory
			/*
			 * <T> T getBean(String name, Class<T> requiredType) throws BeansException
			 */
			ATMImpl atm1=ctx.getBean("my_atm",ATMImpl.class);
			//Invoke B.L
			atm1.withdraw(1000);//will be using specified dependency for B.L
			ATMImpl atm2=ctx.getBean("my_atm",ATMImpl.class);
			System.out.println(atm1==atm2);//true : singleton | false : prototype
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
