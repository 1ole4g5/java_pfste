package ru.stqa.jpfste.addressbook;

public class ContactData {
	public String firstName;
	public String lastName;
	public String nickName;
	public String address;
	public String eMail;

	public ContactData(String firstName, String lastName, String nickName, String address, String eMail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.address = address;
		this.eMail = eMail;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getAddress() {
		return address;
	}

	public String getEMail() {
		return eMail;
	}
}