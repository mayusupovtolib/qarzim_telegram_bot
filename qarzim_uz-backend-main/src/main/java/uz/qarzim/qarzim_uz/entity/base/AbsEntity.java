package uz.qarzim.qarzim_uz.entity.base;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class AbsEntity {

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @LastModifiedBy
    private Long updatedBy;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ExtraData extraData;

    private boolean deleted = false;
}
