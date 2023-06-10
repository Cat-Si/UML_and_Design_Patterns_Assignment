package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.iterators.SkillCollection;

public interface BaseSkillRepository {
        SkillCollection getAll();

       void add(Skill skill) throws EntryAlreadyExistsException;

        void edit(Skill skill);
}
