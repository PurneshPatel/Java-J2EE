package dao;

import pojos.Student;

public interface IStudentDao {
//add a method for student admission
	String admitNewStudent(Student student,String courseTitle);
}
