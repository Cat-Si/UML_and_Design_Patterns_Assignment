package useCases.manager;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import repositories.interfaces.BaseManagerRepository;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;
import useCases.UseCaseCommand;
import useCases.utility.UUIDGenerator;

public class AddNewManager extends BaseUseCase implements UseCaseCommand {

    private final BaseManagerRepository MANAGER_REPOSITPRY;


    public AddNewManager(BaseManagerRepository managerRepository) {
        MANAGER_REPOSITPRY = managerRepository;
    }

    public void execute() throws IllegalArgumentException, EntryAlreadyExistsException {
        String forename = (String) getNextRequestParameter();
        String surname = (String) getNextRequestParameter();
        String userName = (String) getNextRequestParameter();
        String password = (String) getNextRequestParameter();
        SystemRole systemRole = (SystemRole) getNextRequestParameter();
        Manager manager1 = new Manager(UUIDGenerator.generate(), forename, surname,userName, password,
                systemRole);
        MANAGER_REPOSITPRY.add(manager1);

    }
}
