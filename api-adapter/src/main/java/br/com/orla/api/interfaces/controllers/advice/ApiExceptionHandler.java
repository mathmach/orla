package br.com.orla.api.interfaces.controllers.advice;

import br.com.orla.exceptions.BusinessException;
import br.com.orla.exceptions.InternalServerErrorException;
import br.com.orla.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ApiErrorMessage> handleException(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<ApiErrorMessage> handleException(ResourceNotFoundException ex, WebRequest request) {

        List<String> errors = Arrays.asList(ex.getMessage());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }


    @ExceptionHandler({InternalServerErrorException.class})
    public final ResponseEntity<ApiErrorMessage> handleException(InternalServerErrorException ex, WebRequest request) {

        List<String> errors = Arrays.asList(ex.getMessage());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler({BusinessException.class})
    public final ResponseEntity<ApiErrorMessage> handleException(BusinessException ex, WebRequest request) {

        List<String> list = Arrays.asList(ex.getMessage());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, list);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public final ResponseEntity<ApiErrorMessage> handleException(MethodArgumentTypeMismatchException ex, WebRequest request) {

        ex.printStackTrace();

        if (nonNull(ex.getRequiredType()) && ex.getRequiredType().isEnum()) {

            List<String> validValues = new ArrayList<>();

            for (Object enumConstant : ex.getRequiredType().getEnumConstants()) {
                String name = ((Enum) enumConstant).name();
                validValues.add(name);
            }

            StringBuilder stringBuilder = new StringBuilder().append("The parameter [")
                    .append(ex.getName())
                    .append("] must contain one of the values: ")
                    .append(Strings.join(validValues, ','));

            List<String> errors = Arrays.asList(stringBuilder.toString());
            ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, errors);
            return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
        }

        List<String> list = Arrays.asList(ex.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, list);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public final ResponseEntity<ApiErrorMessage> handleException(HttpMessageNotReadableException ex, WebRequest request) {

        final String error = Arrays.stream(ex.getMessage().split(";")).findFirst().orElse("Invalid create.");
        List<String> list = Arrays.asList(error);
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, list);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

}
