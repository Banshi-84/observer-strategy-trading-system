
import java.util.HashMap;
import java.util.HashSet;

public class Broker implements StockListener, BrokerSource {

    private HashMap<Stock, Integer> streaks;
    private HashMap<Stock, Double> lastPrices;
    private HashSet<BrokerListener> brokerListeners;

    public Broker() {
        streaks = new HashMap<>();
        lastPrices = new HashMap<>();
        brokerListeners = new HashSet<>();
    }

    @Override
    public void update(StockEvent e) {
        System.out.println("Broker received event for: " + e.getStock().getSymbol() + " $" + e.getPrice());

        Stock s = e.getStock();
        double newPrice = e.getPrice();
        double oldPrice = lastPrices.getOrDefault(s, newPrice);

        int prevStreak = streaks.getOrDefault(s, 0);
        int streak;
        String advice = BrokerEvent.HOLD;

        if (newPrice > oldPrice) {
            streak = (prevStreak >= 0) ? prevStreak + 1 : 1;
            if (prevStreak <= -3) {
                advice = BrokerEvent.BUY;
                System.out.println("BUY " + s.getSymbol() + " !!!");
            }

        } else if (newPrice < oldPrice) {
            streak = (prevStreak <= 0) ? prevStreak - 1 : -1;
            if (prevStreak >= 3) {
                advice = BrokerEvent.SELL;
                System.out.println("SELL " + s.getSymbol() + " !!!!");
            }

        } else {
            streak = 0;
        }

        System.out.println("Streak for " + s.getSymbol() + ": " + streak);
        System.out.println("Sending advice: " + advice + " for " + s.getSymbol());

        streaks.put(s, streak);
        lastPrices.put(s, newPrice);

        BrokerEvent be = new BrokerEvent(advice, s);
        updateBrokerListeners(be);
    }



    @Override
    public void addBrokerListener(BrokerListener bl) {
        brokerListeners.add(bl);
    }

    @Override
    public void removeBrokerListener(BrokerListener bl) {
        brokerListeners.remove(bl);
    }

    @Override
    public void updateBrokerListeners(BrokerEvent e) {
        for (BrokerListener bl : brokerListeners) {
            bl.update(e);
        }
    }

}
