package com.SmartContact_Manager.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users_info")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	private String role;
	private String imageurl;
	@Column(length=400)
	private String about;
	private boolean Embaded;
	
	//
	@OneToMany( mappedBy = "user", cascade= CascadeType.ALL,orphanRemoval = true)
	private List<Contacts> contacts = new ArrayList<>();

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String name, String email, String password, String role, String imageurl, String about,
			boolean embaded, List<Contacts> contacts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.imageurl = imageurl;
		this.about = about;
		Embaded = embaded;
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isEmbaded() {
		return Embaded;
	}

	public void setEmbaded(boolean embaded) {
		Embaded = embaded;
	}

	public List<Contacts> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", imageurl=" + imageurl + ", about=" + about + ", Embaded=" + Embaded + ", contacts=" + contacts
				+ "]";
	}
	
	
	

}
