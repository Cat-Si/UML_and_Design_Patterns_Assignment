# Code Issues 

AddSkillsToStaffController ----

Unable to add/edit/delete skills from staff. 

Delete should allow staff to select the skill on the view and click delete and it will delete the skill
Edit should allow the person to edit the information on the view and click edit and it will save edited skill
Add should take the person to a new view which has empty fields to add skillname, skillLevel, expiry, notes.

Handout page 13 


StaffUser ---- 

Want it to check if it exists based on the username

# Test Issues 

AddNewStaffTest ---- 
test03 throws correct Exception but still fails test 
Mockito is dulled out 


cannot change in UserSkillRepository as it breaks code 
public Optional<List<UserSkill>> getSkillsForStaff(**StaffUser u**) {

