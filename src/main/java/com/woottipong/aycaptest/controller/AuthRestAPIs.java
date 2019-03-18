package com.woottipong.aycaptest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woottipong.aycaptest.message.request.LoginForm;
import com.woottipong.aycaptest.message.request.SignUpForm;
import com.woottipong.aycaptest.message.response.JwtResponse;
import com.woottipong.aycaptest.model.MemberType;
import com.woottipong.aycaptest.model.MemberTypeName;
import com.woottipong.aycaptest.model.User;
import com.woottipong.aycaptest.repository.MemberTypeRepository;
import com.woottipong.aycaptest.repository.UserRepository;
import com.woottipong.aycaptest.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberTypeRepository memberTypeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity<String>("Fail -> Phone is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
        if(signUpRequest.getSalary().doubleValue() < 15000) {
        	return new ResponseEntity<String>("Fail -> Salary is less than 15,000!",
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(); //new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setAddress(signUpRequest.getAddress());
        user.setPhone(signUpRequest.getPhone());
        user.setSalary(signUpRequest.getSalary());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String refCode = sdf.format(new Date()) + signUpRequest.getPhone().substring(signUpRequest.getPhone().length() - 4);
        user.setReferenceCode(refCode);
        
        MemberType memberType;
        double salary = signUpRequest.getSalary().doubleValue();
        
        if(salary > 50000) {
        	memberType = memberTypeRepository.findByTypeName(MemberTypeName.PLATINUM).get();
        } else if(salary >= 30000 && salary <= 50000) {
        	memberType = memberTypeRepository.findByTypeName(MemberTypeName.GOLD).get();
        } else if(salary >= 15000 && salary < 30000) {
        	memberType = memberTypeRepository.findByTypeName(MemberTypeName.SILVER).get();
        } else {
        	return new ResponseEntity<String>("Fail -> Salary is less than 15,000!", HttpStatus.BAD_REQUEST);
        }
        
        Set<MemberType> userTypes = new HashSet<>();
        userTypes.add(memberType);
        user.setMemberType(userTypes);
        userRepository.save(user);
        return ResponseEntity.ok().body("{\"success\": 1}");
    }
}
