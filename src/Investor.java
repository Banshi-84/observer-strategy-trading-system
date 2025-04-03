
import java.util.HashMap;
import java.util.HashSet;

public class Investor implements StockListener{
    private String name;
    private double fundsAvailable;
    private HashMap<Stock, Double> portfolio;
    private Market m;
    private HashSet<InvestorListener> listeners;
    private InvestmentStrategy strategy;




    public Investor(String name, double fundsAvailable, Market m) {
        this.name = name;
        this.fundsAvailable = fundsAvailable;
        this.m = m;
        portfolio = new HashMap<Stock, Double>();
        listeners = new HashSet<InvestorListener>();
    }

    public void buy(String sym, int numShares) throws TradeException{
        double v;
        Stock s = m.getStock(sym);
        if (s != null) {
            double transaction = s.getPrice() * numShares;
            if (transaction > fundsAvailable) {
                throw new TradeException("Insufficient Funds");
            }
            fundsAvailable -= s.getPrice() * numShares;
            v = portfolio.getOrDefault(s, 0.0);
            portfolio.put(s, v + numShares);

            s.addStockListener(this);
        }
        else{
            throw new TradeException("Bad Stock Symbol");
        }
    }
    public void sell(String sym, int numShares) throws TradeException{
        Stock s = m.getStock(sym);
        if (s != null) {
            Double sharesAvail = portfolio.get(s);

            if (sharesAvail == null || numShares > sharesAvail) {
                throw new TradeException("Not enough shares");
            }
            portfolio.put(s, sharesAvail - numShares);
            fundsAvailable += numShares * s.getPrice();
        }
        else{
            throw new TradeException("Bad Stock Symbol");
        }
    }

    public double getNetWorth() {
        double nw = fundsAvailable;
        for (Stock s: portfolio.keySet()){
            nw += s.getPrice() * portfolio.get(s);
        }
        System.out.println("Net Worth: "+ nw);
        return nw;
    }

    @Override
    public void update(StockEvent e) {
        if (strategy != null) {
            int recommendation = strategy.buyOrSell(e);
            try {
                if (recommendation > 0) {
                    buy(e.getStock().getSymbol(), recommendation);
                } else if (recommendation < 0) {
                    sell(e.getStock().getSymbol(), -recommendation);
                }
            } catch (TradeException ex) {
                System.out.println("Strategy trade failed: " + ex.getMessage());
            }
        }

        updateInvestorListeners();
    }


    public String getName() {
        return name;
    }

    public double getFundsAvailable() {
        return fundsAvailable;
    }

    public void setStrategy(InvestmentStrategy strategy) {
        this.strategy = strategy;
    }
    

    public HashMap<Stock, Double> getPortfolio() {
        return portfolio;
    }
    public String getPorfolioString(){
        String ps= "";
        String name, shrs, value;
        for (Stock s: portfolio.keySet()){
            name = s.getSymbol();
            shrs = " shares: " + portfolio.get(s);
            value = " value: "+ String.format("%.2f",s.getPrice()* portfolio.get(s));

            ps += name + shrs + value + "\n";

        }
        return ps;
    }

    public void addInvestorListener(InvestorListener il){
        listeners.add(il);
    }
    public void removeInvestorListener(InvestorListener il){
        listeners.remove(il);
    }
    public void updateInvestorListeners(){
        for (InvestorListener il: listeners){
            il.update();
        }
    }

}
