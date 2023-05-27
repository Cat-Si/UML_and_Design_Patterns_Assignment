package useCases.manager.managerFactory;

import globals.Ioc_Container;
import useCases.UseCaseCommand;
import useCases.manager.AddNewManager;
import useCases.manager.GetAllManagers;


public class ManagerFactory {

    public enum CommandType{view, add}

    public static UseCaseCommand createCommand(ManagerFactory.CommandType commandType) {
        switch (commandType) {
            case add:
                return new AddNewManager(Ioc_Container.getManagerRepository());
            default:
                throw new IllegalArgumentException();
        }
    }

    public static UseCaseQuery createQuery (ManagerFactory.CommandType commandType) {
        switch (commandType) {
            case view:
                return new GetAllManagers(Ioc_Container.getManagerRepository());
            default:
                throw new IllegalArgumentException();
        }
    }
}
