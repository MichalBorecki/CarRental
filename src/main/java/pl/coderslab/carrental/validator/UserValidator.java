package pl.coderslab.carrental.validator;


import pl.coderslab.carrental.web.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "message.firstName", "Imię jest wymagane.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "message.lastName", "Nazwisko jest wymagane.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password", "Hasło jest wymagane.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.email", "Adres email jest wymagany.");
    }

}
