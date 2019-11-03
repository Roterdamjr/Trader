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

public class IFrameLancarExecucao extends JInternalFrame {
	
	private JTable tblAbertas;
	//private ArrayList linhasParaUpdate= new ArrayList();
	private JTable tblExecutadas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameLancarExecucao frame = new IFrameLancarExecucao();
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
	public IFrameLancarExecucao() {
		setTitle("Lan\u00E7ar Execu\u00E7\u00E3o");
		setClosable(true);
		setBounds(100, 10, 800, 600);
		
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
         tabela Abertas
         */
         incializaTabelaAbertas();
         JScrollPane scrollPane = new JScrollPane(tblAbertas);
         panel_3.add(scrollPane);
         
         JPanel panel_5 = new JPanel();
         panel_3.add(panel_5, BorderLayout.SOUTH);
         
         JButton btnIncluirOrdem = new JButton("Incluir Ordem");
         btnIncluirOrdem.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		incluirOrdem();
         	}
         });
         panel_5.add(btnIncluirOrdem);
         
         JPanel panel_8 = new JPanel();
         panel_3.add(panel_8, BorderLayout.NORTH);
         
         JLabel lblNewLabel = new JLabel("Ordens Abertas");
         lblNewLabel.setForeground(Color.BLUE);
         lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
         panel_8.add(lblNewLabel);
         
         JPanel panel_6 = new JPanel();
         panel_3.add(panel_6, BorderLayout.WEST);
         
         JPanel panel_7 = new JPanel();
         panel_3.add(panel_7, BorderLayout.EAST);
         
         JPanel panel_4 = new JPanel();
         panel_1.add(panel_4);
         panel_4.setLayout(new BorderLayout(0, 0));
         /*
         tabela Execu��o
         */
         incializaTabelaExecutadas();        
         JScrollPane scrollPane_1 = new JScrollPane(tblExecutadas);
         /*   */
         
         panel_4.add(scrollPane_1, BorderLayout.CENTER);
                  
         JPanel panel_9 = new JPanel();
         panel_4.add(panel_9, BorderLayout.NORTH);
         
         JLabel lblNewLabel_1 = new JLabel("Ordens Executadas");
         lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblNewLabel_1.setForeground(Color.BLUE);
         panel_9.add(lblNewLabel_1);
         
         JPanel panel_10 = new JPanel();
         panel_4.add(panel_10, BorderLayout.WEST);
         
         JPanel panel_11 = new JPanel();
         panel_4.add(panel_11, BorderLayout.EAST);
         
         JPanel panel_12 = new JPanel();
         panel_4.add(panel_12, BorderLayout.SOUTH);

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
         		populaTabelaAbertas();
         		populaTabelaExecucao();
         	}
         });
         panel_2.add(btnSair);
         panel_2.add(button_1);
   
         tblExecutadas.setColumnSelectionAllowed(false);
         tblExecutadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         tblExecutadas.setFont(new Font("Tahoma", Font.PLAIN, 16));  
   
         populaTabelaAbertas();
         populaTabelaExecucao();

	}

	private void incluirOrdem(){
		
		DefaultTableModel modeloAbertas= (DefaultTableModel)tblAbertas.getModel();
		DefaultTableModel modeloExecucao= (DefaultTableModel)tblExecutadas.getModel();
		
		//insere a opera��o 
		int numLinha=tblAbertas.getSelectedRow();

		Object[] linha= new Object[]{
				Utilitario.buscaDataAtual(),
				modeloAbertas.getValueAt(numLinha, 0),
				modeloAbertas.getValueAt(numLinha, 1),
				modeloAbertas.getValueAt(numLinha, 2),
				modeloAbertas.getValueAt(numLinha, 3),
				modeloAbertas.getValueAt(numLinha, 4),
				modeloAbertas.getValueAt(numLinha, 5),
				modeloAbertas.getValueAt(numLinha, 6),
				modeloAbertas.getValueAt(numLinha, 7),
				modeloAbertas.getValueAt(numLinha, 1)
		};
		
		modeloExecucao.insertRow(modeloExecucao.getRowCount(),linha);
		
		//exclui linha da table origem
		modeloAbertas.removeRow(numLinha);
	}
	
	private void salvar(){
		
		try{ 				
			
			DefaultTableModel modelo= (DefaultTableModel)tblExecutadas.getModel();
	
			for (int i = 0; i <= modelo.getRowCount()-1; i++) {
				if(modelo.getValueAt(i, 8).equals("A")){					
					//ATUALIZA OPERA��ES		
					
					//sq_operacao
					Integer itg = (Integer)modelo.getValueAt(i, 7);
					int sq=  itg.intValue();
					
					//data da execu��o
					String DataDeExcucao=(String)modelo.getValueAt(i, 0);				
					
					//vl_corrente
					BigDecimal valorCorrente = Utilitario.converteParaBigDecimal(							
							modelo.getValueAt(i, 9)	);

					//atualiza no banco
					new OperacaoDao().updateParaExecutada(sq,DataDeExcucao,valorCorrente);				

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
	
	private void incializaTabelaAbertas(){
		tblAbertas = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblAbertas.setColumnSelectionAllowed(false);
		tblAbertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAbertas.setFont(new Font("Tahoma", Font.PLAIN, 16));  
	}

	private void incializaTabelaExecutadas(){
		//premite edi��o na data e valor de compra
		tblExecutadas = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return (column==0 || column==2);
			}
		};
		tblExecutadas.setRowSelectionAllowed(false);
		//tblExecutadas = new JTable();
	}
	
	private int populaTabelaAbertas(){
		int ret=0;
		
		try {
			ret= Tabela.popula(tblAbertas, 
					new OperacaoDao().buscarAbertas(),				
					new int[]{6,7}
			);	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}	

	private int populaTabelaExecucao(){
		int ret=0;
		
		try {
			ret= Tabela.popula(tblExecutadas, 
					new OperacaoDao().buscarExecutadas(),
					new int[]{7,8,9}			
			);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//muda cor de c�lulas edit�veis
		Tabela.ajustaCor(tblExecutadas,
				new int[]{0,2}
		);
		
		return ret;
	}

}
