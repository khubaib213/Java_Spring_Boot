package com.example.myJar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

    @NotBlank(message = "Message is required")
    @Size(min = 2,max = 50,message = "Name characters should not be greater than 50")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be correct email address")
    private String email;
    public CreateUserDTO(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
