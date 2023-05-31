package sportinterest.securite;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sportinterest.user.User;
import sportinterest.user.UserService;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class JWTToken {

    @Value("${secretkey}")
    private String secretKey;
    @Autowired
    UserService userService;

    /**
     * Generate token à la mano
     * Pour aider à la compréhension
     * @param user
     * @return
     */
    private String generateTokenV1(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userid", user.getId());

        try {
            //Convert secretKey into PrivateKey
            byte[] keybytes = Base64.getDecoder().decode(secretKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keybytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            //Create header and claims
            String header = Base64.getUrlEncoder().encodeToString("{'alg':'RS256'}".getBytes(StandardCharsets.UTF_8));
            String claimsString = new JSONObject(claims).toString();

            // Concaténer le header et les revendications pour former le contenu du token
            String unsignedToken = header + "." + claimsString;

            //Sign token with secretKey
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(unsignedToken.getBytes(StandardCharsets.UTF_8));
            byte[] signedBytes = signature.sign();
            String signatureString = Base64.getUrlEncoder().encodeToString(signedBytes);

            // Concaténer le contenu du token signé avec la signature pour former le token JWT final
            String token = unsignedToken + "." + signatureString;

            return token;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String generateToken(User user) {
        String token = Jwts.builder()
                .claim("userid", user.getId())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        return token;
    }

    public User decryptToken(String token) {
        try {
            Jws<Claims> parsedToken = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            Claims claims = parsedToken.getBody();

            String userId = claims.get("userid", String.class);

            return userService.getOneUser(Integer.parseInt(userId)).get();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
