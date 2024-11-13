package uz.qarzim.qarzim_uz.repository;

import uz.qarzim.qarzim_uz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsernameAndDeletedFalse(String username);

    Optional<User> findByIdAndDeletedFalse(Long id);

    Optional<User> findByUsernameAndDeletedFalseAndEnabledTrue(String username);

    @Modifying
    @Query(value = "update users" +
            "           set deleted = true" +
            "           where id = :id", nativeQuery = true)
    void deleteUser(@Param("id") Long id);

    @Modifying
    @Query(value = "update users\n" +
            "set password = :password\n" +
            "where id = :id", nativeQuery = true)
    void changePassword(@Param("password") String password,
                        @Param("id") Long id);
}
