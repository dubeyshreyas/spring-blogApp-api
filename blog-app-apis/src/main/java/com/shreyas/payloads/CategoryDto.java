package com.shreyas.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    Integer cat_id;
    @NotBlank(message = "category name can't be blank")
	String cat_name;
    @Size(min = 5, max = 20)
	String cat_desc;
}
