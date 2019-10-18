package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import utilitarios.Utilitario;
import dao.EstrategiaDao;
import dao.OperacaoDao;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class IFrameOperacao extends JInternalFrame {
	private JTable tblEstrategia;
	private JTable tblOperacao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameOperacao frame = new IFrameOperacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IFrameOperacao() {
		setTitle("Opera\u00E7\u00F5es");
		setClosable(true);
		setBounds(0, 0, 603, 479);
		
		 JPanel panel = new JPanel();
         panel.setBackground(SystemColor.controlHighlight);  
 
         Container container = getContentPane();
         container.add(panel, BorderLayout.NORTH);
         
         JPanel panel_1 = new JPanel();
         getContentPane().add(panel_1, BorderLayout.CENTER);
         panel_1.setLayout(new GridLayout(0, 1, 5, 5));
         
         JPanel panel_3 = new JPanel();
         panel_3.setBorder(new LineBorder(Color.BLUE));
         panel_1.add(panel_3);
         
         tblEstrategia = new JTable();
         tblEstrategia.setModel(new DefaultTableModel(
         	new Object[][] {
         		{null, null, null, null, null},
         		{null, null, null, null, null},
         		{null, null, null, null, null},
         	},
         	new String[] {
         		"New column", "New column", "New column", "New column", "New column"
         	}
         ));
         
         incializaTabelaEstrategia();
         
         panel_3.setLayout(new BorderLayout(5, 5));
         JScrollPane scrollPane = new JScrollPane(tblEstrategia);
         panel_3.add(scrollPane);
         
         JPanel panel_5 = new JPanel();
         panel_3.add(panel_5, BorderLayout.SOUTH);
         
         JButton btnIncluirOperacao = new JButton("Incluir Operacao");
         btnIncluirOperacao.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		incluirOperacao();
         	}
         });
         panel_5.add(btnIncluirOperacao);
         
         JPanel panel_6 = new JPanel();
         panel_3.add(panel_6, BorderLayout.WEST);
         
         JPanel panel_8 = new JPanel();
         panel_3.add(panel_8, BorderLayout.NORTH);
         
         JPanel panel_7 = new JPanel();
         panel_3.add(panel_7, BorderLayout.EAST);
         
         
         JPanel panel_4 = new JPanel();
         panel_4.setBorder(new LineBorder(Color.BLUE));
         panel_1.add(panel_4);
         panel_4.setLayout(new BorderLayout(0, 0));

         
         incializaTabelaOperacao();
         tblOperacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
         //panel_4.add(tblOperacao);
         
         JScrollPane scrollPane_1 = new JScrollPane(tblOperacao);
         panel_4.add(scrollPane_1);
         
         JPanel panel_9 = new JPanel();
         panel_4.add(panel_9, BorderLayout.NORTH);
         
         JPanel panel_10 = new JPanel();
         panel_4.add(panel_10, BorderLayout.SOUTH);
         
         JPanel panel_11 = new JPanel();
         panel_4.add(panel_11, BorderLayout.WEST);
         
         JPanel panel_12 = new JPanel();
         panel_4.add(panel_12, BorderLayout.EAST);

         JPanel panel_2 = new JPanel();
         getContentPane().add(panel_2, BorderLayout.SOUTH);
         
         JButton button_1 = new JButton("Salvar");
         button_1.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		//salvar();
         	}
         });
         panel_2.add(button_1);
         
         
         populaTabelaEstrategia();
         populaTabelaOperacao();
         
	}

	private void incluirOperacao(){
		DefaultTableModel modeloEstrategia= (DefaultTableModel)tblEstrategia.getModel();
		DefaultTableModel modeloOperacao= (DefaultTableModel)tblOperacao.getModel();
		//modeloEstrategia.
		int numLinha=tblEstrategia.getSelectedRow();

		Object[] linha= new Object[]{
				modeloEstrategia.getValueAt(numLinha, 0),
				null,
				modeloEstrategia.getValueAt(numLinha, 2),
				modeloEstrategia.getValueAt(numLinha, 4),
				modeloEstrategia.getValueAt(numLinha, 5),
				modeloEstrategia.getValueAt(numLinha, 1),
				modeloEstrategia.getValueAt(numLinha, 3)

		};

		modeloOperacao.insertRow(modeloOperacao.getRowCount(),linha);
	}
    
	private void incializaTabelaEstrategia(){
		tblEstrategia = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblEstrategia.setColumnSelectionAllowed(false);
		tblEstrategia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEstrategia.setFont(new Font("Tahoma", Font.PLAIN, 16));  
	}

	private void incializaTabelaOperacao(){
		tblOperacao = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return column==2;
			}
		};
	}
	
	private int populaTabelaEstrategia(){
		DefaultTableModel modelo= (DefaultTableModel)tblEstrategia.getModel();

		//remove linhas 
		int rowCount = modelo.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}

		try {
			Utilitario.resultSetToTableModel(
					new EstrategiaDao().buscarTodosRSParaOperacao(),tblEstrategia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo2= (DefaultTableModel)tblEstrategia.getModel();
		return modelo2.getRowCount();
	}	

	private int populaTabelaOperacao(){
		DefaultTableModel modelo= (DefaultTableModel)tblOperacao.getModel();

		//remove linhas 
		int rowCount = modelo.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}

		try {
			Utilitario.resultSetToTableModel(
					new OperacaoDao().buscarTodosRS(),tblOperacao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo2= (DefaultTableModel)tblOperacao.getModel();
		return modelo2.getRowCount();
	}	
}
