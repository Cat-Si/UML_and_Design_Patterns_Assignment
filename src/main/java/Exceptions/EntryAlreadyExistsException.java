package Exceptions;

public class EntryAlreadyExistsException extends Exception{
    private String errorMessage;
    public EntryAlreadyExistsException(String errorMessage){
        this.errorMessage= errorMessage;
    }
    public String getMessage(){
        return errorMessage;
    }
}
