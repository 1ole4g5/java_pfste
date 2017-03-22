package ru.stqa.jpfste.addressbook;

public class GroupData {
	public String name;
	public String header;
	public String footer;

	public GroupData(String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
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
}