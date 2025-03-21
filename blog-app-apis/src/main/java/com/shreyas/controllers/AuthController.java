package com.shreyas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.config.JwtTokenHelper;
import com.shreyas.model.Role;
import com.shreyas.payloads.UserDto;
import com.shreyas.repo.UserRepo;
import com.shreyas.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService service;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenHelper jwtTokenHelper;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
		user.setPass(passwordEncoder.encode(user.getPass()));
		UserDto createdUser = service.createUser(user);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) throws Exception{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtTokenHelper.generateToken(userDetails.getUsername());
	}
	
	
	static class LoginRequest {
		private String username;
        private String password;
        private Role role;

        // Constructors
        public LoginRequest() {}

        public LoginRequest(String username, String password, Role role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        // Getters and Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}
        
	    
	    
	}
}
