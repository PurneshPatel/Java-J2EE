package dependency;

import org.springframework.stereotype.Component;

@Component("test")
//Singleton n Eager
public class TestTransport implements Transport {
	public TestTransport() {
		System.out.println("in cnstr of " +getClass().getName());
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
