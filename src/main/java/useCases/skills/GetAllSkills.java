package useCases.skills;

import domain.Skill;
import domain.iterators.Iterator;
import domain.iterators.SkillCollection;
import domain.iterators.SkillIterator;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;
import useCases.skills.skillFactory.UseCaseQuery;

import java.util.ArrayList;
import java.util.List;

public class GetAllSkills extends BaseUseCase implements UseCaseQuery {

    private final BaseSkillRepository SKILL_REPOSITORY;

    public GetAllSkills(BaseSkillRepository skillRepository) {
        SKILL_REPOSITORY = skillRepository;
    }
    public List<Skill> execute() {
        List<Skill> skillList = new ArrayList<>();
        SkillCollection skillCollection = SKILL_REPOSITORY.getAll();
        SkillIterator skillIterator = skillCollection.getIterator();

        while (skillIterator.hasNext()) {
            Skill currentSkill = (Skill) skillIterator.next();
            skillList.add(currentSkill);
        }

        return skillList;
    }
}
