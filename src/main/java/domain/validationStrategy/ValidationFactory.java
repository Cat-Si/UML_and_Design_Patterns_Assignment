package domain.validationStrategy;

import domain.Category;
import domain.Skill;

import java.util.UUID;

public class ValidationFactory {
    public static Skill createSkill(Category category, UUID id, String skillName) {
        return new Skill(category, id, skillName, new SkillValidation());
    }
}
