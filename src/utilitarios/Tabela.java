package utilitarios;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabela {
	
	public static int popula(JTable table, ResultSet rs,int numColunaOculta){
		DefaultTableModel modelo= (DefaultTableModel)table.getModel();

		//remove linhas 
		int rowCount = modelo.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}

		try {
			Utilitario.resultSetToTableModel(rs,table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//esconde colunas com sq
		table.getColumnModel().getColumn(numColunaOculta).setMinWidth(0);
		table.getColumnModel().getColumn(numColunaOculta).setMaxWidth(0);
		
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo2= (DefaultTableModel)table.getModel();
		return modelo2.getRowCount();
	}
}
