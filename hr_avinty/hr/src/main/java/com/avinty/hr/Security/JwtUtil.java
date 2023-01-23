package com.avinty.hr.Security;

import com.avinty.hr.Model.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil{





    private static String Secret = "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b";

    private static Integer ValidDays = 1;

    public String generateJwt(Optional<User> optionalUser) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Integer userId = optionalUser.get().getId();
        String role = optionalUser.get().getRole();
        String username = optionalUser.get().getUsername();


        Instant now = Instant.now();

        String token = Jwts.builder()
                .setIssuer("Mate")
                .claim("id", userId)
                .claim("username", username )
                .claim("role", role)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ValidDays, ChronoUnit.DAYS)))
                .signWith(
                        signatureAlgorithm,
                        signingKey
                )
                .compact();
        return "Bearer " +token;

    }
    //enum

    public boolean validateJwt(String token) {

        try{

            String cleanToken = token.split(" ")[1];
            Jwts.parser().setSigningKey(Secret).parseClaimsJws(cleanToken);

            String[] parts = cleanToken.split("\\.");
            JSONObject data = new JSONObject(decode(parts[1]));

            if (data.getLong("exp") > (System.currentTimeMillis() / 1000)) {
                return true;
            };
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }




    }

    public boolean roleCheck(String roleName, String token) {
        try {
            String cleanToken = token.split(" ")[1];
            String[] parts = cleanToken.split("\\.");
            JSONObject header = new JSONObject(decode(parts[0]));
            JSONObject data = new JSONObject(decode(parts[1]));
            String signature = decode(parts[2]);
            if (data.getString("role").equals(roleName)) {
                return true;
            };
            return false;
        }catch (Exception ex){
            return false;
        }


    }

    public String getRoleFromJwt(String token) {
        String cleanToken = token.split(" ")[1];
        String[] parts = cleanToken.split("\\.");
        JSONObject data = new JSONObject(decode(parts[1]));
        return data.getString("role");

    }

    public Integer getIdFromJwt(String token) {
        String cleanToken = token.split(" ")[1];
        String[] parts = cleanToken.split("\\.");
        JSONObject data = new JSONObject(decode(parts[1]));
        return data.getInt("id");

    }

    public String getUsernameFromJwt(String token) {
        String cleanToken = token.split(" ")[1];
        String[] parts = cleanToken.split("\\.");
        JSONObject data = new JSONObject(decode(parts[1]));
        return data.getString("username");

    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
