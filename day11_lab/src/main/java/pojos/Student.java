package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students_tbl")
public class Student extends BaseEntity{
	//studentId,name,email + Course courseEnrolled
	@Column(length = 30)
	private String name;
	@Column(length = 30,unique = true)
	private String email;
	@ManyToOne //many to one multiplicity : mandatory
	@JoinColumn(name = "course_id") //specifying FK col name : optional BUT reco
	private Course courseEnrolled;
	public Student() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	public Student(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Course getCourseEnrolled() {
		return courseEnrolled;
	}
	public void setCourseEnrolled(Course courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}
	@Override
	public String toString() {
		return "Student "+getId()+" [name=" + name + ", email=" + email + ", courseEnrolled=" + courseEnrolled + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	

}
