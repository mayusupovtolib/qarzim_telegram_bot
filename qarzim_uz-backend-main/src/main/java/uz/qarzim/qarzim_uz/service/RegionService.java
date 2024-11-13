package uz.qarzim.qarzim_uz.service;

import uz.qarzim.qarzim_uz.entity.Region;
import uz.qarzim.qarzim_uz.payload.ProjectionForSelect;
import uz.qarzim.qarzim_uz.payload.admin.request.RegionDto;
import uz.qarzim.qarzim_uz.payload.admin.response.RegionProjection;
import uz.qarzim.qarzim_uz.repository.RegionRepository;
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
public class RegionService {

    private final RegionRepository regionRepository;

    private final MapperDto mapperDto;

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "region_select", allEntries = true)
    public HttpEntity<?> addRegion(RegionDto regionDto) {
        try {
            Region region = mapperDto.makeRegion(regionDto);
            regionRepository.save(region);
            return AllApiResponse.response(201, 1, "Success");
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "region_select", allEntries = true)
    public HttpEntity<?> editRegion(Long regionId, RegionDto regionDto) {
        try {
            Optional<Region> optionalRegion = regionRepository.findByIdAndDeletedFalse(regionId);
            if (optionalRegion.isPresent()) {
                Region region = optionalRegion.get();
                region = mapperDto.updateRegion(regionDto, region);
                regionRepository.save(region);
                return AllApiResponse.response(200, 1, "Success");
            } else {
                return AllApiResponse.response(404, 0, "No resul found!");
            }
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "region_select", allEntries = true)
    public HttpEntity<?> deleteRegion(Long regionId) {
        try {
            Optional<Region> optionalRegion = regionRepository.findByIdAndDeletedFalse(regionId);
            if (optionalRegion.isPresent()) {
                regionRepository.updateDeletedTrueByIdNative(regionId);
                return AllApiResponse.response(204, 1, "Success");
            } else {
                return AllApiResponse.response(404, 0, "No resul found!");
            }
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    public HttpEntity<?> getPageableRegion(String search, List<String> fromTo, Integer page, Integer size) {
        try {
            Page<RegionProjection> projectionPage;
            if (fromTo != null) {
                projectionPage = regionRepository.getAllForAdminNative(search, fromTo.get(0) != null ? fromTo.get(0) : null, fromTo.get(1) != null ? fromTo.get(1) : null, CommandUtils.simplePageable(page, size));
            } else {
                projectionPage = regionRepository.getAllForAdminNative(search, null, null, CommandUtils.simplePageable(page, size));
            }
            return AllApiResponse.response(200, 1, "Success", projectionPage, page, size);
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }

    @Cacheable(cacheNames = "region_select")
    public HttpEntity<?> getRegionForSelect() {
        try {
            List<ProjectionForSelect> projectionForSelectList = regionRepository.getAllForSelectNative();
            return AllApiResponse.response(200, 1, "Success", projectionForSelectList);
        } catch (Exception e) {
            return AllApiResponse.response(500, 0, "Error", e.getMessage());
        }
    }
}
