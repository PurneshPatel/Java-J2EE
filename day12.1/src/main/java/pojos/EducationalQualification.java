package pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//Student HAS-A Educational Qualifications
//type (enum), year , % marks
@Embeddable
public class EducationalQualification {
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EduType type;
	private int year;
	private double marks;
	public EducationalQualification() {
		// TODO Auto-generated constructor stub
	}
	public EducationalQualification(EduType type, int year, double marks) {
		super();
		this.type = type;
		this.year = year;
		this.marks = marks;
	}
	public EduType getType() {
		return type;
	}
	public void setType(EduType type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "EducationalQualification [type=" + type + ", year=" + year + ", marks=" + marks + "]";
	}
	

}
