package uz.qarzim.qarzim_uz.repository;

import uz.qarzim.qarzim_uz.entity.District;
import uz.qarzim.qarzim_uz.payload.ProjectionForSelect;
import uz.qarzim.qarzim_uz.payload.admin.response.DistrictProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findByIdAndDeletedFalse(Long id);

    @Query(value = "select count(id)\n" +
            "from district\n" +
            "where deleted = false\n" +
            "  and name = :name", nativeQuery = true)
    Integer getCountByNameNative(@Param("name") String name);

    @Modifying
    @Query(value = "update district\n" +
            "set deleted = true\n" +
            "where id = :id", nativeQuery = true)
    void updateDeletedTrueByIdNative(@Param("id") Long id);

    @Query(value = "select d.id         as id,\n" +
            "       d.created_at as createdAt,\n" +
            "       d.name       as name,\n" +
            "       r.name       as regionName\n" +
            "from district d\n" +
            "         left join region r on r.id = d.region_id\n" +
            "where d.deleted = false\n" +
            "  and (:search is null or d.name ilike concat('%', :search, '%'))\n" +
            "  and (:from is null or d.created_at >= to_timestamp(:from, 'yyyy-mm-dd hh24:mi:ss'))\n" +
            "  and (:to is null or d.created_at <= to_timestamp(:to, 'yyyy-mm-dd hh24:mi:ss'))\n" +
            "  and (:region_id is null or r.id = :region_id)\n" +
            "order by d.created_at desc", nativeQuery = true)
    Page<DistrictProjection> getAllForAdminNative(@Param("search") String search,
                                                  @Param("from") String from,
                                                  @Param("to") String to,
                                                  @Param("region_id") Long regionId,
                                                  Pageable pageable);

    @Query(value = "select d.id   as id,\n" +
            "       d.name as name\n" +
            "from district d\n" +
            "         left join region r on r.id = d.region_id\n" +
            "where d.deleted = false\n" +
            "  and (:region_id is null or r.id = :region_id)\n" +
            "order by r.name, d.name", nativeQuery = true)
    List<ProjectionForSelect> getAllForSelectNative(@Param("region_id") Long regionId);
}
