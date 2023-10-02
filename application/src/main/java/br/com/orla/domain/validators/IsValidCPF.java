package br.com.orla.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CPFValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidCPF {
    String message() default "{IsValidCPF}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
