package com.thesis.scheduling.businesslevel.config.token;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.thesis.scheduling.modellevel.entity.Member;

@Service
public class TokenService {

    private String secret = "PorpyLazy";

    private String issuer = "BackEnd";    
    
    public TokenService() {

	}

	public String tokenize(Member member) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 360);
        Date expiresAt = calendar.getTime();
		
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", member.getMemberId())
                .withClaim("role", "ROLE_TEACHER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }
	
    public DecodedJWT verify(String token)  {
    	System.out.print("verify token : ");
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            System.out.println("Pass");
            return verifier.verify(token);

        } catch (Exception e) {
        	System.out.println(e);
            return null;
        }
    }
    
    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
	
}
