package uz.qarzim.qarzim_uz.annotation;

import uz.qarzim.qarzim_uz.annotation.validator.HasRegionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HasRegionValidator.class)
public @interface HasRegion {

    String message() default "Region is not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
