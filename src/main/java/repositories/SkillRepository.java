package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseSkillRepository;

import java.util.List;

public class SkillRepository implements BaseSkillRepository {

    private final DataProvider IN_MEMORY_DATABASE;

    public SkillRepository(DataProvider dataProvider) {
        IN_MEMORY_DATABASE = dataProvider;
    }

    public List<Skill> getAll() {
        return IN_MEMORY_DATABASE.getSkill();
    }


    public void add(Skill skill) throws EntryAlreadyExistsException {
        if (getAll().contains(skill)) {
            throw new EntryAlreadyExistsException("Skill already exists");
        } else {
            getAll().add(skill);
        }
    }

    public void edit(Skill skill){
        for (Skill s: getAll()) {
            if (s.getId().equals(skill.getId())) {
                s.setSkillName(skill.getSkillName());
            }
        }
    }

}