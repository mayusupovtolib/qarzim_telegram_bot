package uz.qarzim.qarzim_uz.annotation.validator;

import uz.qarzim.qarzim_uz.annotation.HasRegion;
import uz.qarzim.qarzim_uz.repository.RegionRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class HasRegionValidator implements ConstraintValidator<HasRegion, Long> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            return regionRepository.findByIdAndDeletedFalse(value).isPresent();
        }
        return false;
    }
}
