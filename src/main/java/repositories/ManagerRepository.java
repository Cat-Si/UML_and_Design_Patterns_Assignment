package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseManagerRepository;

import java.util.List;

public class ManagerRepository implements BaseManagerRepository {
    private final DataProvider IN_MEMORY_DATABASE;

    public ManagerRepository(DataProvider dataProvider) {
        IN_MEMORY_DATABASE = dataProvider;
    }

    public List<Manager> getAll() {
        return IN_MEMORY_DATABASE.getManager();
    }

    public void add(Manager manager) throws EntryAlreadyExistsException {
        if (getAll().contains(manager)) {
            throw new EntryAlreadyExistsException("Manager already exists");
        } else {
            getAll().add(manager);
        }
    }

    public void edit(Manager manager) {
        for (Manager m : getAll()) {
            if (m.getId().equals(manager.getId())) {
                m.setFullName(manager.getFullName());
            }
        }
    }
}
