package med.voll.api.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.secret}")
  private String jwtSecret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
      return JWT.create()
          .withIssuer("VollMedClinic")
          .withSubject(user.getLogin())
          .withExpiresAt(getExpirationDate())
          .sign(algorithm);
    } catch (
        JWTCreationException exception){
      throw new RuntimeException("Error creating token", exception);
    }
  }

  public String getSubject(String token) {
    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
      verifier = JWT.require(algorithm)
          .withIssuer("VollMedClinic")
          .build()
          .verify(token.replace("Bearer ", "").trim());
      verifier.getSubject();
    } catch (JWTVerificationException exception) {
        throw new RuntimeException("Error verifying token");
    }
    return verifier.getSubject();
  }

  private Instant getExpirationDate() {
    return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC);
  }

}
