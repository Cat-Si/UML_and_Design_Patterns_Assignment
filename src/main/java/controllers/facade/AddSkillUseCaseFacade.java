package controllers.facade;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import useCases.UseCaseCommand;
import useCases.category.categoryFactory.CategoryFactory;
import useCases.category.categoryFactory.UseCaseQuery;
import useCases.skills.skillFactory.SkillFactory;

import java.util.List;

public class AddSkillUseCaseFacade {

    private final UseCaseCommand addNewSkill = SkillFactory.createCommand(SkillFactory.CommandType.add);
    private final UseCaseQuery getAllCategories = CategoryFactory.createQuery(CategoryFactory.CommandType.view);

    public List<Category> getAllCategories() {
        return getAllCategories.execute();
    }


    public void  addNewSkill(String name, Category category) throws EntryAlreadyExistsException {
        addNewSkill.add(name);
        addNewSkill.add(category);
        addNewSkill.execute();
    }


}


