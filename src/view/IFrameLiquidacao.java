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

public class IFrameLiquidacao extends JInternalFrame {
	
	private JTable tblEmExecucao;
	private JTable tblLiquidacao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameLiquidacao frame = new IFrameLiquidacao();
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
	public IFrameLiquidacao() {
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
         
         JButton btnLiquidar = new JButton("Liquidar");
         btnLiquidar.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		incluirLiquidacao();
         	}
         });
         panel_5.add(btnLiquidar);
         
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
         
         JPanel panel_4 = new JPanel();
         panel_4.setBorder(new LineBorder(Color.BLUE));
         panel_1.add(panel_4);
         panel_4.setLayout(new BorderLayout(0, 0));
         
         
         /*
         tabela
         */
         incializaTabelaLiquidacao();
         JScrollPane scrollPane_1 = new JScrollPane(tblLiquidacao);
         panel_4.add(scrollPane_1, BorderLayout.CENTER);
         /*   */
         
         JPanel panel_9 = new JPanel();
         panel_4.add(panel_9, BorderLayout.NORTH);
         
         JLabel lblNewLabel_1 = new JLabel("Ordens Liquidadas");
         lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblNewLabel_1.setForeground(Color.BLUE);
         panel_9.add(lblNewLabel_1);
         
         JPanel panel_10 = new JPanel();
         panel_4.add(panel_10, BorderLayout.SOUTH);
         
         JPanel panel_11 = new JPanel();
         panel_4.add(panel_11, BorderLayout.EAST);
         
         JPanel panel_12 = new JPanel();
         panel_4.add(panel_12, BorderLayout.WEST);

         JPanel panel_2 = new JPanel();
         getContentPane().add(panel_2, BorderLayout.SOUTH);
         
         JButton button_1 = new JButton("Salvar");
         button_1.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		salvar();
         	}
         });
         
         JButton btnSair = new JButton("Sair");
         btnSair.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		dispose();
         	}
         });
         
         JButton btnRefresh = new JButton("Refresh");
         panel_2.add(btnRefresh);
         btnRefresh.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {         	
         		populaTabelaExecucao();
 
         	}
         });
         panel_2.add(btnSair);
         panel_2.add(button_1);
   
         populaTabelaExecucao();
         populaTabelaLiquidacao();
	}

	private void incluirLiquidacao(){
		DefaultTableModel modeloEmExecucao= (DefaultTableModel)tblEmExecucao.getModel();
		DefaultTableModel modeloLiquidacao= (DefaultTableModel)tblLiquidacao.getModel();
		
		//insere a operação 
		int numLinha=tblEmExecucao.getSelectedRow();

		Object[] linha= new Object[]{
				Utilitario.buscaDataAtual(),
				new String(),
				modeloEmExecucao.getValueAt(numLinha, 0),
				modeloEmExecucao.getValueAt(numLinha, 1),
				modeloEmExecucao.getValueAt(numLinha, 3),
				modeloEmExecucao.getValueAt(numLinha, 4)
		};

		modeloLiquidacao.insertRow(modeloLiquidacao.getRowCount(),linha);
		
		//exclui estrategia da table origem
		modeloEmExecucao.removeRow(numLinha);
	}
	
	private void salvar(){
		
		try{ 							
			DefaultTableModel modelo= (DefaultTableModel)tblLiquidacao.getModel();
	
			for (int i = 0; i <= modelo.getRowCount()-1; i++) {
				if(modelo.getValueAt(i, 5).equals("E")){					
					//ATUALIZA OPERAÇÕES		
					
					//sq
					Integer itg = (Integer)modelo.getValueAt(i, 4);
					int sq=  itg.intValue();

					//data da venda
					String Data=(String)modelo.getValueAt(i, 0);				
					
					//vl_corrente
					BigDecimal valorCorrente = Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 1));						

					//atualiza no banco
					new OperacaoDao().updateParaLiquidada(sq, Data, valorCorrente);				
				}
			}
			
			//refresh na tabela para preencher o sq_opreracao
			populaTabelaExecucao();
			
			JOptionPane.showMessageDialog(null, "Dados salvos" , "Aviso", 
			        JOptionPane.INFORMATION_MESSAGE);
			
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar" , "Aviso", 
			        JOptionPane.INFORMATION_MESSAGE);
        }
	}    
	
	private void incializaTabela(){
		tblEmExecucao = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblEmExecucao.setColumnSelectionAllowed(false);
		tblEmExecucao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEmExecucao.setFont(new Font("Tahoma", Font.PLAIN, 16));  
	}

	private void incializaTabelaLiquidacao(){
		//editavel a o valor corrente
		tblLiquidacao = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return column==1;
			}
		};
		tblLiquidacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblLiquidacao.setRowSelectionAllowed(false);
	}

	
	private int populaTabelaExecucao(){
		int ret=0;
		
		try {
			ret= Tabela.popula(tblEmExecucao, 
					new OperacaoDao().buscarExecutadasNaLiquidacao(),
					new int[]{3,4}
			);	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}	

	private int populaTabelaLiquidacao(){
		int ret=0;
		
		try {
			ret= Tabela.popula(tblLiquidacao, 
					new OperacaoDao().buscarLiquidadas(),
					new int[]{4,5}
			);	
		} catch (Exception e) {	e.printStackTrace();
		}
		//muda cor de células editáveis
		Tabela.ajustaCor(tblLiquidacao,
				new int[]{0,1}
		);
		return ret;
	}	

}
