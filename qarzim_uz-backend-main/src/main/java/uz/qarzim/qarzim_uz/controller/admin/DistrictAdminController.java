package uz.qarzim.qarzim_uz.controller.admin;

import uz.qarzim.qarzim_uz.payload.admin.request.DistrictDto;
import uz.qarzim.qarzim_uz.service.DistrictService;
import uz.qarzim.qarzim_uz.utill.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/district")
@RequiredArgsConstructor
public class DistrictAdminController {

    private final DistrictService districtService;

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addNewDistrict(@RequestBody @Valid DistrictDto districtDto) {
        return districtService.addDistrict(districtDto);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editDistrict(@PathVariable("id") Long districtId,
                                      @RequestBody @Valid DistrictDto districtDto) {
        return districtService.editDistrict(districtId, districtDto);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteDistrict(@PathVariable("id") Long districtId) {
        return districtService.deleteDistrict(districtId);
    }

    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @GetMapping()
    public HttpEntity<?> getDistrictPageable(@RequestParam(name = "search", required = false) String search,
                                           @RequestParam(name = "from_to", required = false) List<String> fromTo,
                                           @RequestParam(name = "region_id", required = false) Long regionId,
                                           @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                           @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return districtService.getPageableDistrict(search, fromTo, regionId, page, size);
    }
}
