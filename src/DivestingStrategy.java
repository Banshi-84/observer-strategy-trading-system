public class DivestingStrategy implements InvestmentStrategy {

    private Investor investor;

    public DivestingStrategy(Investor investor) {
        this.investor= investor;
    }

    @Override
    public int buyOrSell(StockEvent e) {
        Stock stock = e.getStock();
        double price = e.getPrice();

        int shareToSell = (int)(100.0/price);
        if(shareToSell < 1) {
            shareToSell = 1;
        }

        double ownedShares = investor.getPortfolio().getOrDefault(stock, 0.0);
        if(ownedShares < shareToSell) {
            return -(int)ownedShares;
        }
        return -shareToSell;
    }
}
