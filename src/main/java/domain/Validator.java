package domain;

import java.time.LocalDate;

public abstract class Validator {
    public void isNull(Object attributeToCheck, String messageIfError) throws IllegalArgumentException{
        if(attributeToCheck==null) {
            throw new IllegalArgumentException(messageIfError);
        }
    }

    public void isBlank(String attributeToCheck, String messageIfError) throws IllegalArgumentException{
        if(attributeToCheck.isBlank()) {
            throw new IllegalArgumentException(messageIfError);
        }
    }


}
