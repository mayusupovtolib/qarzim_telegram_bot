package uz.qarzim.qarzim_uz.secret;

import uz.qarzim.qarzim_uz.entity.User;
import uz.qarzim.qarzim_uz.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRepository userRepo;
    @Value("${jwt.secretKey}")
    private String key;
    @Value("${jwt.expireDateInMilliSecund}")
    private Long expireDateMiliSecund;

    public String generateToken(User user) {
        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime() + expireDateMiliSecund);
        return Jwts
                .builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(issueDate)
                .claim("id", String.valueOf(user.getId()))
                .claim("username", user.getUsername())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        } catch (
                ExpiredJwtException e) {
            System.err.println("Muddati o'tgan");
        } catch (
                MalformedJwtException malformedJwtException) {
            System.err.println("Buzilgan token");
        } catch (
                SignatureException s) {
            System.err.println("Kalit so'z xato");
        } catch (
                UnsupportedJwtException unsupportedJwtException) {
            System.err.println("Qo'llanilmagan token");
        } catch (IllegalArgumentException ex) {
            System.err.println("Bo'sh token");
        }
        return false;
    }

    public User getUserFromToken(String token) {
        String userId = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        Optional<User> userOptional = userRepo.findByIdAndDeletedFalse(Long.valueOf(userId));
        return userOptional.orElse(null);
    }
}
