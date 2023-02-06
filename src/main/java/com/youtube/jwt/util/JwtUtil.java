package com.youtube.jwt.util;

import com.youtube.jwt.dao.RoleDao;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "learn_programming_yourself";

    private static final int TOKEN_VALIDITY = 3600 * 5;
    private final RoleDao roleDao;

    public JwtUtil(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        System.out.println("role- "+userDetails.getAuthorities());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
        List<String> roleNames = grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String firstRoleName = roleNames.get(0);
        System.out.println("ssssssss- "+firstRoleName);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("role", firstRoleName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
			LOGGER.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
			LOGGER.error("Expired JWT token", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token", ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.", ex);
        } catch (Exception ex) {
            System.out.println("Invalid Token");
            LOGGER.error("Invalid Token", ex);}
        return false;

    }
    public String getUserIdFromJWT_Pro(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();


        return claims.getSubject();
    }
    public String getFirstRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        String claimValue = claims.get("role", String.class);
        return  claimValue;
    }
    public String getRoleFromTokensSet(GrantedAuthority x){

//        Iterator<String> iterator = roles.iterator();
//        String firstItem="";
//        if (iterator.hasNext()) {
//            firstItem = iterator.next();
//            System.out.println("First item: " + firstItem);
//        }
        return "firstItem";

    }
}
