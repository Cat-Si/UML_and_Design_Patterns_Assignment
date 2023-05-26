package useCases.staff.staffFactory;

import globals.Ioc_Container;

import useCases.UseCaseCommand;
import useCases.staff.AddNewStaff;
import useCases.staff.EditStaff;
import useCases.staff.GetAllStaff;

public class StaffFactory {
    public enum CommandType{add, edit, view}

    public static UseCaseCommand createCommand(StaffFactory.CommandType commandType) {
        switch (commandType) {
            case add:
                return new AddNewStaff(Ioc_Container.getStaffUserRepository());
            case edit:
                return new EditStaff(Ioc_Container.getStaffUserRepository());
            default:
                throw new IllegalArgumentException();
        }
    }



    public static UseCaseQuery createQuery(StaffFactory.CommandType commandType) {
        switch (commandType) {
            case view:
                return new GetAllStaff(Ioc_Container.getStaffUserRepository());
            default:
                throw new IllegalArgumentException();
        }
    }
}
