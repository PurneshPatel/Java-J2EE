package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //mandatory
public class AdharCard {

//	Data members : card number , creation date , location
	@Column(name="card_number",unique = true,length = 30)
	private String cardNumber;
	@Column(name="created_on")
	private LocalDate createdOn;
	@Column(length = 50)
	private String location;

	public AdharCard() {
		// TODO Auto-generated constructor stub
	}

	public AdharCard(String cardNumber, LocalDate createdOn, String location) {
		super();
		this.cardNumber = cardNumber;
		this.createdOn = createdOn;
		this.location = location;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "AdharCard [cardNumber=" + cardNumber + ", createdOn=" + createdOn + ", location=" + location + "]";
	}

}
