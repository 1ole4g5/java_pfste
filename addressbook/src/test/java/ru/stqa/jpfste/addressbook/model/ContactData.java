package ru.stqa.jpfste.addressbook.model;

public class ContactData {
	private int id;
	private String firstName;
	private String lastName;
	private String nickName;
	private String address;
	private String eMail;
	private String group;

	public ContactData(String firstName, String lastName, String nickName, String address, String eMail, String group) {
		this.id = Integer.MIN_VALUE;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.address = address;
		this.eMail = eMail;
		this.group = group;
	}
	
	public ContactData(int id, String firstName, String lastName, String nickName, String address, String eMail, String group) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.address = address;
		this.eMail = eMail;
		this.group = group;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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