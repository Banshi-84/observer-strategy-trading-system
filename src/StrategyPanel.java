import javax.swing.*;

public class StrategyPanel extends JPanel {

    public StrategyPanel(Investor investor, Broker broker) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Strategy Selector"));

        JRadioButton noActivity = new JRadioButton("No Activity");
        JRadioButton growth = new JRadioButton("Growth");
        JRadioButton divesting = new JRadioButton("Divesting");
        JRadioButton trader = new JRadioButton("Trader");
        JRadioButton listen = new JRadioButton("Listen to Broker");

        ButtonGroup group = new ButtonGroup();
        group.add(noActivity);
        group.add(growth);
        group.add(divesting);
        group.add(trader);
        group.add(listen);

        add(noActivity);
        add(growth);
        add(divesting);
        add(trader);
        add(listen);

        noActivity.setSelected(true);
        investor.setStrategy(new NoActivity());

        noActivity.addActionListener(e -> investor.setStrategy(new NoActivity()));
        growth.addActionListener(e -> investor.setStrategy(new GrowthStrategy()));
        divesting.addActionListener(e -> investor.setStrategy(new DivestingStrategy(investor)));
        trader.addActionListener(e -> investor.setStrategy(new TraderStrategy()));
        listen.addActionListener(e -> investor.setStrategy(new ListenToBrokerStrategy(broker)));
    }
}
