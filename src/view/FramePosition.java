package view;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import utilitarios.Utilitario;
import dao.EstrategiaDao;
 
public class FramePosition extends JInternalFrame {
	private JTable tblEstrategia;
	private JTable tblPosition;
 
    public FramePosition() {
        super("Position",
              true, //resizable
               true, //closable
              true, //maximizable
               true);//iconifiable  
 
         setSize(500,424);  
 
         //setBounds(10,10, 200, 100);
 
        JPanel panel = new JPanel();
         panel.setBackground(SystemColor.controlHighlight);  
 
         Container container = getContentPane();
         container.add(panel, BorderLayout.NORTH);
         
         JButton btnCarregar = new JButton("Caregar");
         btnCarregar.setHorizontalAlignment(SwingConstants.LEFT);
         btnCarregar.setFont(new Font("Times New Roman", Font.BOLD, 14));
         panel.add(btnCarregar);
         
         JPanel panel_1 = new JPanel();
         getContentPane().add(panel_1, BorderLayout.CENTER);
         
         
         panel_1.setLayout(new GridLayout(0, 1, 0, 0));
         
         JPanel panel_3 = new JPanel();
         panel_1.add(panel_3);
         
         incializaTabelaEstrategia();
         JScrollPane scrollPane = new JScrollPane(tblEstrategia);
         panel_3.add(scrollPane);
         
         
         JPanel panel_4 = new JPanel();
         panel_1.add(panel_4);
         
         incializaTabelaPosition();                
         JScrollPane scrollPane_1 = new JScrollPane(tblPosition);
                  
         panel_4.add(scrollPane_1);
         
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
         
    }
    
    private void populaTabelaEstrategia(){
    	DefaultTableModel modelo= (DefaultTableModel)tblEstrategia.getModel();
    	
    	//remove linhas 
    	int rowCount = modelo.getRowCount();
    	for (int i = rowCount - 1; i >= 0; i--) {
    		modelo.removeRow(i);
    	}
    	
    	
    	try {
			Utilitario.resultSetToTableModel(new EstrategiaDao().buscarTodosRS(),tblEstrategia);
		} catch (SQLException e) {
			e.printStackTrace();
		}
/*    	ArrayList<Negociacao> lista=new NegociacaoDao().buscarTodos();
    	int contLinha=0;
    	for (Negociacao negociacao:lista){
    		modelo.setValueAt(negociacao.getAtivo(), contLinha, 0); 
    		modelo.setValueAt(negociacao.getStart(), contLinha, 1); 
    		modelo.setValueAt(negociacao.getStop(), contLinha, 2); 
    		modelo.setValueAt(negociacao.getGain(), contLinha, 3); 
    		modelo.setValueAt(negociacao.getGainParcial(), contLinha, 4); 
    		contLinha++;
    	}*/
    }
 
    private void incializaTabelaPosition(){
        tblPosition= new JTable();
        
        tblPosition.setCellSelectionEnabled(true);  
        
        tblPosition.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        		{null,null,null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"Ativo", "Start", "Stop", "Gain", "Gain Parcial"
        	}
        ));
    }
    private void incializaTabelaEstrategia(){
        tblEstrategia = new JTable();
        
        tblEstrategia.setCellSelectionEnabled(true);  
        
        tblEstrategia.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"VLID3", "15,46", "14,10", null, null},
        		{"", "", "", null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"Ativo", "Start", "Stop", "Gain", "Gain Parcial"
        	}
        ));
        /*
        máscara nnumerica
        */
        JFormattedTextField ftext = new JFormattedTextField();
        MaskFormatter mask;
        try {
	        mask= new MaskFormatter("##,##");
            mask.install(ftext);            
        } catch (ParseException e) {e.printStackTrace();
        }
        for(int i=1;i<=4;i++){
        	tblEstrategia.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(ftext));
        }
        
    }
    

 }