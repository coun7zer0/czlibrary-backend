package cz.library.store.security.infrastructure.service.jwt;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService implements TokenProvider {

  private final SecretKey apiSecret;

  public JwtService(String apiSecret) {
    this.apiSecret = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public String generateToken(User user, String username) {
    return Jwts.builder()
        .issuer("cz.library.store")
        .subject(username)
        .expiration(generateExpirationDate())
        .claim("user_id", user.getId())
        .signWith(apiSecret)
        .compact();
  }

  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(apiSecret).build().parseSignedClaims(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }

  @Override
  public String getSubject(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().verifyWith(apiSecret)
          .build()
          .parseSignedClaims(token);
      return claims.getPayload().getSubject();
    } catch (JwtException e) {
      return null;
    }
  }

  @Override
  public Long getUserIdFromJWT(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().verifyWith(apiSecret)
          .build()
          .parseSignedClaims(token);
      return Long.valueOf(claims.getPayload().get("user_id").toString());
    } catch (JwtException e) {
      return null;
    }
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2));
  }

}
