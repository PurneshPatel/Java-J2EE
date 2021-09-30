package dao;

import java.sql.*;
import static utils.DBUtils.fetchDBConnection;

public class BankAccountDaoImpl implements IBankAccountDao {
	private Connection cn;
	private CallableStatement cst1;

	public BankAccountDaoImpl() throws ClassNotFoundException, SQLException {
		// get cn
		cn = fetchDBConnection();
		// create CST to execute stored procedure
		String invocationSyntax = "{call transfer_funds(?,?,?,?,?)}";// ? => placeholder for IN / OUT / IN OUT
		cst1 = cn.prepareCall(invocationSyntax);
		// register OUT parameter : OUT / IN OUT
		// => Must inform the JDBC data type of the OUT param to JVM before execution of
		// the stored proc/func --o.w exception is thrown!
		//Method of CST : public void registerOutParameter(int paramPos,int jdbcType)
		cst1.registerOutParameter(4, Types.DOUBLE);
		cst1.registerOutParameter(5, Types.DOUBLE);
		
	}

	@Override
	public String transferFunds(int srcId, int destId, double amount) throws SQLException {
		//set IN params (inherited from PST)
		cst1.setInt(1, srcId);
		cst1.setInt(2, destId);
		cst1.setDouble(3, amount);
		//exec stored proc
		//public boolean execute() throws SE
		cst1.execute();
		//use CST's getters to get the results stored in OUT parameter
		StringBuilder sb=new StringBuilder("Funds transferred !");
		sb.append(" Updated Src balance "+cst1.getDouble(4));
		sb.append(" Updated dest balance "+cst1.getDouble(5));
		return sb.toString();
	}
	public void cleanUp() throws SQLException
	{
		if (cst1 != null)
			cst1.close();
		if(cn != null)
			cn.close();
		System.out.println("acct dao cleaned up !");
	}

}
