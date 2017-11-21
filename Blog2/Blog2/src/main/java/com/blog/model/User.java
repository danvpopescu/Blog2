package com.blog.model;

public class User {

    private Long idUser;

    private String codeUser;

    private String firstName;

    private String lastName;

    private String codePermission;

    private String userName;

    private String password;

    public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getCodeUser() {
		return codeUser;
	}
	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCodePermission() {
		return codePermission;
	}
	public void setCodePermission(String codePermission) {
		this.codePermission = codePermission;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof User) {
			return this.idUser.equals(((User) obj).idUser);
		}
		return false;
	}

	public static String formatName(String lastName, String firstName) {

		String name = "";
		if (lastName != null && !lastName.equals("")) {
			name = lastName;
		}
		if (firstName != null && !firstName.equals("")) {
			if (name.length() > 0) {
				name += ", ";
			}
			name += firstName;
		}

	    return name;
	}

	public static String formatNameFirstLast(String firstName, String lastName) {
		String name = "";
		if (firstName != null && !firstName.equals("")) {
			name = firstName;
		}
		if (lastName != null && !lastName.equals("")) {
			if (name.length() > 0) {
				name += ", ";
			}
			name += lastName;
		}
		return name;
	}

	public static String formatShortName(String lastName, String firstName) {
		String name = "";
		if (firstName != null && !firstName.equals("")) {
			name = firstName.substring(0, 1);
		}
		if (lastName != null && !lastName.equals("")) {
			name += lastName;
		}
		return name.toLowerCase();    
	}
	
}
