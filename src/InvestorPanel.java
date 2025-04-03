import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvestorPanel extends JPanel implements InvestorListener {

    JLabel name;
    JLabel fundsAvailable;
    JTextArea portfolio;
    JLabel netWorth;
    JTextField symb;
    JTextField qty;
    JButton buyBtn;
    JButton sellBtn;
    Investor i;

    public InvestorPanel(Investor i, Broker broker) {
        int pWidth = 200;

        this.i = i;
        i.addInvestorListener(this);
        name = new JLabel();
        fundsAvailable = new JLabel();
        portfolio = new JTextArea();
        portfolio.setEditable(false);
        netWorth = new JLabel();
        symb = new JTextField();
        symb.setPreferredSize(new Dimension(pWidth, 20));
        qty = new JTextField();
        qty.setPreferredSize(new Dimension(pWidth, 20));
        setPreferredSize(new Dimension(pWidth, 400));
        refresh();

        buyBtn = new JButton("Buy");
        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sym = symb.getText();
                int numshares = Integer.parseInt(qty.getText());

                try {
                    i.buy(sym, numshares);
                } catch (TradeException ex) {
                    System.out.println(ex.getMessage());
                }
                refresh();
            }
        });

        sellBtn = new JButton("Sell");
        sellBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sym = symb.getText();
                int numshares = Integer.parseInt(qty.getText());

                try {
                    i.sell(sym, numshares);
                } catch (TradeException ex) {
                    System.out.println(ex.getMessage());
                }
                refresh();
            }
        });

        add(name);
        add(symb);
        add(qty);
        add(buyBtn);
        add(sellBtn);
        add(fundsAvailable);
        add(portfolio);
        add(netWorth);
        add(new StrategyPanel(i, broker));
    }

    public void refresh() {
        name.setText(i.getName());
        fundsAvailable.setText(String.format("Funds Available: %.2f", i.getFundsAvailable()));
        portfolio.setText(i.getPorfolioString());
        netWorth.setText(String.format("Total Value: %.2f", i.getNetWorth()));
    }

    @Override
    public void update() {
        System.out.println("Refreshing");
        refresh();
    }
}
