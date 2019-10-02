package utilitarios;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class NumbCellEditor extends AbstractCellEditor implements TableCellEditor {
	   /**  
     * classe para editar a celula com formato de moeda  
     */   
    private static final long serialVersionUID = 1L;   
    private JFormattedTextField moeda = null;   
    private NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));   
       
    private JFormattedTextField getCell() {   
        if (moeda == null)   
            moeda = new JFormattedTextField(formatter);   
        return moeda;   
    }   
  
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {   
        getCell().setValue(value);   
        return getCell();   
    }   
  
    public Object getCellEditorValue() {   
        return getCell().getValue();   
    }   
}
