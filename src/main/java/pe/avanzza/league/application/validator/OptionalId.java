package pe.avanzza.league.application.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Gloria R. Leyva Jerez
 * This annotation validate a identifier
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalIdValidator.class)
public @interface OptionalId {
    String message() default "The identifier is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
