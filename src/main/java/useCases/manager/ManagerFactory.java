package useCases.manager;

import globals.Ioc_Container;


public class ManagerFactory {

    public enum CommandType{view}

    public static UseCaseQuery createQuery (ManagerFactory.CommandType commandType) {
        switch (commandType) {
            case view:
                return new GetAllManagers(Ioc_Container.getManagerRepository());
            default:
                throw new IllegalArgumentException();
        }
    }
}
