import java.util.Collection;
import java.util.HashMap;

public class Market {
    private HashMap<String, Stock> m;

    public Market() {
        m = new HashMap<String, Stock>();
    }

    public void addStock(Stock s){
        m.put(s.getSymbol(),s);
    }

    public Stock getStock(String symbol){
        return m.get(symbol);
    }

    public Double getPrice(String symbol){
        return m.get(symbol).getPrice();
    }

    public void setPrice(String symb, Double value){
        System.out.println(symb+": "+ value);
        Stock s = m.get(symb);
        s.setPrice(value);
    }

    @Override
    public String toString() {
        return "Market{" +
                "m=" + m +
                '}';
    }

    public Collection<Stock> getAllStocks() {
        return m.values();
    }

}
