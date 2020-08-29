package pe.avanzza.league.application.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Gloria R. Leyva Jerez
 */
public class CustomNotBlankValidator implements ConstraintValidator<CustomNotBlank, String> {

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field == null || !field.isBlank();
    }

}
