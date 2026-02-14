public interface Observable {
    void subscribe(Observer user);
    void unsubscribe(Observer user);
    void notifyUser(String news);
}
