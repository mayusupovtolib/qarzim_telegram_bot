package uz.qarzim.qarzim_uz.secret;

import uz.qarzim.qarzim_uz.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        if (token != null) {
            User user = getUserFromToken(token);
            if (user != null) {
                if (!user.isAccountNonExpired()) {
                    System.err.println("User Expired");
                } else if (!user.isAccountNonLocked()) {
                    System.err.println("User Locked");
                } else if (!user.isCredentialsNonExpired()) {
                    System.err.println("User Credential expired");
                } else if (!user.isEnabled()) {
                    System.err.println("User Disabled");
                } else {
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    public User getUserFromToken(String token) {
        if (jwtProvider.validateToken(token)) {
            return jwtProvider.getUserFromToken(token);
        }
        return null;
    }


    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return token != null ? token.substring(7) : null;
    }
}
