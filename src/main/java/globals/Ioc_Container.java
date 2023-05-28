package globals;

import repositories.interfaces.*;

public class Ioc_Container {


    private  BaseSkillRepository skillRepository;
    private  BaseCategoryRepository categoryRepository;
    private  BaseUserSkillRepository userSkillRepository;
    private  BaseManagerRepository managerRepository;
    private   BaseStaffUserRepository staffUserRepository;


    private static final Ioc_Container INSTANCE = new Ioc_Container();
    private Ioc_Container() {
        // Private constructor to prevent direct instantiation
    }

    public static  Ioc_Container getInstance() {
        return INSTANCE;
    }

    private Ioc_Container build(Ioc_ContainerBuilder builder) {
        this.skillRepository = builder.skillRepository;
        this.categoryRepository = builder.categoryRepository;
        this.userSkillRepository = builder.userSkillRepository;
        this.managerRepository = builder.managerRepository;
        this.staffUserRepository = builder.staffUserRepository;
        return this;
    }

    public  final BaseSkillRepository getSkillRepository() {
        return skillRepository;
    }

    public  final BaseCategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public  final BaseUserSkillRepository getUserSkillRepository() {
        return userSkillRepository;
    }

    public  final BaseManagerRepository getManagerRepository() {
        return managerRepository;
    }

    public  final BaseStaffUserRepository getStaffUserRepository() {
        return staffUserRepository;
    }

    public static class  Ioc_ContainerBuilder {
        private BaseSkillRepository skillRepository;
        private BaseCategoryRepository categoryRepository;
        private BaseUserSkillRepository userSkillRepository;
        private BaseManagerRepository managerRepository;
        private BaseStaffUserRepository staffUserRepository;

        private Ioc_ContainerBuilder(){};

        public Ioc_ContainerBuilder(BaseSkillRepository skillRepository, BaseCategoryRepository categoryRepository, BaseUserSkillRepository userSkillRepository, BaseManagerRepository managerRepository, BaseStaffUserRepository staffUserRepository) {
            this.skillRepository = skillRepository;
            this.categoryRepository = categoryRepository;
            this.userSkillRepository = userSkillRepository;
            this.managerRepository = managerRepository;
            this.staffUserRepository = staffUserRepository;
        }

        public Ioc_Container build() {
            return Ioc_Container.INSTANCE.build(this);
        }
    }



}
