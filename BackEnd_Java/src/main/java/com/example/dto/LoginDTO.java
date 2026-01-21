package com.example.dto;

<<<<<<< HEAD
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
=======
import lombok.Getter;
import lombok.Setter;


public class LoginDTO {
    private String email;
    private String password;
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
<<<<<<< HEAD
	public void setPassword(String password) {
		this.password = password;
	}
=======
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
}
