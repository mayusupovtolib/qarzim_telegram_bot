package uz.qarzim.qarzim_uz.entity;

import uz.qarzim.qarzim_uz.entity.base.AbsEntityLong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Region extends AbsEntityLong {

    @Column(nullable = false)
    private String name;
}
