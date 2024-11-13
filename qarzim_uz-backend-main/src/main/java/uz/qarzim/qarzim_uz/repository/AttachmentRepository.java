package uz.qarzim.qarzim_uz.repository;


import uz.qarzim.qarzim_uz.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    
}
