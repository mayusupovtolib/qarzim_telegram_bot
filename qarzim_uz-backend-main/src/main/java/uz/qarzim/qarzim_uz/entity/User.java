package uz.qarzim.qarzim_uz.entity;

import uz.qarzim.qarzim_uz.entity.base.AbsEntityLong;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "auth_user")
public class User extends AbsEntityLong implements UserDetails {

    private String username;

    private String password;

    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private String typeOfUser;

    private boolean accountNonLocked = true;

    private boolean accountNonExpired = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public User(String username, String password, Set<Role> roles, String typeOfUser) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.typeOfUser = typeOfUser;
    }
}