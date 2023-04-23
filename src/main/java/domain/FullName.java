package domain;

import java.util.Objects;

public final class FullName extends Validator{
    private String firstName;
    private String surname;

    public FullName(String firstName, String surname){
        this.firstName= firstName;
        this.surname = surname;
        validate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullName)) return false;
        FullName fullName = (FullName) o;
        return  Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(surname, fullName.surname);
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSurname(){
        return surname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname);
    }

    public String toString(){
        return String.format("%s %s",
                firstName,
                surname);
    }

    private void validate(){
        isNull(firstName,"firstname must not be null");
        isBlank(firstName,"firstname must not be blank");
        isNull(surname,"surname must not be null");
        isBlank(surname,"surname must not be blank");
    }
}
