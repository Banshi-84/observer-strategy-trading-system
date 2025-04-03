import javax.swing.*;
import java.awt.*;

public class StockLabel extends JPanel implements StockListener{

    JLabel stockSym;
    JLabel stockPrice;

    public StockLabel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(50,30));
        stockSym = new JLabel("");
        stockPrice = new JLabel("");
        add(stockSym);
        add(Box.createRigidArea(new Dimension(20,0)));
        add(stockPrice);
    }

    public StockLabel(Stock s) {
        super();
        setStock(s);
    }

    public void setStock(Stock s) {
        s.addStockListener(this);
        stockSym.setText(s.getSymbol());
        stockPrice.setText(String.format("%.2f",s.getPrice()));
    }


    @Override
    public void update(StockEvent e) {
       System.out.println("price "+e.getPrice());
       stockPrice.setText(String.format("%.2f",e.getPrice()));
    }
}
