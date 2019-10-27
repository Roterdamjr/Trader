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

public class IFrameAcompanharExecucao extends JInternalFrame {
	
	private JTable tblEmExecucao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameAcompanharExecucao frame = new IFrameAcompanharExecucao();
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
	public IFrameAcompanharExecucao() {
		setTitle("Acompanhar Execucao");
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
         		populaTabela();
 
         	}
         });
         panel_2.add(btnSair);
         panel_2.add(button_1);
   
         populaTabela();


	}

	
	
	private void salvar(){
		
		try{ 				
			
			DefaultTableModel modelo= (DefaultTableModel)tblEmExecucao.getModel();
	
			for (int i = 0; i <= modelo.getRowCount()-1; i++) {
				if(modelo.getValueAt(i, 8).equals("A")){					
					// atualiza operações					
					BigDecimal bd = (BigDecimal)modelo.getValueAt(i, 7);
					int sq_estrategia= Utilitario.converteBigDecimalParaInt(bd);
					String DataDeExcucao=(String)modelo.getValueAt(i, 0);				
					
					new OperacaoDao().updateParaExecutada(sq_estrategia,DataDeExcucao,null);				

					// EXCLUI ESTRATEGIAS
					new EstrategiaDao().excluir(sq_estrategia);
				}
			}
			
			//refresh na tabela para preencher o sq_opreracao
			populaTabela();
			
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


	
	private int populaTabela(){
		int ret=0;
		
		try {
			ret= Tabela.popula(tblEmExecucao, 
					new OperacaoDao().buscarExecutadasParaAcompanhar(),
					new int[]{8}
			);	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}	



}
