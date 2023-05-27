package domain.validationStrategy;

import domain.Category;
import domain.Validator;

import java.util.UUID;

public class SkillValidation extends Validator implements ValidationStrategy {
    @Override
    public void validate(Object...args){
        Category category = (Category) args[0];
        UUID id = (UUID) args[1];
        String skillName = (String) args[2];
        isNull(id,"id must not be null");
        isNull(category, "category must not be null");
        isNull(skillName,"name must not be null");
        isBlank(skillName,"name must not be blank");
    }
}
