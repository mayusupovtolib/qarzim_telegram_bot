package uz.qarzim.qarzim_uz.annotation.validator;

import uz.qarzim.qarzim_uz.annotation.UniqueRegionName;
import uz.qarzim.qarzim_uz.repository.RegionRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueRegionNameValidator implements ConstraintValidator<UniqueRegionName, String> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            return regionRepository.getCountByNameNative(value) == 0;
        }
        return false;
    }
}
