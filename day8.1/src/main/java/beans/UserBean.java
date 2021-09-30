package beans;

import java.sql.SQLException;

import dao.UserDaoImpl;
import pojos.User;

public class UserBean {
//properties  : state (non static n no transient data members)
	//clnt's conversational state (clnt specific info)
	private String email;
	private String password;
	//DAO layer ref
	private UserDaoImpl userDao;
	//validated user details
	private User validatedUserDetails;
	//status message
	private String message;
	
	//default arg-less constr
	public UserBean() throws ClassNotFoundException,SQLException{
		System.out.println("in user bean constr");
		//create instance of DAO layer
		userDao=new UserDaoImpl();
	}
	//setters n getters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDaoImpl getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	public User getValidatedUserDetails() {
		return validatedUserDetails;
	}
	public void setValidatedUserDetails(User validatedUserDetails) {
		this.validatedUserDetails = validatedUserDetails;
	}
	
	public String getMessage() {
		return message;
	}
	//add B.L method : for user validation
	public String authenticateUser() throws SQLException
	{
		System.out.println("in B.L : auth "+email+"  "+password);
		//invoke Dao's method for validation
		validatedUserDetails=userDao.validateUser(email, password);
		//null checking 
		if(validatedUserDetails == null) {
			message="Invalid Login , Please retry...";
			return "login";
		}
		//=> valid login
		//role based authorization
		if(validatedUserDetails.getRole().equals("customer")) //customer login 
		{
			message="Customer Login Successful!!!!";
			return "topics";
		}
		//admin login
		message="Admin Login Successful!!!!";
		return "show_tut_form";
	}
	
	
}
