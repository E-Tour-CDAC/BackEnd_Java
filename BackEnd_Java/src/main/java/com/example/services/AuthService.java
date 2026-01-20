package com.example.services;

import com.example.dto.CustomerMasterDTO;
import com.example.dto.LoginDTO;
import com.example.entities.CustomerMaster;

import com.example.enums.AuthProvider;

import com.example.enums.CustomerRole;
import com.example.mapper.CustomerMasterMapper;
import com.example.model.CustomerMasterModel;
import com.example.repositories.CustomerMasterRepository;
import com.example.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private CustomerMasterRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerMasterMapper mapper;


    //REGISTER (LOCAL)
    public CustomerMasterModel register(CustomerMasterDTO dto) {
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

        CustomerMasterModel model = mapper.toModel(saved);
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


}
