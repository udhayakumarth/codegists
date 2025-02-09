package dev.udhayakumar.codegists.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login/google")
    public ResponseEntity<?> googleAuth(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(googleAuthService.getGoogleAuthUrl()));
        logger.info(googleAuthService.getGoogleAuthUrl());
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).headers(httpHeaders).body("");
    }

    @GetMapping("login/google/callback")
    public ResponseEntity<?> googleCallback(@RequestParam("code") String code) {
        return ResponseEntity.status(HttpStatus.OK).body(googleAuthService.authenticateWithGoogle(code));
    }
}
