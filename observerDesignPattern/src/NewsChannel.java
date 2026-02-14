import java.util.ArrayList;
import java.util.List;

public class NewsChannel implements Observable{
    private List<Observer>observerList;
    public NewsChannel(){
        observerList=new ArrayList<>();
    }
    @Override
    public void subscribe(Observer user){
        observerList.add(user);
        System.out.println("Successfully subscribed.");
    }

    @Override
    public void unsubscribe(Observer user) {
        if (observerList.contains(user)) {
            observerList.remove(user);
            System.out.println("Successfully unsubscribed.");
        } else {
            System.out.println("Warning: Observer was not found in the subscription list.");
        }
    }

    @Override
    public void notifyUser(String news){
        for(Observer it:observerList){
            it.update(news);
        }
    }

    public void uploadNews(String news){
        this.notifyUser(news);
    }
}
