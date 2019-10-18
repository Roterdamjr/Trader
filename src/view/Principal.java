package view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
 
public class Principal extends JFrame{
 
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu mnPrincipal;
    private IFrameEstrategias frameEstrategia;
    private IFrameOperacao frameOperacao;

 
    public static void main(String args[]){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

    }
    
    public Principal(){
 
        super("Exemplo de JDesktopPane");
        setBackground(Color.GRAY);
 
        int inset = 20;

        setBounds(20,20,800,600);
        
        
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.GRAY);
        setContentPane(desktopPane);                                

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnPrincipal = new JMenu("Principal");
        menuBar.add(mnPrincipal);
                
        JMenuItem mniEstrategia = new JMenuItem("Estrat\u00E9gia");
        mniEstrategia.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	exibeframeEstrategia();
            }
        });
        mnPrincipal.add(mniEstrategia);
        
        JMenuItem mniOperacao = new JMenuItem("Opera\u00E7\u00E3o");
        mniOperacao.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	exibeframeOperacao();            	
            }
        });
        mnPrincipal.add(mniOperacao);
        
        
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //exibeframeEstrategia();
        exibeframeOperacao();
    }
 
	private void exibeframeEstrategia()	{
		 if(frameEstrategia == null){			 
             frameEstrategia = new IFrameEstrategias();
             frameEstrategia.setVisible(true);
   
             desktopPane.add(frameEstrategia);
         }
         else if(!frameEstrategia.isVisible()){
             frameEstrategia.setVisible(true);
             desktopPane.add(frameEstrategia);
         }
	}

	
	private void exibeframeOperacao()	{
        if(frameOperacao == null){
            frameOperacao = new IFrameOperacao();
            frameOperacao.setVisible(true);
            desktopPane.add(frameOperacao);
        }
        else if(!frameOperacao.isVisible()){
            frameOperacao.setVisible(true);
            desktopPane.add(frameOperacao);
        }
	}
	

 
}