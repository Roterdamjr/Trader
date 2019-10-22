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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Operacao;
import utilitarios.Utilitario;
import dao.EstrategiaDao;
import dao.OperacaoDao;
import javax.swing.JLabel;

public class IFrameLancarExecucao extends JInternalFrame {
	private JTable tblEstrategia;
	private JTable tblAbertas;
	private ArrayList<BigDecimal> sqParaExcluir= new ArrayList<BigDecimal>();
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
		setTitle("Ln\u00E7ar Execu\u00E7\u00E3o");
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
         
         incializaTabelaAbertas();
         
         panel_3.setLayout(new BorderLayout(5, 5));
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
         
         incializaTabelaExecutadas();
         JScrollPane scrollPane_1 = new JScrollPane(tblExecutadas);
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

         
         incializaTabelaExecutadas();

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
         	}
         });
         panel_2.add(btnSair);
         panel_2.add(button_1);
         
         
         populaTabelaAbertas();
         populaTabelaExecucao();
         
	}

	private void incluirOrdem(){
		
		DefaultTableModel modeloAbertas= (DefaultTableModel)tblAbertas.getModel();
		DefaultTableModel modeloExecucao= (DefaultTableModel)tblAbertas.getModel();
		
		//insere a operação 
		int numLinha=tblAbertas.getSelectedRow();

		Object[] linha= new Object[]{
				modeloAbertas.getValueAt(numLinha, 0),
				modeloAbertas.getValueAt(numLinha, 1),
				modeloAbertas.getValueAt(numLinha, 2),
				modeloAbertas.getValueAt(numLinha, 3),
				modeloAbertas.getValueAt(numLinha, 4),
				modeloAbertas.getValueAt(numLinha, 5),
				modeloAbertas.getValueAt(numLinha, 6)
		};

		modeloExecucao.insertRow(modeloExecucao.getRowCount(),linha);
				
		//marca estrategia para exclusão no BD
		BigDecimal bd = (BigDecimal)modeloAbertas.getValueAt(numLinha, 7);		
		sqParaExcluir.add(bd);
		
		//exclui estrategia da table
		modeloAbertas.removeRow(numLinha);
	}
	
	private void salvar(){
		
	/*	try{ 				
			// inclui operações
			DefaultTableModel modelo= (DefaultTableModel)tblOperacao.getModel();
	
			for (int i = 0; i <= modelo.getRowCount()-1; i++) {
				if(modelo.getValueAt(i, 6)==null){ 
					Operacao est= new Operacao();
					est.setAtivo((String)modelo.getValueAt(i, 0));
					est.setStart(Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 1)));
					est.setStop(Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 2)));
					est.setGainParcial(Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 3)));
					est.setQuantidade(Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 4)));
					est.setGain(Utilitario.converteParaBigDecimal(modelo.getValueAt(i, 5)));

					new OperacaoDao().inserir(est);
				}
			}
			
			// exclui Estrategias
			for(BigDecimal obj:sqEstrategiasparaExcluir){
				int sq_estrategia= Utilitario.converteBigDecimalParaInt(obj);
				new EstrategiaDao().excluir(sq_estrategia);
			}
		
			//refresh na tabela para preencher o _sq_opreracao
			populaTabelaOperacao();
			
			JOptionPane.showMessageDialog(null, "Dados salvos" , "Aviso", 
			        JOptionPane.INFORMATION_MESSAGE);
			
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar" , "Aviso", 
			        JOptionPane.INFORMATION_MESSAGE);
        }*/
	}
    
/*	private boolean dadosValidos(){

		DefaultTableModel modelo= (DefaultTableModel)tblOperacao.getModel();
		
		for (int i = 0; i <= modelo.getRowCount()-1; i++) {
			if(modelo.getValueAt(i, 1)==null){
				JOptionPane.showMessageDialog(null, "Valor corrente nulo" , "Aviso", 
				        JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		
		return true;
	}*/
	
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
		tblExecutadas = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblAbertas.setColumnSelectionAllowed(false);
		tblAbertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAbertas.setFont(new Font("Tahoma", Font.PLAIN, 16));  
	}
	
	private int populaTabelaAbertas(){
		DefaultTableModel modelo= (DefaultTableModel)tblAbertas.getModel();

		//remove linhas 
		int rowCount = modelo.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}

		try {
			Utilitario.resultSetToTableModel(
					new OperacaoDao().buscarTodosRS(),
					tblAbertas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//esconde colunas com sq
		tblAbertas.getColumnModel().getColumn(6).setMinWidth(0);
		tblAbertas.getColumnModel().getColumn(6).setMaxWidth(0);
		
		//retorna o numero de linhas da tablea		
		DefaultTableModel modelo2= (DefaultTableModel)tblAbertas.getModel();
		return modelo2.getRowCount();
	}	

	private int populaTabelaExecucao(){
		return 0;
	}

}
