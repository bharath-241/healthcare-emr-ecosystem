package com.healthcare.identity.dto;
public class LoginResponse {
 private String message,accessToken,refreshToken;
 public LoginResponse(){} public LoginResponse(String m,String a,String r){message=m;accessToken=a;refreshToken=r;}
 public String getMessage(){return message;} public String getAccessToken(){return accessToken;} public String getRefreshToken(){return refreshToken;}
}
