package com.lambdaschool.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {
    @Autowired
    private TokenStore tokenStore;

    @GetMapping(value = {"/oath/revoke-token", "logout",}, produces = "application/json")
    public ResponseEntity<?> logoutSelf(HttpServletRequest request)
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null)
        {
            String TokenValue = authHeader.replace("Bearer", "").trim();
        }
        OAuth2AccessToken accessToken = TokenStore.readAccessToken(tokenValue)
                tokenStore.removeAccessToken(accessToken);
    }           return new ResponseEntity <> (HttpStatus.OK)
}
