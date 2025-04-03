import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class StockPanel extends JPanel {

    private ArrayList<StockLabel> stockLabels;
    private int size;
    private Scanner scan;
    private Market m;
    private Broker broker;

    public StockPanel(int size, Market m, Broker broker) {
        this.size = size;
        this.m = m;
        this.broker = broker;

        setPreferredSize(new Dimension(150, 400));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField fname = new JTextField("CoeExchangeDay1.txt");
        fname.setMaximumSize(new Dimension(300, 30));
        add(fname);

        JButton openButton = new JButton("Open");
        add(openButton);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    scan = new Scanner(new File("Data/" + fname.getText()));
                    for (int i = 0; i < size; i++) {
                        String[] stockData = scan.nextLine().split(" ");
                        Stock s = new Stock(stockData[0], Double.parseDouble(stockData[1]));
                        stockLabels.get(i).setStock(s);
                        m.addStock(s);
                        s.addStockListener(broker);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Bad File", "Oops", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton nextButton = new JButton("Next");
        add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] stockData = scan.nextLine().split(" ");
                m.setPrice(stockData[0], Double.parseDouble(stockData[1]));
            }
        });

        stockLabels = new ArrayList<StockLabel>();
        for (int i = 0; i < size; i++) {
            StockLabel sl = new StockLabel();
            add(sl);
            sl.setAlignmentX(Component.LEFT_ALIGNMENT);
            stockLabels.add(sl);
        }
    }
}
