package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.AuthUserData;
import med.voll.api.domain.users.User;
import med.voll.api.infrastructure.security.JwtData;
import med.voll.api.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired(required = true)
  private TokenService tokenService;

  @PostMapping()
  public ResponseEntity<JwtData> authenticateUser(@RequestBody @Valid AuthUserData authUserData) {
    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(authUserData.login(), authUserData.password());
    Authentication authenticatedUser = authenticationManager.authenticate(authenticationToken);
    String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
    return ResponseEntity.ok(new JwtData(jwtToken));
  }

}
