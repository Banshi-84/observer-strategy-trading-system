public class BrokerEvent {

    public static final String BUY = "BUY!";
    public static final String SELL = "SELL!";
    public static final String HOLD = "Hold";

    private String advice;
    private Stock stock;

    public BrokerEvent(String advice, Stock stock) {
        this.advice = advice;
        this.stock = stock;
    }

    public String getAdvice() {
        return advice;
    }

    public Stock getStock() {
        return stock;
    }
}
