package uz.qarzim.qarzim_uz.payload.admin.response;

import java.sql.Timestamp;

public interface RegionProjection {

    Long getId();

    Timestamp getCreatedAt();

    String getName();
}
