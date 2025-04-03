import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Stock Market");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();

        Market m = new Market();

        Broker broker = new Broker();
        StockPanel sp = new StockPanel(4, m, broker);
        BrokerPanel bp = new BrokerPanel(broker);

        Investor i = new Investor("Bob", 2000, m);
        InvestorPanel ip = new InvestorPanel(i, broker);
        i.setStrategy(new NoActivity());

        Investor i2 = new Investor("Mary", 20000, m);
        InvestorPanel ip2 = new InvestorPanel(i2, broker);

        jp.add(sp);
        jp.add(ip);
        jp.add(ip2);
        jp.add(bp);

        frame.setContentPane(jp);
        frame.pack();
        frame.setVisible(true);
    }
}
