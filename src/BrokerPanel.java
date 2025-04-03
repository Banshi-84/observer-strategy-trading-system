import java.awt.*;
import javax.swing.*;

public class BrokerPanel extends JPanel implements BrokerListener {

    private JLabel adviceLabel;

    public BrokerPanel(Broker broker) {
        setPreferredSize(new Dimension(200, 50));
        setBorder(BorderFactory.createTitledBorder("Broker Advice"));

        adviceLabel = new JLabel("Waiting for signal...");

        add(adviceLabel);
        broker.addBrokerListener(this);
    }

    @Override
    public void update(BrokerEvent e) {
        String msg = e.getAdvice() + " " + e.getStock().getSymbol();
        adviceLabel.setText(msg);
    }
}
