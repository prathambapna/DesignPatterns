public interface InventoryObserver {
    void onStockChanged(int sku, int currentQty);
}
