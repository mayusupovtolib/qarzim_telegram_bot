package uz.qarzim.qarzim_uz.entity;

import uz.qarzim.qarzim_uz.entity.base.AbsEntityLong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Role extends AbsEntityLong implements GrantedAuthority {

    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
