import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedMap;

public class Stock implements StockEventSource{
    private String symbol;
    private double price;
    private HashSet<StockListener> listeners;


    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        listeners = new HashSet<StockListener>();
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0){
            this.price = price;
        }
        else{
            throw new IllegalArgumentException();
        }
        updateStockListeners();
    }

    public void addStockListener(StockListener sl){
        listeners.add(sl);
    }
    public void removeStockListener(StockListener sl){
        listeners.remove(sl);
    }
    public void updateStockListeners(){
        StockEvent e = new StockEvent(this,getPrice());
        for (StockListener sl: listeners){
            sl.update(e);
        }
    }

    @Override
    public String toString() {
        return symbol +":"+ String.format("%.2f",price);
    }
    
}
