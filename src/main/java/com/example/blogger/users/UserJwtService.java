package com.example.blogger.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class UserJwtService {
    private static final String SECRET="oadoa3942423948q2j3ed13je1asldnasnsalndl";
    public static final String CLAIM_USERNAME="username";

    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    String createJwtToken(String username)
    {
        return JWT.create().withClaim(CLAIM_USERNAME,username)
                        .withIssuedAt(new Date()) // TODD: add expiry date
                        .sign(algorithm);
    }


    String getUsernameFromJwtToken(String jwtToken)
    {
        return JWT.require(algorithm).build().verify(jwtToken).getClaim(CLAIM_USERNAME).asString();
    }

}
