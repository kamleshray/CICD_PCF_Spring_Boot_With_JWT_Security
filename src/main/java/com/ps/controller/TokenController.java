package com.ps.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.model.JwtUser;
import com.ps.util.JwtUtil;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/token")
@CrossOrigin
@Api(tags = "1- Token-Endpoint", description = "Token Generate")
public class TokenController {

    private JwtUtil jwtGenerator;

    public TokenController(JwtUtil jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/getNormalUserToken")
    public String generate(String yourName, int yearOfBirth) {
        return jwtGenerator.generate(new JwtUser(yearOfBirth, yourName, "USER"));

    }
    
    @GetMapping("/getAdminToken")
    public String getSampleTokenRequest(){
		return jwtGenerator.generate(new JwtUser(1990, "Dhananjaya", "ADMIN"));
    }
}
