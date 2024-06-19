package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.User;
import com.bytekeeper.backend.service.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimAccessor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/*
/oauth2/callback se apeleaza dupa ce s-a logat userul cu contul google si ii primesc tokenurile
in frontend trebuie sa faceti
 */
@RestController
public class OAuth2Controller {
    private final UserService userService;

    public OAuth2Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/oauth2/callback", produces = "application/json")
    public ResponseEntity<String> handleGoogleCallback(@RequestParam("code") String code) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", "140352902475-5hgpbh9obko5f7fd5h51sebbo830olg4.apps.googleusercontent.com");
        map.add("client_secret", "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");
        map.add("redirect_uri", "http://localhost:8080/oauth2/callback");
        map.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token",
                request,
                Map.class
        );

        String accessToken = response.getBody().get("access_token").toString();
        String idToken = response.getBody().get("id_token").toString();

        SignedJWT signedJWT = SignedJWT.parse(idToken);
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
        String name = claims.getStringClaim("name");
        String email = claims.getStringClaim("email");
        String token = claims.getSubject();
        userService.addUser(new User(name, email, token));

        return ResponseEntity.ok(token);  // returneaza tokenul userului pentru a-l salva in session storage
    }
}
