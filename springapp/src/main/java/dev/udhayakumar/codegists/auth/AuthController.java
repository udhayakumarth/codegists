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

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login/google")
    public ResponseEntity<?> googleAuth(){
        try{
            URI uri = URI.create(googleAuthService.getGoogleAuthUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            log.info("Redirect URI: {}", uri);
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).headers(httpHeaders).body("");
        } catch (Exception e) {
            log.error("Error occurred for Google Login: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("login/google/callback")
    public ResponseEntity<?> googleCallback(@RequestParam("code") String code) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(googleAuthService.authenticateWithGoogle(code));
        } catch (Exception e) {
            log.error("Error occurred for Google Login Callback: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
