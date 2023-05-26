package useCases.skills;

import globals.Ioc_Container;

public class SkillFactory {
    public enum CommandType{view}

    public static UseCaseQuery createQuery(CommandType commandType) {
        switch (commandType) {
            case view:
                return new GetAllSkills(Ioc_Container.getSkillRepository());
            default:
                throw new IllegalArgumentException();
        }
    }
}
