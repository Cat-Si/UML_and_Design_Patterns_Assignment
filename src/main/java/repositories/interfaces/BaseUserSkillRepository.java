package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.UserSkill;

import java.util.List;

public interface BaseUserSkillRepository {
    List<UserSkill> getAll();

    void add(UserSkill userSkill) throws EntryAlreadyExistsException;

    void edit(UserSkill userSkill);
}

