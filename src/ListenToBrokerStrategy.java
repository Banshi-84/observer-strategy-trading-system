import java.util.HashMap;

public class ListenToBrokerStrategy implements InvestmentStrategy, BrokerListener {

    private HashMap<Stock, String> latestAdvice;

    public ListenToBrokerStrategy(Broker broker) {
        latestAdvice = new HashMap<>();
        broker.addBrokerListener(this);
    }

    @Override
    public void update(BrokerEvent e) {
        latestAdvice.put(e.getStock(), e.getAdvice());
    }

    @Override
    public int buyOrSell(StockEvent e) {
        String advice = latestAdvice.getOrDefault(e.getStock(), BrokerEvent.HOLD);

        if (advice.equals(BrokerEvent.BUY)) {
            return 20;
        } else if (advice.equals(BrokerEvent.SELL)) {
            return -20;
        } else {
            return 0;
        }
    }
}
