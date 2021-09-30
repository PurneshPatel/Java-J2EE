package dependent;

import dependency.Transport;

public class ATMImpl implements ATM {
	private Transport myTransport;// =new HttpTransport();//dependency

	//constr based D.I
	public ATMImpl(Transport t1234) {
		myTransport=t1234;
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
	public void myInit() {
		System.out.println("in init " + myTransport);
	}

	// custom destory method
	public void myDestroy() {
		System.out.println("in destroy " + myTransport);
	}

}
