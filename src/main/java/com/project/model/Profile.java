package com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String profiles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfile() {
		return profiles;
	}

	public void setProfile(String profile) {
		this.profiles = profile;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", profile=" + profiles + "]";
	}

}
