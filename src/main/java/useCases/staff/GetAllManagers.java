package useCases.staff;

import domain.Manager;
import repositories.interfaces.BaseManagerRepository;


import java.util.List;

public class GetAllManagers {
    public final BaseManagerRepository MANAGER_REPOSITORY;

    public GetAllManagers(BaseManagerRepository managerRepository) {
        MANAGER_REPOSITORY = managerRepository;
    }

    public List<Manager> execute() {
        return MANAGER_REPOSITORY.getAll();
    }
}
