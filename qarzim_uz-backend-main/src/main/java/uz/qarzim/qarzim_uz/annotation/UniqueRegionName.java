package uz.qarzim.qarzim_uz.annotation;

import uz.qarzim.qarzim_uz.annotation.validator.UniqueRegionNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRegionNameValidator.class)
public @interface UniqueRegionName {

    String message() default "This region is already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
