package domain;


import java.util.Objects;
import java.util.UUID;

public class Category extends Validator {

    private String categoryName;
    private UUID id;

    public Category(UUID id, String categoryName) {
        this.categoryName = categoryName;
        this.id = id;
        validate();
    }


    private final void validate(){
        isNull(id,"id must not be null");
        isNull(categoryName,"Category must not be null");
        isBlank(categoryName,"Category must not be blank");
    }

    public UUID getId() { return id; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        validate();
    }

    public String toString(){
        return String.format("%s", categoryName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

}

    /*    private List<String> categoryName;
        public Category () {
            categoryName = new ArrayList<>();
            categoryName.add("OFFICE365");
            categoryName.add("PROGRAMMING");
            categoryName.add("TESTING_TOOLS");
            categoryName.add("VERSION_CONTROL");
        }

    public List<String> getCategoryName(){
            return categoryName;
    }*/

