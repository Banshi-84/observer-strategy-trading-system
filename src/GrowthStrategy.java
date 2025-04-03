
public class GrowthStrategy implements InvestmentStrategy {
    @Override
    public int buyOrSell(StockEvent e) {
        double price = e.getPrice();

        int shares = (int) (50.0/price);
        if(shares < 1) {
            return 1;
        }
        return shares;
    }
}
