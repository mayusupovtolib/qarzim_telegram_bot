package uz.qarzim.qarzim_uz.service;

import uz.qarzim.qarzim_uz.entity.District;
import uz.qarzim.qarzim_uz.payload.ProjectionForSelect;
import uz.qarzim.qarzim_uz.payload.admin.request.DistrictDto;
import uz.qarzim.qarzim_uz.payload.admin.response.DistrictProjection;
import uz.qarzim.qarzim_uz.repository.DistrictRepository;
import uz.qarzim.qarzim_uz.responseObject.AllApiResponse;
import uz.qarzim.qarzim_uz.utill.CommandUtils;
import uz.qarzim.qarzim_uz.utill.MapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;

    private final MapperDto mapperDto;

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "district_select", allEntries = true)
    public HttpEntity<?> addDistrict(DistrictDto districtDto) {
        try {
            District district = mapperDto.makeDistrict(districtDto);
            districtRepository.save(district);
            return AllApiResponse.response(201, 1, "Success");
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "district_select", allEntries = true)
    public HttpEntity<?> editDistrict(Long districtId, DistrictDto districtDto) {
        try {
            Optional<District> optionalDistrict = districtRepository.findByIdAndDeletedFalse(districtId);
            if (optionalDistrict.isPresent()) {
                District district = optionalDistrict.get();
                district = mapperDto.updateDistrict(districtDto, district);
                districtRepository.save(district);
                return AllApiResponse.response(200, 1, "Success");
            } else {
                return AllApiResponse.response(404, 0, "No resul found!");
            }
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "district_select", allEntries = true)
    public HttpEntity<?> deleteDistrict(Long districtId) {
        try {
            Optional<District> optionalDistrict = districtRepository.findByIdAndDeletedFalse(districtId);
            if (optionalDistrict.isPresent()) {
                districtRepository.updateDeletedTrueByIdNative(districtId);
                return AllApiResponse.response(204, 1, "Success");
            } else {
                return AllApiResponse.response(404, 0, "No resul found!");
            }
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    public HttpEntity<?> getPageableDistrict(String search, List<String> fromTo, Long regionId, Integer page, Integer size) {
        try {
            Page<DistrictProjection> projectionPage;
            if (fromTo != null) {
                projectionPage = districtRepository.getAllForAdminNative(search, fromTo.get(0) != null ? fromTo.get(0) : null, fromTo.get(1) != null ? fromTo.get(1) : null, regionId, CommandUtils.simplePageable(page, size));
            } else {
                projectionPage = districtRepository.getAllForAdminNative(search, null, null, regionId, CommandUtils.simplePageable(page, size));
            }
            return AllApiResponse.response(200, 1, "Success", projectionPage, page, size);
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    @Cacheable(cacheNames = "district_select", key = "#regionId")
    public HttpEntity<?> getRegionForSelect(Long regionId) {
        try {
            List<ProjectionForSelect> projectionForSelectList = districtRepository.getAllForSelectNative(regionId);
            return AllApiResponse.response(200, 1, "Success", projectionForSelectList);
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }
}
