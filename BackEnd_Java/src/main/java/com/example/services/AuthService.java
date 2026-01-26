package com.example.services;

import com.example.dto.CustomerDTO;
import com.example.dto.LoginDTO;
import com.example.entities.CustomerMaster;

import com.example.enums.AuthProvider;

import com.example.enums.CustomerRole;
import com.example.mapper.CustomerMapper;
import com.example.model.CustomerModel;
import com.example.repositories.CustomerRepository;
import com.example.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerMapper mapper;


    //REGISTER (LOCAL)
    public CustomerModel register(CustomerDTO dto) {
        logger.info("Attempting to register user with email: {}", dto.getEmail());

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            logger.warn("Registration failed: Email {} already exists", dto.getEmail());
            throw new RuntimeException("Email already registered");
        }

        CustomerMaster entity = new CustomerMaster();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setCustomerRole(CustomerRole.CUSTOMER);
        entity.setAuthProvider(AuthProvider.LOCAL);
        entity.setProfileCompleted(false);

        CustomerMaster saved = repository.save(entity);
        logger.info("User registered successfully: {}", saved.getEmail());

        CustomerModel model = mapper.toModel(saved);
        model.setPassword(null); // 🔒 never expose

        return model;
    }


    //LOGIN (LOCAL)
    public String login(LoginDTO dto) {

        CustomerMaster user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (user.getAuthProvider() != AuthProvider.LOCAL) {
            throw new RuntimeException("Please login using " + user.getAuthProvider());
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getCustomerRole().name());
    }

    public CustomerModel getCustomerProfile(String email) {
        CustomerMaster customer = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerModel model = mapper.toModel(customer);
        model.setPassword(null); // 🔒 never expose password

        return model;
    }


    //GOOGLE LOGIN
//    public String googleLogin(GoogleUserPayload payload) {
//
//        CustomerMaster user = repository.findByEmail(payload.getEmail())
//                .orElseGet(() -> {
//                    CustomerMaster u = new CustomerMaster();
//                    u.setEmail(payload.getEmail());
//                    u.setFirstName(payload.getFirstName());
//                    u.setLastName(payload.getLastName());
//                    u.setCustomerRole(CustomerRole.CUSTOMER);
//                    u.setAuthProvider(AuthProvider.GOOGLE);
//                    u.setProfileCompleted(true);
//                    u.setPassword(null);
//                    return repository.save(u);
//                });
//
//        return jwtUtil.generateToken(user.getEmail(), user.getCustomerRole().name());
//    }

    //update profile
    public CustomerModel updateCustomerProfile(String email, CustomerDTO dto) {

        CustomerMaster customer = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // updatable fields
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setProfileCompleted(true);

        // password optional
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        CustomerMaster saved = repository.save(customer);

        CustomerModel model = mapper.toModel(saved);
        model.setPassword(null); // never return password

        return model;
    }

}
