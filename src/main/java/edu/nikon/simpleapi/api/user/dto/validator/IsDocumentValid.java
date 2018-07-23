package edu.nikon.simpleapi.api.user.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DocumentValidator.class)
public @interface IsDocumentValid {
    String message() default "All the data of the document must be specified or none";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
