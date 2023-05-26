package useCases;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUseCase {
    private int currentIndex = 0;
    private final List requestList = new ArrayList<>();

    public void clear() {
        requestList.clear();
    }
    public void add(Object parameter) {
        requestList.add(parameter);
    }

    public Object getNextRequestParameter(){
        Object listItem = requestList.get(currentIndex++);
        if(currentIndex==requestList.size()) {
            requestList.clear();
            currentIndex = 0;
        }
        return listItem;
    }
}
