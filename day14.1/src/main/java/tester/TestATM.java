package tester;

import dependency.SoapTransport;
import dependent.ATMImpl;

public class TestATM {

	public static void main(String[] args) {
		ATMImpl ref=new ATMImpl();//dependent obj
		//create dependency
		SoapTransport transport=new SoapTransport();
		//setter based D.I
		ref.setMyTransport(transport);
		ref.deposit(1000);//B.L

	}

}
