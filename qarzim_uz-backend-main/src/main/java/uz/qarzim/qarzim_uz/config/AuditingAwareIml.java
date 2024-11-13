package uz.qarzim.qarzim_uz.config;

import uz.qarzim.qarzim_uz.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditingAwareIml implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals("" + authentication.getPrincipal()))) {
            try {
                return Optional.of(((User) authentication.getPrincipal()).getId());
            } catch (Exception e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
