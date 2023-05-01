package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;


import java.util.List;

public interface BaseManagerRepository {

    List<Manager> getAll();

    void add(Manager manager) throws EntryAlreadyExistsException;

    void edit(Manager manager);
}
