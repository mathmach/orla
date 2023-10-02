package br.com.orla.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidator implements ConstraintValidator<IsValidCPF, String> {
    @Override
    public void initialize(IsValidCPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile("^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})|(\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2})$");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
