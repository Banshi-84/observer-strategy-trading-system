public class StockEvent {


    private Stock stock;
    private double price;
    public StockEvent(Stock s, double price) {

        this.stock = s;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }
}
