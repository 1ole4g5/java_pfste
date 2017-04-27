package ru.stqa.jpfste.addressbook.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {

	@XStreamOmitField
	@Id
	@Column(name = "group_id")
	private int id = Integer.MAX_VALUE;
	
	@Expose
	@Column(name = "group_name")
	private String name;
	
	@Expose
	@Column(name = "group_header")
	@Type(type = "text")
	private String header;
	
	@Expose
	@Column(name = "group_footer")
	@Type(type = "text")
	private String footer;
	
	@Column(name = "domain_id")
	@Type(type = "int")
	private int domain_id;
	
	@ManyToMany(mappedBy = "groups")
	private Set<ContactData> contacts = new HashSet<ContactData>();
		
//	public Set<ContactData> getContacts() {
//		return contacts;
//	}
	
	public Contacts getContacts() {
		return new Contacts(contacts);
	}

	public int getDomainId() {
		return domain_id;
	}
	
	public GroupData withDomainId(int domain_id) {
		this.domain_id = domain_id;
		return this;
	}

	public int getId() {
		return id;
	}

	public GroupData withId(int id) {
		this.id = id;
		return this;
	}

	public GroupData withName(String name) {
		this.name = name;
		return this;
	}

	public GroupData withHeader(String header) {
		this.header = header;
		return this;
	}

	public GroupData withFooter(String footer) {
		this.footer = footer;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	@Override
	public String toString() {
		return "GroupData [id=" + id + ", name=" + name + ", header=" + header + ", footer=" + footer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + domain_id;
		result = prime * result + ((footer == null) ? 0 : footer.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		GroupData other = (GroupData) obj;
		if (domain_id != other.domain_id)
			return false;
		if (footer == null) {
			if (other.footer != null)
				return false;
		} else if (!footer.equals(other.footer))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}