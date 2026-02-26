public interface ReplenishStrategy {
    void replenishItems(InventoryManager inventoryManager,int sku,int qty);
}
