/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import java.time.Instant;
import java.util.Base64;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author David
 */
@Service
public class JweService {

    private static final long TTL_SECONDS = 24 * 60 * 60;
    private final byte[] secretKey;

    public JweService(@Value("${security.jwe.secret}") String secretBase64) {
        this.secretKey = Base64.getDecoder().decode(secretBase64);
    }

    public String createToken(String user) {
        long now = Instant.now().getEpochSecond();

        long exp = now + TTL_SECONDS;

        Map<String, Object> payloadMap = new HashMap<>();

        payloadMap.put("sub", user);
        payloadMap.put("iat", now);
        payloadMap.put("exp", exp);

        JWEHeader header = new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM)
                .contentType("JWT")
                .build();

        Payload payload = new Payload(payloadMap);

        JWEObject jweObject = new JWEObject(header, payload);

        try{
            jweObject.encrypt(new DirectEncrypter(secretKey));
        }
        catch(JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }

        return jweObject.serialize();
    }

    public String validateToken(String token) throws Exception {
        JWEObject jweObject = JWEObject.parse(token);

        jweObject.decrypt(new DirectDecrypter(secretKey));

        Map<String, Object> json = JSONObjectUtils.parse(jweObject.getPayload().toString());

        long now = Instant.now().getEpochSecond();

        Number exp = (Number) json.get("exp");
        
        if (exp == null || now > exp.longValue())
            throw new RuntimeException("Token expirado");
        
        return (String) json.get("sub");
    }
}
