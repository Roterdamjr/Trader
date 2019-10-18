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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Estrategia;
import utilitarios.Utilitario;
import dao.EstrategiaDao;
import java.awt.GridLayout;

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
		setBounds(0, 0, 600, 250);

		//setSize(535,380);  

		//setBounds(10,10, 200, 100);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);  

		Container container = getContentPane();
		container.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Patrim\u00F4nio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(lblNewLabel);
		
		txtPatrimonio = new JTextField();
		txtPatrimonio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPatrimonio.setText("30000");
		panel.add(txtPatrimonio);
		txtPatrimonio.setColumns(5);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);

		incializaTabela();

		JScrollPane scrollPane = new JScrollPane(tblEstrategia);
		panel_1.add(scrollPane);


		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnSugerir = new JButton("Sugerir");
		btnSugerir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sugerir();

			}
			
		});
		panel_2.add(btnSugerir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		panel_2.add(btnSalvar);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.WEST);
				panel_3.setLayout(new GridLayout(8, 1, 0, 0));
		
				JButton btnInserir = new JButton("+");
				btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 28));
				btnInserir.setForeground(Color.BLUE);
				panel_3.add(btnInserir);
				
						JButton btnExcluir = new JButton("-");
						btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 28));
						btnExcluir.setForeground(Color.BLUE);
						panel_3.add(btnExcluir);
						btnExcluir.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								excluir();
							}
						});
				btnInserir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						inserir();
					}
				});

		populaTabelaEstrategia();
		
	}

	private void inserir(){
		DefaultTableModel modelo= (DefaultTableModel)tblEstrategia.getModel();
		modelo.addRow(new Object[]{null,null,null,null,null,null});
	} 

	private void excluir(){
		DefaultTableModel modelo= (DefaultTableModel)tblEstrategia.getModel();
        if (tblEstrategia.getSelectedRow() >= 0){
        	modelo.removeRow(tblEstrategia.getSelectedRow());
        	tblEstrategia.setModel(modelo);
        }else{
            JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
        }
	}

	public void sugerir(){
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo= (DefaultTableModel)tblEstrategia.getModel();
	
		int nLinhas=modelo.getRowCount();
		
		if(nLinhas>0){
			BigDecimal quantLinhas=Utilitario.converteIntParaBigDecimal(nLinhas);
			
			BigDecimal patrimonio=Utilitario.converteStringParaBigDecimal(txtPatrimonio.getText());	
		
			BigDecimal valorPorAtivo= patrimonio.divide(quantLinhas,BigDecimal.ROUND_UP);
			
			for(int i=0;i<nLinhas;i++){
				//sugere quantidades
				
				BigDecimal vlCompra=Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 2));
				BigDecimal quantidade=valorPorAtivo.divide(vlCompra,BigDecimal.ROUND_UP);
				modelo.setValueAt(quantidade,i, 6);
				
				//sugere stops
				BigDecimal start = vlCompra;
				BigDecimal stop=Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 3));;

				BigDecimal gain=start.multiply(new BigDecimal("4")).subtract(		
						stop.multiply(new BigDecimal("3"))
				);
				
				BigDecimal gainParcial=start.multiply(new BigDecimal("2")).subtract(
						stop
				);
				
				modelo.setValueAt(gain,i, 4);
				modelo.setValueAt(gainParcial,i, 5);
			}
		}
	}
	
	private void salvar(){
		new EstrategiaDao().excluir();

		DefaultTableModel modelo= (DefaultTableModel)tblEstrategia.getModel();

		for (int i = 0; i <= modelo.getRowCount()-1; i++) {

			Estrategia est= new Estrategia(
					(String)modelo.getValueAt(i, 1),
					Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 2)),
					 Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 3)),
					 Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 4)),
					 Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 5)),
					 Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 6)),
					 null
					 );	
			
			new EstrategiaDao().inserir(est);
		}
		JOptionPane.showMessageDialog(null,
		        "Dados salvos'" , //mensagem
		        "Aviso", // titulo da janela 
		        JOptionPane.INFORMATION_MESSAGE);

	}

	
	private void incializaTabela(){
		tblEstrategia = new JTable();
		tblEstrategia.setColumnSelectionAllowed(true);
		tblEstrategia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEstrategia.setFont(new Font("Tahoma", Font.PLAIN, 16));  
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
		
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo2= (DefaultTableModel)tblEstrategia.getModel();
		return modelo2.getRowCount();
	}	
	
}