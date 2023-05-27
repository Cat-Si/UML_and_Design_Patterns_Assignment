package domain.iterators;

import domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillIterator implements Iterator {
    private List<Skill> skills = new ArrayList<>();

    int index;

    public SkillIterator(List<Skill> skills) {
        if (skills!=null) {
            this.skills.addAll(skills);
        }
    }

    @Override
    public boolean hasNext() {
        if (index < skills.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Skill next() {
        if (this.hasNext()) {
            return skills.get(index++);
        }
        return null;
    }
}
