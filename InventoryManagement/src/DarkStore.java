import java.util.List;

public class DarkStore implements InventoryObserver{
    private final InventoryManager inventoryManager;
    private ReplenishStrategy replenishStrategy;
    private final String name;
    private final double x,y;
    public DarkStore(String name,double x,double y){
        this.name=name;
        this.x=x;
        this.y=y;
        this.inventoryManager=new InventoryManager(new DbInventoryStore());
        this.inventoryManager.addObserver(this);
    }

    @Override
    public void onStockChanged(int sku, int currentQty) {
        if(replenishStrategy!=null){
            replenishStrategy.replenishItems(inventoryManager,sku,currentQty);
        }
    }

    public void setReplenishStrategy(ReplenishStrategy replenishStrategy){
        this.replenishStrategy=replenishStrategy;
    }

    public double calculateDistance(double userX,double userY){
        return Math.sqrt((x - userX)*(x - userX) + (y - userY)*(y - userY));
    }

    public boolean tryDeductStock(int sku, int qty) { return inventoryManager.tryDeductStock(sku, qty); }
    public void addStock(int sku, int qty) { inventoryManager.addStock(sku, qty); }
    public int checkStock(int sku) { return inventoryManager.checkStock(sku); }
    public List<Product> getAllProducts() { return inventoryManager.getAvailableProducts(); }

}
