package com.example.dto;

import org.jspecify.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String password;
	public @Nullable CharSequence getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
