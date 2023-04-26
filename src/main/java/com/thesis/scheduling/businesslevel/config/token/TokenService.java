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

        String role = "Anonymous";
        if(member.getRole() == 0){
            role = "Newbie";
        }else if(member.getRole() == 1){
            role = "Teacher";
        }else if(member.getRole() == 2){
            role = "Staff";
        }else if(member.getRole() == 3){
            role = "Admin";
        }
		
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", member.getMemberId())
                .withClaim("role", role)
                .withClaim("name", member.getThFirstName() + "   "+ member.getThLastName() )
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
