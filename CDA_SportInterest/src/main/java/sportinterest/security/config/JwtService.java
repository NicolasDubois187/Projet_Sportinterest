package sportinterest.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secretkey}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refreshtoken.expiration}")
    private long refreshExpiration;

    /***
     * Extracts the mail from the token
     * @param token
     * @return  the mail
     */
    public String extractMail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /***
     * Extracts the claims from the token
     * @param token
     * @param claimsResolver
     * @return  the claims
     * @param <T>   the type of the claims
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /***
     * Generates a key from the secret key
     * @return  the key
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /***
     * Generates a token
     * @param extraClaims
     * @param userDetails
     * @return  the token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);                                                             //Builds the token
    }

    /***
     * Generates a refresh token
     * @param userDetails
     * @return  the refresh token
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);                                                             //Builds the token
    }

    /***
     * Generates a token
     * @param extraClaims
     * @param userDetails
     * @param expiration
     * @return  the token
     */
    public String buildToken(Map <String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())                              //Sets the subject to the mail
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /***
     * Validates the token
     * @param token
     * @param userDetails
     * @return  true if the token is valid
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String mail = extractMail(token);
        return mail.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /***
     * Extracts the expiration date from the token
     * @param token
     * @return  the expiration date
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /***
     * Extracts the expiration date from the token
     * @param token
     * @return  the expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /***
     * Extracts all the claims from the token
     * @param token
     * @return  the claims
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /***
     * Generates the key used to sign the token
     * @return  the key
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
