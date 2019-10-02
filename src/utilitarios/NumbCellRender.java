package utilitarios;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class NumbCellRender {
	/**  
     * classe para mostrar a celula com formato de moeda  
     */   
    private static final long serialVersionUID = 1L;   
    private JLabel cell = null;   
    private NumberFormat formatter = NumberFormat.getCurrencyInstance( new Locale("pt","BR") ); // Locale.getDefault()   
       
    private JLabel getCell() {   
        if (cell == null)   
            cell = new JLabel();   
        return cell;   
    }   
  
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {   
        if (value != null) {   
            getCell().setText( formatter.format(value) );   
            getCell().setHorizontalAlignment(SwingConstants.RIGHT);   
        } else getCell().setText("");   
        return getCell();   
    }  
}
