package useCases.staff;
import domain.StaffUser;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;
import java.util.List;

public class GetAllStaff extends BaseUseCase {
    public final BaseStaffUserRepository STAFF_REPOSITORY;

    public GetAllStaff(BaseStaffUserRepository staffUserRepository) {
        this.STAFF_REPOSITORY = staffUserRepository;
    }

    public List<StaffUser> execute() {
        return STAFF_REPOSITORY.getAll();
    }
}
