package com.healthcare.identity.dto;
import jakarta.validation.constraints.*;
public class RegisterRequest {
 @NotBlank private String name; @NotBlank @Email private String email; @NotBlank private String password; private String role;
 public String getName(){return name;} public void setName(String v){name=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;} public String getRole(){return role;} public void setRole(String v){role=v;}
}
