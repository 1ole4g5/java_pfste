package ru.stqa.jpfste.addressbook.model;

public class ContactData {
	public String firstName;
	public String lastName;
	public String nickName;
	public String address;
	public String eMail;
	private String group;

	public String getGroup() {
		return group;
	}

	public ContactData(String firstName, String lastName, String nickName, String address, String eMail, String group) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.address = address;
		this.eMail = eMail;
		this.group = group;
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