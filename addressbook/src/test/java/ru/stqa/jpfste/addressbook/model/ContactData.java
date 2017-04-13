package ru.stqa.jpfste.addressbook.model;

public class ContactData {

	private int id = Integer.MIN_VALUE;
	private String firstName;
	private String lastName;
	private String nickName;
	private String address;
	private String eMail;
	private String group;

	public int getId() {
		return id;
	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public ContactData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ContactData withNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactData withMail(String eMail) {
		this.eMail = eMail;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public String getGroup() {
		return group;
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

	@Override
	public String toString() {
		return "ContactData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		ContactData other = (ContactData) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}