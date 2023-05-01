package globals;

import repositories.interfaces.BaseCategoryRepository;
import repositories.interfaces.BaseSkillRepository;
import repositories.interfaces.BaseUserSkillRepository;

public class Ioc_Container {

    private static BaseSkillRepository skillRepository;

    private static BaseCategoryRepository categoryRepository;

    private static BaseUserSkillRepository userSkillRepository;

    public Ioc_Container(BaseSkillRepository skillRepository, BaseCategoryRepository categoryRepository, BaseUserSkillRepository userSkillRepository) {
        this.skillRepository= skillRepository;
        this.categoryRepository = categoryRepository;
        this.userSkillRepository = userSkillRepository;
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
}
