Factory per usecase collection 

abstract factory to contain all the usecase factories 

singleton pattern - IoC Container requires comnination with Builder

Builder with domain or user classes

Memento Pattern enabale restoration of state (maybe used in the edit classes)

Decorator could be used by manager to filter staff

composite - use to view a manager and all staff belonging to the manager - allowing the one reference to the manager to request data from each of his staff in turn

Facade Where you have a controller that interacts with more than one use case (e.g. the EnrolStudentOnModuleController) you could implement a Facade to interact with that rather than the 5 use cases - reducing coupling.

Observer - You could amend your code so that if you delete a Skill that the SkillRepository is observed by UserSkills observer and that deletes any user skills with that skill.

Chain of Responsibility - Could be used with the user to determine whether the manager or staffUser manages the object 


### Create a addNewManager useCase = Adding new staff should use either AddNewManager or AddNewStaff depending on the systemRole selected 