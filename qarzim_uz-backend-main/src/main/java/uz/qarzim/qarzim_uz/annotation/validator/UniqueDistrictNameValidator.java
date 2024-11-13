package uz.qarzim.qarzim_uz.annotation.validator;

import uz.qarzim.qarzim_uz.annotation.UniqueDistrictName;
import uz.qarzim.qarzim_uz.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class UniqueDistrictNameValidator implements ConstraintValidator<UniqueDistrictName, String> {


    private final DistrictRepository districtRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            return districtRepository.getCountByNameNative(value) == 0;
        }
        return false;
    }
}
