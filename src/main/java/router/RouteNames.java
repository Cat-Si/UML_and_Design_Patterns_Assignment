package router;

public enum RouteNames {
    HOME("Home.fxml"),

    //Manager Views
    MANAGER_MAIN("manager/ManagerMainPage.fxml"),
    EDIT_SKILL("manager/EditSkill.fxml"),
    ADD_SKILL("manager/AddSkill.fxml"),
    EDIT_STAFF("manager/EditStaff.fxml"),

    SHOW_SKILL("manager/ShowSkill.fxml"),
    ADD_STAFF("manager/AddStaff.fxml"),

    //REMOVE_CATEGORY("manager/RemoveCategoryController.fxml") - not used,

    //Staff Views
    STAFF_MAIN("staff/StaffMainPage.fxml"),
   USER_DETAILS("staff/UserDetails.fxml"),
    USER_SKILL("staff/AddSkillToUser.fxml");



    private final String location;

    public String getLocation(){
        return location;
    }

   RouteNames(String location) {
       String VIEW_PATH = "/views/";
       this.location = VIEW_PATH + location;
    }
}