import java.util.HashMap;
public class TraderStrategy implements InvestmentStrategy {

    private HashMap<Stock, Double> initialPrices;

    public TraderStrategy() {
        initialPrices = new HashMap<>();
    }
    @Override
    public int buyOrSell(StockEvent e) {
        Stock stock = e.getStock();
        double currentPrice = e.getPrice();

        if(!initialPrices.containsKey(stock)) {
            initialPrices.put(stock, currentPrice);
            return 0;
        }
        
        double initialPrice = initialPrices.get(stock);
        double change = (currentPrice - initialPrice) / initialPrice;

        if(change <= -0.04){
            return 10;
        }
        else if (change >= 0.04) {
            return -10;
        }

        return 0;



    }
}
