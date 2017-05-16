package ru.stqa.jpfste.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Column(name = "nickname")
    private String nickName;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "email")
    @Type(type = "text")
    private String eMail_1;

    @Column(name = "email2")
    @Type(type = "text")
    private String eMail_2;

    @Column(name = "email3")
    @Type(type = "text")
    private String eMail_3;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Transient
    private String allEMails;

    @Transient
    private String allDataDetailsForm;

    @Transient
    private String allDataEditForm;

    @Transient
//	@Column(name = "photo")
//	@Type(type = "text")
    private String photo;

    //	public File getPhoto() {
//		return new File(photo);
//	}
//
//	public ContactData withPhoto(File photo) {
//		this.photo = photo.getPath();
//		return this;
//	}
    @Column(name = "domain_id")
    @Type(type = "int")
    private int domain_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public ContactData withAllDataEditForm(String allDataEditForm) {
        this.allDataEditForm = allDataEditForm;
        return this;
    }

    public ContactData withDomainId(int domain_id) {
        this.domain_id = domain_id;
        return this;
    }

    public ContactData withAllDataDetailsForm(String allDataDetailsForm) {
        this.allDataDetailsForm = allDataDetailsForm;
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

    public String getAllDataDetailsForm() {
        return allDataDetailsForm;
    }

    public String getAllDataEditForm() {
        return allDataEditForm;
    }

    public int getDomain_id() {
        return domain_id;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "ContactData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
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
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
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

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}