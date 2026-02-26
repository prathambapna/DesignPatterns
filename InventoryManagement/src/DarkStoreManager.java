import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DarkStoreManager {
    private final List<DarkStore> darkStores = new ArrayList<>();

    public void registerDarkStore(DarkStore ds) { darkStores.add(ds); }

    public List<DarkStore> getNearbyDarkStores(double userX, double userY,double maxDistance){
        List<Pair<Double,DarkStore>>distList=new ArrayList<>();
        for(DarkStore ds: darkStores){
            double dist=ds.calculateDistance(userX,userY);
            if(dist<=maxDistance){
                distList.add(new Pair<>(dist,ds));
            }
        }

        List<DarkStore>nearbyStores=new ArrayList<>();
        distList.sort(Comparator.comparing(Pair::getKey));
        for(Pair<Double,DarkStore> p:distList){
            nearbyStores.add(p.getValue());
        }

        return nearbyStores;
    }
}
