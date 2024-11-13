package uz.qarzim.qarzim_uz.entity;

import uz.qarzim.qarzim_uz.entity.base.AbsEntityUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsEntityUUID {

    private String name;

    private String originalName;

    private String pathFile;

    private String contentType;

    private double size;
}
