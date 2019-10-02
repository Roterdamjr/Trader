package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import utilitarios.Utilitario;
import dao.EstrategiaDao;

import javax.swing.JTextField;

public class IFrameEstrategias extends JInternalFrame {
	private JTable tblEstrategia;
	private JTextField txtPatrimonio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameEstrategias frame = new IFrameEstrategias();
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
	public IFrameEstrategias() {
		setClosable(true);
		setTitle("Estrat\u00E9gias");
		setBounds(30, 30, 450, 300);

		setSize(500,300);  

		//setBounds(10,10, 200, 100);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);  

		Container container = getContentPane();
		container.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Patrim\u00F4nio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.RED);
		panel.add(lblNewLabel);
		
		txtPatrimonio = new JTextField();
		txtPatrimonio.setText("30000");
		panel.add(txtPatrimonio);
		txtPatrimonio.setColumns(10);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);

		incializaTabela();

		JScrollPane scrollPane = new JScrollPane(tblEstrategia);
		panel_1.add(scrollPane);


		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluir();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
			
		});
		panel_2.add(btnRefresh);
		panel_2.add(btnExcluir);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novo();
			}
		});
		panel_2.add(btnNovo);

		populaTabelaEstrategia();
	}

	private void novo(){
		DialogEstrategia dlg= new DialogEstrategia();
		dlg.setVisible(true);
		populaTabelaEstrategia();
	} 

	private void excluir(){

		BigDecimal cel=(BigDecimal)tblEstrategia.getValueAt(tblEstrategia.getSelectedRow(),0);
		
		int id= Utilitario.converteBigDecimalParaInt(cel);
		new EstrategiaDao().excluir(id);
		
		populaTabelaEstrategia();
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
					new EstrategiaDao().buscarTodosRS(),tblEstrategia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//esconde colunas com sq
		tblEstrategia.getColumnModel().getColumn(0).setMinWidth(0);
		tblEstrategia.getColumnModel().getColumn(0).setMaxWidth(0);
				
		DefaultTableModel modelo2= (DefaultTableModel)tblEstrategia.getModel();
		return modelo2.getRowCount();
	}
	
	private void refresh(){
		int quantLinhas=populaTabelaEstrategia();
		
		BigDecimal patrimonio=Utilitario.converteStringParaBigDecimal(txtPatrimonio.getText());

		String quantLinhasBD=String.valueOf(quantLinhas);
		BigDecimal valorPorAtivo= patrimonio.divide(new BigDecimal(quantLinhasBD));
		System.out.println(valorPorAtivo);
	}
	
	private void incializaTabela(){

		tblEstrategia = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEstrategia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEstrategia.setFont(new Font("Tahoma", Font.PLAIN, 11));  
		


	}
}
