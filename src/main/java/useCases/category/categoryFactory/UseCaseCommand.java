package useCases.category.categoryFactory;

import Exceptions.EntryAlreadyExistsException;

public interface UseCaseCommand {
    void add(Object parameter);
    void execute() throws IllegalArgumentException, EntryAlreadyExistsException;

}
