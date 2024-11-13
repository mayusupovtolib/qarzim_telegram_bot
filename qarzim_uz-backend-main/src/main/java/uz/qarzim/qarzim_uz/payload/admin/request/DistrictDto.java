package uz.qarzim.qarzim_uz.payload.admin.request;

import uz.qarzim.qarzim_uz.annotation.HasRegion;
import uz.qarzim.qarzim_uz.annotation.UniqueDistrictName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {

    @NotNull
    @UniqueDistrictName
    private String name;

    @NotNull
    @HasRegion
    private Long regionId;
}
