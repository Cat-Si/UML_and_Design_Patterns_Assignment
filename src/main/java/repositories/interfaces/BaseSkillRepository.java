package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import java.util.List;

public interface BaseSkillRepository {
        List<Skill> getAll();

       void add(Skill skill) throws EntryAlreadyExistsException;

        void edit(Skill skill);
}
