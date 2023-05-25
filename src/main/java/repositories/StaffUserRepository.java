package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.StaffUser;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseStaffUserRepository;

import java.util.List;

public class StaffUserRepository implements BaseStaffUserRepository {

    private final DataProvider IN_MEMORY_DATABASE;

    public StaffUserRepository(DataProvider dataProvider) {
        IN_MEMORY_DATABASE = dataProvider;
    }

    public List<StaffUser> getAll() {
        return IN_MEMORY_DATABASE.getStaffUser();
    }

    public void add(StaffUser staffUser) throws EntryAlreadyExistsException{
        if (getAll().contains(staffUser)) {
            throw new EntryAlreadyExistsException("Staff User already exists");
        } else {
            getAll().add(staffUser);
        }
    }

    public void edit(StaffUser staffUser) {
        for (StaffUser su : getAll()) {
            if (su.getId().equals(staffUser.getId())) {
                su.setFullName(staffUser.getFullName());
                su.setUsername(staffUser.getUsername());
                su.setPassword(staffUser.getPassword());
                su.setSystemRole(staffUser.getSystemRole());
                su.setStaffRole(staffUser.getStaffRole());
                su.setCurrentManager(staffUser.getCurrentManager());
            }
        }
    }


}
