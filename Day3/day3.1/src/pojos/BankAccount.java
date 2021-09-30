package pojos;

public class BankAccount {
	 //id | name | type | bal
	private int acctId;
	private String name;
	private String acType;
	private double balance;
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}
	public int getAcctId() {
		return acctId;
	}
	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankAccount [acctId=" + acctId + ", name=" + name + ", acType=" + acType + ", balance=" + balance + "]";
	}
	
	
}
