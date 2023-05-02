package controllers.interfaces;

//Interface to allow communication between Controller classes to allows id of object to edit to be passed
public interface DomainObjectToEdit {
     void passItemToEdit(Object itemToEdit);
}
