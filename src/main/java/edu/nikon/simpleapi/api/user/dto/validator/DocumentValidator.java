package edu.nikon.simpleapi.api.user.dto.validator;

import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.UnexpectedTypeException;
import java.util.Date;

public class DocumentValidator implements ConstraintValidator<IsDocumentValid, Object> {

    private String docCode;
    private String docNumber;
    private Date docDate;

    @Override
    public void initialize(IsDocumentValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof SaveUserDto) {
            SaveUserDto dto = (SaveUserDto) value;

            docCode = dto.getDocCode();
            docNumber = dto.getDocNumber();
            docDate = dto.getDocDate();
        } else if (value instanceof UpdateUserDto) {
            UpdateUserDto dto = (UpdateUserDto) value;

            docCode = dto.getDocCode();
            docNumber = dto.getDocNumber();
            docDate = dto.getDocDate();
        } else {
            throw new UnexpectedTypeException();
        }

        return (docCode != null && docNumber != null && docDate != null) ||
                (docCode == null && docNumber == null && docDate == null);
    }
}
