package domain.iterators;

import domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillCollection implements Container {
    private List<Skill> skills = new ArrayList<>();

    @Override
    public SkillIterator getIterator() {
        return new SkillIterator(skills);
    }

    public void add(Skill skill) {
        skills.add(skill);
    }

    public void remove(Skill skill) {
        skills.remove(skill);
    }

    public Skill get(int i) {
        return skills.get(i);
    };
}
