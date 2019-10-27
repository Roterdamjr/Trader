package utilitarios;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tabela {
	
	public static int popula(JTable table, ResultSet rs,int[] numColunaOculta){
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
		
		//esconde colunas 
		for(int i=0;i<=numColunaOculta.length-1;i++){
			table.getColumnModel().getColumn(numColunaOculta[i]).setMinWidth(0);
			table.getColumnModel().getColumn(numColunaOculta[i]).setMaxWidth(0);
		}
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo2= (DefaultTableModel)table.getModel();
		return modelo2.getRowCount();
	}

	public static void ajustaCor(JTable table, int[]colunas){
		DefaultTableCellRenderer coluna = new DefaultTableCellRenderer(); 
		coluna.setForeground(Color.WHITE); 
		coluna.setBackground(Color.BLUE);
		for(int i=0;i<=colunas.length-1;i++){
			table.getColumnModel().getColumn(colunas[i]).setCellRenderer(coluna);
			table.getColumnModel().getColumn(colunas[i]).setCellRenderer(coluna);
		}

	}}
