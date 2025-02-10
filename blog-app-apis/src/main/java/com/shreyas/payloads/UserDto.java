package com.shreyas.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	@NotBlank(message = "name can't be blanck")
	private String name;
	private Integer uid;
	@Email(message = "mail is not in proper format")
	private String email;
	@Size(min = 4, max = 12)
	@NotBlank(message = "can't be blanck")
	private String pass;
	@NotBlank(message = "add something in about, can't be blanck")
	private String about;
	private String role;
}
