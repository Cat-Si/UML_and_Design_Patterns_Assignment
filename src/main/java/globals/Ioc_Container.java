package globals;

import repositories.interfaces.*;

public class Ioc_Container {

    private static BaseSkillRepository skillRepository;

    private static BaseCategoryRepository categoryRepository;

    private static BaseUserSkillRepository userSkillRepository;

    private static BaseManagerRepository managerRepository;

    private static  BaseStaffUserRepository staffUserRepository;

    public Ioc_Container(BaseSkillRepository skillRepository, BaseCategoryRepository categoryRepository, BaseUserSkillRepository userSkillRepository, BaseManagerRepository managerRepository, BaseStaffUserRepository staffUserRepository) {
        this.skillRepository= skillRepository;
        this.categoryRepository = categoryRepository;
        this.userSkillRepository = userSkillRepository;
        this.managerRepository = managerRepository;
        this.staffUserRepository = staffUserRepository;

    }

    public static final BaseSkillRepository getSkillRepository() {
        return skillRepository;
    }

    public static final BaseCategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public static final BaseUserSkillRepository getUserSkillRepository() {
        return userSkillRepository;
    }

    public static final BaseManagerRepository getManagerRepository() {
        return managerRepository;
    }

    public static final BaseStaffUserRepository getStaffUserRepository() {
        return staffUserRepository;
    }
}
