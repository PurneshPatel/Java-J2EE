package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students_tbl")
public class Student extends BaseEntity{
	//studentId,name,email + Course courseEnrolled
	@Column(length = 30)
	private String name;//eg of Basic Value Type : Optional annotation @Basic
	@Column(length = 30,unique = true)
	private String email;//eg of Basic Value Type : @Basic
	@ManyToOne(fetch = FetchType.LAZY)//many to one multiplicity : mandatory (default fetch type=EAGER)
	@JoinColumn(name = "course_id") //specifying FK col name : optional BUT reco
	private Course courseEnrolled;
	//one to one uni dir association between Student (entity) -----> AdharCard (value)
	@Embedded //optional , added only for clarity
	private AdharCard card;//Composite Value Type : single
	//one to many uni dir asso between Student(Ent) ---> Edu Qual.(Composite val type)
	@ElementCollection //mandatory : to specify collection of composite val type (embeddable)
	@CollectionTable(name = "student_qualifications",joinColumns = @JoinColumn(name="sid"))
	private List<EducationalQualification> qualifications=new ArrayList<>();
	//one to many uni dir asso between Student(Ent) --->Hobbies(String : basic value) 
	@ElementCollection
	@CollectionTable(name="student_hobbies",joinColumns = @JoinColumn(name="sid"))
	@Column(length = 100)
	private List<String> hobbies=new ArrayList<>();
	
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
	
	public AdharCard getCard() {
		return card;
	}

	public void setCard(AdharCard card) {
		this.card = card;
	}
	

	public List<EducationalQualification> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<EducationalQualification> qualifications) {
		this.qualifications = qualifications;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Student "+getId()+" name=" + name + ", email=" + email ;//+ ", courseEnrolled=" + courseEnrolled + "]";
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
