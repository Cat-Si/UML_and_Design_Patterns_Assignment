package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.StaffUser;
import java.util.List;

public interface BaseStaffUserRepository {

        List<StaffUser> getAll();

        void add(StaffUser staffUser) throws EntryAlreadyExistsException;

        void edit(StaffUser staffUser);


}
