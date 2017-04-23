package ru.stqa.jpfste.addressbook.model;

import java.io.File;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("contact")
public class ContactData {

	@XStreamOmitField
	private int id = Integer.MAX_VALUE;
	@Expose
	private String firstName;
	@Expose
	private String lastName;
	private String nickName;
	@Expose
	private String address;
	private String eMail_1;
	private String eMail_2;
	private String eMail_3;
	private String group;
	private String homePhone;
	@Expose
	private String mobilePhone;
	private String workPhone;
	private String allPhones;
	private String allEMails;
	private String allDetails;
	private File photo;

	public File getPhoto() {
		return photo;
	}

	public ContactData withPhoto(File photo) {
		this.photo = photo;
		return this;
	}

	public ContactData withAllDetails(String allDetails) {
		this.allDetails = allDetails;
		return this;
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

	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactData withFirstEMail(String eMail_1) {
		this.eMail_1 = eMail_1;
		return this;
	}

	public ContactData withSecondEMail(String eMail_2) {
		this.eMail_2 = eMail_2;
		return this;
	}

	public ContactData withThirdEMail(String eMail_3) {
		this.eMail_3 = eMail_3;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	public ContactData withAllEMails(String allEMails) {
		this.allEMails = allEMails;
		return this;
	}

	public int getId() {
		return id;
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

	public String getFirstEMail() {
		return eMail_1;
	}

	public String getSecondEMail() {
		return eMail_2;
	}

	public String getThirdEMail() {
		return eMail_3;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getAllEMails() {
		return allEMails;
	}

	public String getAllDetails() {
		return allDetails;
	}

	@Override
	public String toString() {
		return "ContactData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nickName="
		        + nickName + ", address=" + address + ", eMail_1=" + eMail_1 + ", eMail_2=" + eMail_2 + ", eMail_3="
		        + eMail_3 + ", group=" + group + ", homePhone=" + homePhone + ", mobilePhone=" + mobilePhone
		        + ", workPhone=" + workPhone + ", allPhones=" + allPhones + ", allEMails=" + allEMails + ", allDetails="
		        + allDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((allDetails == null) ? 0 : allDetails.hashCode());
		result = prime * result + ((allEMails == null) ? 0 : allEMails.hashCode());
		result = prime * result + ((allPhones == null) ? 0 : allPhones.hashCode());
		result = prime * result + ((eMail_1 == null) ? 0 : eMail_1.hashCode());
		result = prime * result + ((eMail_2 == null) ? 0 : eMail_2.hashCode());
		result = prime * result + ((eMail_3 == null) ? 0 : eMail_3.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((workPhone == null) ? 0 : workPhone.hashCode());
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (allDetails == null) {
			if (other.allDetails != null)
				return false;
		} else if (!allDetails.equals(other.allDetails))
			return false;
		if (allEMails == null) {
			if (other.allEMails != null)
				return false;
		} else if (!allEMails.equals(other.allEMails))
			return false;
		if (allPhones == null) {
			if (other.allPhones != null)
				return false;
		} else if (!allPhones.equals(other.allPhones))
			return false;
		if (eMail_1 == null) {
			if (other.eMail_1 != null)
				return false;
		} else if (!eMail_1.equals(other.eMail_1))
			return false;
		if (eMail_2 == null) {
			if (other.eMail_2 != null)
				return false;
		} else if (!eMail_2.equals(other.eMail_2))
			return false;
		if (eMail_3 == null) {
			if (other.eMail_3 != null)
				return false;
		} else if (!eMail_3.equals(other.eMail_3))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (homePhone == null) {
			if (other.homePhone != null)
				return false;
		} else if (!homePhone.equals(other.homePhone))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (workPhone == null) {
			if (other.workPhone != null)
				return false;
		} else if (!workPhone.equals(other.workPhone))
			return false;
		return true;
	}
}