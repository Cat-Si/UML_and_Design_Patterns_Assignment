package useCases.staffSkill.staffSkillFactory;

import globals.Ioc_Container;
import useCases.UseCaseCommand;
import useCases.staffSkill.AddSkillToStaff;
import useCases.staffSkill.EditUserSkill;
import useCases.staffSkill.FindSkillsAssignedToStaff;

public class StaffSkillFactory {

    public enum CommandType{add, edit, view}

    public static UseCaseCommand createCommand(StaffSkillFactory.CommandType commandType) {
        switch (commandType) {
            case add:
                return new AddSkillToStaff(Ioc_Container.getInstance().getUserSkillRepository());
            case edit:
                return new EditUserSkill(Ioc_Container.getInstance().getUserSkillRepository());
             default:
                throw new IllegalArgumentException();
        }
    }


    public static UseCaseCommandFind createCommandFind(StaffSkillFactory.CommandType commandType) {
        switch (commandType) {
            case view:
                return new FindSkillsAssignedToStaff(Ioc_Container.getInstance().getUserSkillRepository());

            default:
                throw new IllegalArgumentException();
        }
    }


}
