import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    @MaxLength(30)
    private String username;
    @NotBlank
    @MaxLength(50)
    private String email;
    @NotBlank
    @MaxLength(20)
    private String password;
    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class FormValidator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        if (obj == null) {
            errors.add("Object cannot be null");
            return errors;
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);
                String fieldName = field.getName();

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null ||
                            (value instanceof String &&
                                    ((String) value).trim().isEmpty())) {
                        errors.add(fieldName + " must not be blank");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int maxLength = annotation.value();

                    if (value instanceof String &&
                            ((String) value).length() > maxLength) {
                        errors.add(fieldName +
                                " must not exceed " +
                                maxLength + " characters");
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Unable to access field: " + field.getName());
            }
        }

        return errors;
    }
}

public class FormValidatorTest {
    public static void main(String[] args) {
        SignupForm form = new SignupForm(
                "",
                "thisisaverylongemailaddress@example.com",
                "mypassword"
        );
        List<String> errors = FormValidator.validate(form);
        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Validation errors:");
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}