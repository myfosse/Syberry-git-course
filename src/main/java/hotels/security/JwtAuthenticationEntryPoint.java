package hotels.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
  private static final String ERROR_MESSAGE = "{ \"error\": \"%s\" }";
  private static final String EXPIRED_TOKEN_MESSAGE = "Token is expired";
  private static final String INVALID_TOKEN_MESSAGE = "Token is invalid";

  @Override
  public void commence(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException {

    httpServletResponse.setContentType("application/json");

    if (httpServletRequest.getAttribute("expiredToken") != null) {
      printError(EXPIRED_TOKEN_MESSAGE, httpServletResponse);
    } else if (httpServletRequest.getAttribute("invalidToken") != null) {
      printError(INVALID_TOKEN_MESSAGE, httpServletResponse);
    } else {
      printError(e.getMessage(), httpServletResponse);
    }
  }

  private void printError(String message, HttpServletResponse response) throws IOException {
    response.setStatus(401);
    response.getOutputStream().println(String.format(ERROR_MESSAGE, message));
    logger.error("Responding with unauthorized error. Message - {}", message);
  }
}
