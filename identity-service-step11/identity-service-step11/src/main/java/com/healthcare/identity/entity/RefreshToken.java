package com.healthcare.identity.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="refresh_tokens")
public class RefreshToken {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=500) private String token;
 @Column(nullable=false) private String email;
 @Column(nullable=false) private LocalDateTime expiryDate;
 public RefreshToken(){} public RefreshToken(String t,String e,LocalDateTime x){token=t;email=e;expiryDate=x;}
 public Long getId(){return id;} public String getToken(){return token;} public String getEmail(){return email;} public LocalDateTime getExpiryDate(){return expiryDate;}
}
