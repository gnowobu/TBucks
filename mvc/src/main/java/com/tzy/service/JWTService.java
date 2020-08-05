package com.tzy.service;

import com.tzy.model.Customer;
import com.tzy.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Set;

@Service
public class JWTService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String SECRET_KEY = System.getProperty("key"); //replace it with System.getProperty();
    private final String ISSUER = "com.tzy";
    private final long EXPIRATION_TIME = 86400 * 1000;




    public String generateToken(Customer customer){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(customer.getId()));
        claims.setSubject(customer.getName());//put username into token
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);


        Set<Role> roles = customer.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        //String allowedResource = roles.stream().map(Role::getAllowedResource).collect(Collectors.joining(","));


        for (Role role : roles) {
            if (role.isAllowedRead()) allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate()) allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate()) allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete()) allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }

        claims.put("allowedReadResources", allowedReadResources.replaceAll(",$", "")); //replace the last comma
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(",$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$", ""));


        return builder.compact();
    }


    public Claims decryptJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        logger.debug("Claims: " + claims.toString());
        return claims;
    }
}

