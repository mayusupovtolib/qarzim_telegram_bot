package uz.qarzim.qarzim_uz.utill;


import uz.qarzim.qarzim_uz.entity.District;
import uz.qarzim.qarzim_uz.entity.Region;
import uz.qarzim.qarzim_uz.payload.admin.request.DistrictDto;
import uz.qarzim.qarzim_uz.payload.admin.request.RegionDto;
import uz.qarzim.qarzim_uz.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MapperDto {

    private final RegionRepository regionRepository;

    public Region makeRegion(RegionDto regionDto) {
        if (regionDto != null) {
            return makeOrUpdateRegion(regionDto, new Region());
        }
        return null;
    }

    public Region updateRegion(RegionDto regionDto, Region region) {
        if (regionDto != null) {
            return makeOrUpdateRegion(regionDto, region);
        }
        return null;
    }

    private Region makeOrUpdateRegion(RegionDto regionDto, Region region) {
        region.setName(regionDto.getName());
        return region;
    }

    public District makeDistrict(DistrictDto districtDto) {
        if (districtDto != null) {
            return makeOrUpdateDistrict(districtDto, new District());
        }
        return null;
    }

    public District updateDistrict(DistrictDto districtDto, District district) {
        if (districtDto != null) {
            return makeOrUpdateDistrict(districtDto, district);
        }
        return null;
    }

    private District makeOrUpdateDistrict(DistrictDto districtDto, District district) {
        district.setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findByIdAndDeletedFalse(districtDto.getRegionId());
        optionalRegion.ifPresent(district::setRegion);
        return district;
    }
}
