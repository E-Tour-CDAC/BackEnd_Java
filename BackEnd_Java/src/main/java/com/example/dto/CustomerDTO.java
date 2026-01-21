package com.example.dto;

import com.example.entities.BookingHeader;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    private String address;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    private String password;

<<<<<<< HEAD
    public Integer getId() {
=======
    private String customerRole;

    private String authProvider;

    private Boolean profileCompleted;

    private Set<BookingHeader> bookingHeaders;

	public Integer getId() {
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public String getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(String authProvider) {
		this.authProvider = authProvider;
	}

	public Boolean getProfileCompleted() {
		return profileCompleted;
	}

	public void setProfileCompleted(Boolean profileCompleted) {
		this.profileCompleted = profileCompleted;
	}

	public Set<BookingHeader> getBookingHeaders() {
		return bookingHeaders;
	}

	public void setBookingHeaders(Set<BookingHeader> bookingHeaders) {
		this.bookingHeaders = bookingHeaders;
	}
<<<<<<< HEAD

	private String customerRole;

    private String authProvider;

    private Boolean profileCompleted;

    private Set<BookingHeader> bookingHeaders;
=======
    
    
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
}
