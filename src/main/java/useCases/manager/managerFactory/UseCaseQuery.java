package useCases.manager.managerFactory;

import domain.Manager;

import java.util.List;

public interface UseCaseQuery {

    List<Manager> execute();
}
