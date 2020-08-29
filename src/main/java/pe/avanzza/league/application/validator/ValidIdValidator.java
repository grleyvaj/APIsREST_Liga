package pe.avanzza.league.application.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Gloria R. Leyva Jerez
 */
public class ValidIdValidator implements ConstraintValidator<ValidId, Integer> {

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
//        if (id != null)
            return id >= 1L;
    }
}
