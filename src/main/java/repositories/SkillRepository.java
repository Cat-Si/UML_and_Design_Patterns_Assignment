package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.iterators.SkillCollection;
import domain.iterators.SkillIterator;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseSkillRepository;

public class SkillRepository implements BaseSkillRepository {

    private final DataProvider IN_MEMORY_DATABASE;

    public SkillRepository(DataProvider dataProvider) {
        IN_MEMORY_DATABASE = dataProvider;
    }

    public SkillCollection getAll() {
        return IN_MEMORY_DATABASE.getSkill();
    }


    public void add(Skill skill) throws EntryAlreadyExistsException {
        SkillCollection skillCollection = getAll();
        SkillIterator skillIterator = skillCollection.getIterator();

        while (skillIterator.hasNext()) {
            Skill currentSkill = skillIterator.next();
            if (currentSkill.equals(skill)) { //check if currentSkill equals skill
                throw new EntryAlreadyExistsException("Skill already exists"); //throw exception
            }
            }
        skillCollection.add(skill); //add skill to collection
    }


    public void edit(Skill skill) {
        SkillCollection skillCollection = getAll();
        SkillIterator skillIterator = skillCollection.getIterator();

        while (skillIterator.hasNext()) {
            Skill currentSkill = skillIterator.next();
            if (currentSkill.getId().equals(skill.getId())) {
                currentSkill.setSkillName(skill.getSkillName());
                currentSkill.setCategory(skill.getCategory());
            }
        }
    }


}