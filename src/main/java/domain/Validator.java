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

    public void isTooEarlyDate(LocalDate dateToCheck, LocalDate dateToCompareTo) throws IllegalArgumentException{
       if(dateToCheck.isBefore(dateToCompareTo)){
            throw new IllegalArgumentException("Date is too old");
       }
    }

    public void isFutureDate(LocalDate toCheck) throws IllegalArgumentException{
        if(toCheck.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date is in the future");
        }
    }
}
