package com.chatop.chatop.service;

import com.chatop.chatop.model.UserModel;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
@Service
public class JWTService {

    private JwtEncoder jwtEncoder;
    public JWTService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(UserModel userModel) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now) // Moment de création
                .expiresAt(now.plus(1, ChronoUnit.DAYS)) // Durée dans le temps
                .subject(userModel.getEmail()) // Le subject
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    //Renvoie le subject dans le token
    public StringBuffer getUsernamePasswordLoginInfo(Principal user)
    {
        StringBuffer usernameInfo = new StringBuffer();

        JwtAuthenticationToken token = (JwtAuthenticationToken) user;
        if(token.isAuthenticated()){
            usernameInfo.append(token.getName());
        }
        else{
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

}
