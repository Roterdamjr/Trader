package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import utilitarios.Tabela;
import utilitarios.Utilitario;
import dao.EstrategiaDao;
import dao.OperacaoDao;

public class IFrameAcompanhar extends JInternalFrame {
	
	private JTable tblEmExecucao;
	private JButton btnAtualiza;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameAcompanhar frame = new IFrameAcompanhar();
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
	public IFrameAcompanhar() {
		setTitle("Liquida\u00E7\u00E3o");
		setClosable(true);
		setBounds(0, 0, 800, 600);
		
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
 
         panel_3.setLayout(new BorderLayout(5, 5));
         
         /*
         tabela
         */
         incializaTabela();
         JScrollPane scrollPane = new JScrollPane(tblEmExecucao);
         panel_3.add(scrollPane);
        /*   */
         JPanel panel_5 = new JPanel();
         panel_3.add(panel_5, BorderLayout.SOUTH);
         
         JPanel panel_8 = new JPanel();
         panel_3.add(panel_8, BorderLayout.NORTH);
         
         JLabel lblNewLabel = new JLabel("Ordens em Execu\u00E7\u00E3o");
         lblNewLabel.setForeground(Color.BLUE);
         lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
         panel_8.add(lblNewLabel);
         
         JPanel panel_6 = new JPanel();
         panel_3.add(panel_6, BorderLayout.WEST);
         
         JPanel panel_7 = new JPanel();
         panel_3.add(panel_7, BorderLayout.EAST);
                 

         JPanel panel_2 = new JPanel();
         getContentPane().add(panel_2, BorderLayout.SOUTH);
         
         JButton btnSair = new JButton("Sair");
         btnSair.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		dispose();
         	}
         });
         
         btnAtualiza = new JButton("Atualiza");
         panel_2.add(btnAtualiza);
         btnAtualiza.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {         	
         		atualiza();

         	}
         });
         panel_2.add(btnSair);
   
         populaTabelaExecucao();
 
	}
	
	private void atualiza(){
		DefaultTableModel modelo= (DefaultTableModel)tblEmExecucao.getModel();
		
		for (int i = 0; i <= modelo.getRowCount()-1; i++) {

			BigDecimal lucro =	(getValorCorrente(i).subtract(getValorCompra(i))).
						multiply(getQuantidade(i));
			
			BigDecimal andamento=(
					getValorCorrente(i).subtract(getStop(i))
					).divide(
						getGainParcial(i).subtract(getStop(i))	
						,BigDecimal.ROUND_UP
						);
			andamento.multiply(new BigDecimal("100"));				

			modelo.setValueAt(andamento,i, 4);
			modelo.setValueAt(lucro,i, 5);
			
		}
	}
	
	private void incializaTabela(){
		tblEmExecucao = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return column==3;
			}
		};

		tblEmExecucao.setColumnSelectionAllowed(false);
		tblEmExecucao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEmExecucao.setFont(new Font("Tahoma", Font.PLAIN, 16));  
	}

	
	private int populaTabelaExecucao(){
		int ret=0;
		
		try {
			ret= Tabela.popula(tblEmExecucao, 
					new OperacaoDao().buscarExecutadasParaAcompanhar(),
					new int[]{10}
			);	

		} catch (Exception e) {	e.printStackTrace();
		}
		
		return ret;
	}	
	
	private BigDecimal  getValorCorrente(int numLinha){
		return Utilitario.converteParaBigDecimal(tblEmExecucao.getValueAt(numLinha, 3));
	}
	private BigDecimal  getValorCompra(int numLinha){
		return Utilitario.converteParaBigDecimal(tblEmExecucao.getValueAt(numLinha, 2));
	}
	private BigDecimal  getQuantidade(int numLinha){
		return Utilitario.converteParaBigDecimal(tblEmExecucao.getValueAt(numLinha,1));
	}
	private BigDecimal  getStop(int numLinha){
		return Utilitario.converteParaBigDecimal(tblEmExecucao.getValueAt(numLinha,7));
	}
	private BigDecimal  getGainParcial(int numLinha){
		return Utilitario.converteParaBigDecimal(tblEmExecucao.getValueAt(numLinha,8));
	}
}
