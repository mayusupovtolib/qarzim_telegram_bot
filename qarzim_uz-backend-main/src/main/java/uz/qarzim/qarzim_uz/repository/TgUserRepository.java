package uz.qarzim.qarzim_uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.qarzim.qarzim_uz.entity.TgUser;

import java.util.Optional;

public interface TgUserRepository extends JpaRepository<TgUser, Long> {

    Optional<TgUser> findByChatIdAndDeletedFalse(String chatId);
}
