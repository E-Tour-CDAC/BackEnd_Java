package com.example.model;

import com.example.entities.BookingHeader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {

    private Integer id;

    private String email;

    private String phone;

    private String address;

    private String firstName;

    private String lastName;

    private String password;

    private String customerRole;

    private String authProvider;

    private Boolean profileCompleted;

    private Set<BookingHeader> bookingHeaders;
}
