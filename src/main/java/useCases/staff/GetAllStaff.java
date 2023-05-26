package useCases.staff;
import domain.StaffUser;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;
import useCases.staff.staffFactory.UseCaseQuery;

import java.util.List;

public class GetAllStaff extends BaseUseCase implements UseCaseQuery {
    public final BaseStaffUserRepository STAFF_REPOSITORY;

    public GetAllStaff(BaseStaffUserRepository staffUserRepository) {
        STAFF_REPOSITORY = staffUserRepository;
    }

    public List<StaffUser> execute() {
        return STAFF_REPOSITORY.getAll();
    }
}
