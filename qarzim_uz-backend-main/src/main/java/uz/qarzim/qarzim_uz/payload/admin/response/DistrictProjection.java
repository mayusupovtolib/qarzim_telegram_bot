package uz.qarzim.qarzim_uz.payload.admin.response;

import java.sql.Timestamp;

public interface DistrictProjection {

    Long getId();

    Timestamp getCreatedAt();

    String getName();

    String getRegionName();
}
