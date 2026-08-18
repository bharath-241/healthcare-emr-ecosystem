package com.healthcare.identity.repository;
import com.healthcare.identity.entity.RefreshToken; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>{Optional<RefreshToken> findByToken(String token); void deleteByEmail(String email);}
