package com.SmartContact_Manager.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_contacts")
public class Contacts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String name;
	private String secondname;
	private String mail;
	private String work;
	private String phone;
	private String image;
	@Column(length = 400)
	private String description;
	
	@ManyToOne
	private Users user;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Contacts(int cId, String name, String secondname, String mail, String work, String phone, String image,
			String description, Users user) {
		super();
		this.cId = cId;
		this.name = name;
		this.secondname = secondname;
		this.mail = mail;
		this.work = work;
		this.phone = phone;
		this.image = image;
		this.description = description;
		this.user = user;
	}

	public Contacts() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.cId == ((Contacts) obj).getcId();
	}
	

/*	@Override
	public String toString() {
		return "Contacts [cId=" + cId + ", name=" + name + ", secondname=" + secondname + ", mail=" + mail + ", work="
				+ work + ", phone=" + phone + ", image=" + image + ", description=" + description + ", user=" + user
				+ "]";
	}
	*/
	
	
	
	
}
