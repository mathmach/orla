package br.com.orla.utils;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public abstract class SelfValidating<T> {
    private final Validator validator;

    protected SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() throws MethodArgumentNotValidException {
        @SuppressWarnings("unchecked")
        BeanPropertyBindingResult result = new BeanPropertyBindingResult((T) this, this.getClass().getName());
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new MethodArgumentNotValidException(null, result);
        }
    }
}
