package com.tunaPasta06.entity;

import java.util.HashMap;
import java.util.Map;

public class Pet {
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String PHOTO = "photo";

	private String name;
	private String phone;
	private int photo;

	public Pet() {
	}

	public Pet(String name, String phone, int photo) {
		this.name = name;
		this.phone = phone;
		this.photo = photo;
	}

	public static Map<String, Object> toMap(String name, String phone, int photo) {
		Map<String, Object> map = new HashMap();
		map.put(NAME, name);
		map.put(PHONE, phone);
		map.put(PHOTO, photo);
		return map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	public String toString() {
		return name;
	}

}
