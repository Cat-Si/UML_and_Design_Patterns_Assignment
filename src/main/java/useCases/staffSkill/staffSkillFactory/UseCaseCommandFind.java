package useCases.staffSkill.staffSkillFactory;

import domain.UserSkill;

import java.util.List;
import java.util.Optional;

public interface UseCaseCommandFind {

    void add(Object parameter);
    Optional<List<UserSkill>> execute();
}
