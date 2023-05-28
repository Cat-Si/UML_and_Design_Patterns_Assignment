package useCases.skills.skillFactory;

import globals.Ioc_Container;
import useCases.UseCaseCommand;
import useCases.skills.AddNewSkill;
import useCases.skills.EditSkill;
import useCases.skills.GetAllSkills;

public class SkillFactory {
    public enum CommandType{add, edit, view}


    public static UseCaseCommand createCommand(CommandType commandType) {
        switch (commandType) {
            case add:
                return new AddNewSkill(Ioc_Container.getInstance().getSkillRepository());
            case edit:
                return new EditSkill(Ioc_Container.getInstance().getSkillRepository());
            default:
                throw new IllegalArgumentException();
        }
    }



    public static UseCaseQuery createQuery(CommandType commandType) {
        switch (commandType) {
            case view:
                return new GetAllSkills(Ioc_Container.getInstance().getSkillRepository());
            default:
                throw new IllegalArgumentException();
        }
    }
}
