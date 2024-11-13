package uz.qarzim.qarzim_uz.annotation;

import uz.qarzim.qarzim_uz.annotation.validator.UniqueDistrictNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDistrictNameValidator.class)
public @interface UniqueDistrictName {

    String message() default "This district is already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
