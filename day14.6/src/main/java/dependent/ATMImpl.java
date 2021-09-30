package dependent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dependency.Transport;
//singleton n eager
@Component("my_atm")
public class ATMImpl implements ATM {
	@Autowired//(required = false) //autowire=byType
	@Qualifier("soap") //byName
	private Transport myTransport;// =new HttpTransport();//dependency

	public ATMImpl() {
		System.out.println("in cnstr of " + getClass().getName() + " " + myTransport);
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing " + amt);
		byte[] data = ("depositing " + amt).getBytes();
		myTransport.informBank(data);// dependent obj invoking method of dependency

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing " + amt);
		byte[] data = ("withdrawing " + amt).getBytes();
		myTransport.informBank(data);
	}

	

	// custom init method
	@PostConstruct
	public void myInit() {
		System.out.println("in init " + myTransport);
	}

	// custom destory method
	@PreDestroy
	public void myDestroy() {
		System.out.println("in destroy " + myTransport);
	}

}
