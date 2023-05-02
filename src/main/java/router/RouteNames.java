package router;

import controllers.RemoveCategory;

public enum RouteNames {
    HOME("Home.fxml"),

    //Manager Views
    MANAGER_MAIN("manager/ManagerMainPageController.fxml"),
    EDIT_SKILL("manager/EditSkillController.fxml"),
    ADD_SKILL("manager/AddSkill.fxml"),
    EDIT_STAFF("manager/EditStaffController.fxml"),
    ADD_STAFF("manager/AddStaff.fxml"),
    REMOVE_CATEGORY("manager/RemoveCategory.fxml"),

    //Staff Views
    STAFF_MAIN("staff/StaffMainPageController.fxml"),
   USER_DETAILS("staff/UserDetails.fxml"),
    USER_SKILL("staff/UserSkill.fxml");



    private final String location;
    private final String VIEW_PATH = "/views/";

    public String getLocation(){
        return location;
    }

   RouteNames(String location) {
        this.location = VIEW_PATH + location;
    }
}