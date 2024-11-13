package uz.qarzim.qarzim_uz.payload.admin.request;

import uz.qarzim.qarzim_uz.annotation.UniqueRegionName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDto {

    @NotNull
    @UniqueRegionName
    private String name;
}
