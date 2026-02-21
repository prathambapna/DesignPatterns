public class RealDatabaseService implements DatabaseService{
    @Override
    public void executeQuery(String query){
        System.out.println("Executing query: "+query);
    }

}
