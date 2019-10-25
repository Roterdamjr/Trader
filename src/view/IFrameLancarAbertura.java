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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Operacao;
import utilitarios.Tabela;
import utilitarios.Utilitario;
import dao.EstrategiaDao;
import dao.OperacaoDao;

public class IFrameLancarAbertura extends JInternalFrame {
	private JTable tblEstrategia;
	private JTable tblOperacao;
	private ArrayList<BigDecimal> sqEstrategiasparaExcluir= new ArrayList<BigDecimal>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IFrameLancarAbertura frame = new IFrameLancarAbertura();
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
	public IFrameLancarAbertura() {
		setTitle("Lan\u00E7ar Abertura");
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
         
         JButton btnIncluirOperacao = new JButton("Abrir Ordem");
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
         
         JLabel lblNewLabel = new JLabel("Estrategias");
         lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
         lblNewLabel.setForeground(Color.BLUE);
         panel_8.add(lblNewLabel);
         
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
         
         JLabel lblNewLabel_1 = new JLabel("Ordens Abertas");
         lblNewLabel_1.setForeground(Color.BLUE);
         lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
         panel_9.add(lblNewLabel_1);
         
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
         		populaTabelaEstrategia();
         		populaTabelaOperacao();
         	}
         });
         panel_2.add(btnSair);
         panel_2.add(button_1);
         
         
         populaTabelaEstrategia();
         populaTabelaOperacao();
         
	}

	private void incluirOperacao(){
		
		DefaultTableModel modeloEstrategia= (DefaultTableModel)tblEstrategia.getModel();
		DefaultTableModel modeloOperacao= (DefaultTableModel)tblOperacao.getModel();
		
		//insere a operação 
		int numLinha=tblEstrategia.getSelectedRow();

		Object[] linha= new Object[]{
				modeloEstrategia.getValueAt(numLinha, 0),
				modeloEstrategia.getValueAt(numLinha, 2),
				modeloEstrategia.getValueAt(numLinha, 4),
				modeloEstrategia.getValueAt(numLinha, 5),
				modeloEstrategia.getValueAt(numLinha, 1),
				modeloEstrategia.getValueAt(numLinha, 3)
		};

		modeloOperacao.insertRow(modeloOperacao.getRowCount(),linha);
		
		//marca linha para exclusão no BD
		BigDecimal bd = (BigDecimal)modeloEstrategia.getValueAt(numLinha, 6);		
		sqEstrategiasparaExcluir.add(bd);
		
		//exclui estrategia da table origem
		modeloEstrategia.removeRow(numLinha);
	}
	
	private void salvar(){
		
		try{ 				
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

					new OperacaoDao().inserirAberta(est);
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
        }
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
		//editavel a o valor corrente
		tblOperacao = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return column==1;
			}
		};
		tblOperacao.setRowSelectionAllowed(false);
	}
	
	private int populaTabelaEstrategia(){
				
		int ret =Tabela.popula(tblEstrategia, 
				new EstrategiaDao().buscarTodosRSParaOperacao(),
				6);
		
		return ret;
	}	

	private int populaTabelaOperacao(){
		int ret=0;
		
		try {
			ret = Tabela.popula(tblOperacao, 
					new OperacaoDao().buscarAbertas(),
					6);
			//esconde colunas com situação
			tblOperacao.getColumnModel().getColumn(7).setMinWidth(0);
			tblOperacao.getColumnModel().getColumn(7).setMaxWidth(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}	
	

}
