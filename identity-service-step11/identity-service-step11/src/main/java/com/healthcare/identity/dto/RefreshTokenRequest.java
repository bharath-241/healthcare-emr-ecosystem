package com.healthcare.identity.dto;
import jakarta.validation.constraints.NotBlank;
public class RefreshTokenRequest {
 @NotBlank private String refreshToken;
 public String getRefreshToken(){return refreshToken;} public void setRefreshToken(String v){refreshToken=v;}
}
