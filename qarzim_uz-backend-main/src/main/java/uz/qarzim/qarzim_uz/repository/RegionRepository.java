package uz.qarzim.qarzim_uz.repository;


import uz.qarzim.qarzim_uz.entity.Region;
import uz.qarzim.qarzim_uz.payload.ProjectionForSelect;
import uz.qarzim.qarzim_uz.payload.admin.response.RegionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByIdAndDeletedFalse(Long id);

    @Query(value = "select count(id)\n" +
            "from region\n" +
            "where deleted = false\n" +
            "  and name = :name", nativeQuery = true)
    Integer getCountByNameNative(@Param("name") String name);

    @Modifying
    @Query(value = "update region\n" +
            "set deleted = true\n" +
            "where id = :id", nativeQuery = true)
    void updateDeletedTrueByIdNative(@Param("id") Long id);

    @Query(value = "select r.id         as id,\n" +
            "       r.created_at as createdAt,\n" +
            "       r.name       as name\n" +
            "from region r\n" +
            "where r.deleted = false\n" +
            "  and (:search is null or r.name ilike concat('%', :search, '%'))\n" +
            "  and (:from is null or r.created_at >= to_timestamp(:from, 'yyyy-mm-dd hh24:mi:ss'))\n" +
            "  and (:to is null or r.created_at <= to_timestamp(:to, 'yyyy-mm-dd hh24:mi:ss'))\n" +
            "order by r.created_at desc", nativeQuery = true)
    Page<RegionProjection> getAllForAdminNative(@Param("search") String search,
                                                @Param("from") String from,
                                                @Param("to") String to,
                                                Pageable pageable);

    @Query(value = "select r.id   as id,\n" +
            "       r.name as name\n" +
            "from region r\n" +
            "where r.deleted = false\n" +
            "order by r.name", nativeQuery = true)
    List<ProjectionForSelect> getAllForSelectNative();
}
