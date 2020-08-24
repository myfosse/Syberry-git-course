package hotels.security;

import hotels.models.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @Value("${app.refreshTokenExpirationMs}")
  private int refreshTokenExpirationMs;

  public String generateToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

    return Jwts.builder()
            .setSubject(Long.toString(userPrincipal.getId()))
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  public String refreshToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

    return Jwts.builder()
            .setSubject(Long.toString(user.getId()))
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

  public String generateRefreshToken(boolean rememberMe) {

    Date now = new Date();

    Date expiryDate =
        rememberMe
            ? new Date(now.getTime() + refreshTokenExpirationMs * 14)
            : new Date(now.getTime() + refreshTokenExpirationMs);

    return Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public Long getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

    return Long.parseLong(claims.getSubject());
  }

  public boolean validateToken(String authToken, HttpServletRequest request) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      request.setAttribute("invalidToken", ex.getMessage());
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      request.setAttribute("expiredToken", ex.getMessage());
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }
}
