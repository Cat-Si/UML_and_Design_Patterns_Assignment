package useCases;

import Exceptions.EntryAlreadyExistsException;
import domain.UserSkill;


import java.util.List;

public interface UseCaseCommand {
    void add(Object parameter);
    void execute() throws IllegalArgumentException, EntryAlreadyExistsException;

}
