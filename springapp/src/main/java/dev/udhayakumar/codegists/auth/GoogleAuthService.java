package dev.udhayakumar.codegists.auth;

import dev.udhayakumar.codegists.user.User;
import dev.udhayakumar.codegists.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service

public class GoogleAuthService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    private static final String CLIENT_ID = AuthConstants.CLIENT_ID;
    private static final String CLIENT_SECRET = AuthConstants.CLIENT_SECRET;
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/login/google/callback";
    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";


    public String getGoogleAuthUrl() {
        return GOOGLE_AUTH_URL + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=email";
    }

    public String authenticateWithGoogle(String code) {
        // Exchange authorization code for access token
        String accessToken = getAccessToken(code);

        // Fetch user info from Google
        User googleUser = getUserInfo(accessToken);

        // Store user if not exists
        User user = userRepository.findByEmail(googleUser.getEmail())
                .orElseGet(() -> userRepository.save(new User(googleUser.getEmail(), googleUser.getName(), googleUser.getPicture())));

        // Generate JWT
        return jwtService.generateToken(user);
    }

    private String getAccessToken(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("code", code);
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("redirect_uri", REDIRECT_URI);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(GOOGLE_TOKEN_URL, request, Map.class);

        return (String) response.getBody().get("access_token");
    }

    private User getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<User> response = restTemplate.exchange(GOOGLE_USERINFO_URL, HttpMethod.GET, request, User.class);

        return response.getBody();
    }
}
