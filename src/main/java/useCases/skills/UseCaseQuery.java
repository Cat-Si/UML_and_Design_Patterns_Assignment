package useCases.skills;

import domain.Skill;

import java.util.List;

public interface UseCaseQuery {
    List<Skill> execute();
}
