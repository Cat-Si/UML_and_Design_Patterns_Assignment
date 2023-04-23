package useCases;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUseCase {
    private int currentIndex = 0;
    public final List requestList = new ArrayList<>();

    public Object getNextRequestParameter(){
        Object listItem = requestList.get(currentIndex++);
        if(currentIndex==requestList.size()) {
            requestList.clear();
            currentIndex = 0;
        }
        return listItem;
    }
}
