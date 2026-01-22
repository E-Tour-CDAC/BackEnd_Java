package com.example.controller;

import com.example.dto.CustomerDTO;
import com.example.dto.LoginDTO;
import com.example.model.CustomerModel;
import com.example.services.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class    AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    // PUBLIC REGISTER
    @PostMapping("/register")
    public ResponseEntity<CustomerModel> register(@Valid @RequestBody CustomerDTO dto) {
        logger.info("Attempting to register user");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(dto));
    }

    // PUBLIC LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO dto) {
        logger.info("Attempting to logging user");
        String token = authService.login(dto);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    // GOOGLE
//    @PostMapping("/google")
//    public ResponseEntity<Map<String, String>> googleLogin(
//            @RequestBody GoogleLoginRequestDTO request) {
//
//        String token = authService.googleLogin(request.getIdToken());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("token", token);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @org.springframework.web.bind.annotation.GetMapping("/success")
//    public ResponseEntity<String> loginSuccess(
//            @org.springframework.web.bind.annotation.RequestParam("token") String token) {
//        return ResponseEntity.ok("Login Successful! Your JWT Token is: " + token);
//    }

   // @PostMapping("/google")
    // public ResponseEntity<Map<String, String>> googleLogin(
    //         @RequestBody Map<String, String> request
    // ) {
    //     String token = authService.googleLogin(request.get("idToken"));
    //
    //     return ResponseEntity.ok(
    //             Map.of(
    //                     "token", token,
    //                     "type", "Bearer"
    //             )
    //     );
    // }


}
