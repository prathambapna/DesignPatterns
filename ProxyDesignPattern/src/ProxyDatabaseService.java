public class ProxyDatabaseService implements DatabaseService{
    RealDatabaseService realDatabaseService;
    String role;

    public ProxyDatabaseService(String role){
        this.role=role;
    }

    public void executeQuery(String query){
        //protection proxy
        if(role.equalsIgnoreCase("ADMIN")){
            if(realDatabaseService==null){
                //lazy loading (virtual proxy)
                realDatabaseService=new RealDatabaseService();
            }
            realDatabaseService.executeQuery(query);
        }
        else{
            System.out.println("Only admin allowed to execute query");
        }
    }
}
