package useCases.manager;

import domain.Manager;
import repositories.interfaces.BaseManagerRepository;
import useCases.manager.managerFactory.UseCaseQuery;

import java.util.List;

public class GetAllManagers implements UseCaseQuery {
    private final BaseManagerRepository MANAGER_REPOSITORY;

    public GetAllManagers(BaseManagerRepository managerRepository) {
        MANAGER_REPOSITORY = managerRepository;
    }

    public List<Manager> execute() {
        return MANAGER_REPOSITORY.getAll();
    }
}
