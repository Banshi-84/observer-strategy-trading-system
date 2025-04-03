public interface StockEventSource {
    void addStockListener(StockListener sl);
    void removeStockListener(StockListener sl);
    void updateStockListeners();
}
