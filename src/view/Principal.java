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
    private IFrameLancarAbertura frameLancarAbertura;
    private JMenuItem mniLancarExecucao;
    private IFrameLancarExecucao frameLancarExecucao;

 
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

        setBounds(20,20,800,700);
        
        
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
        
        JMenuItem mniAbrirOrdem = new JMenuItem("Lan\u00E7ar Abertura");
        mniAbrirOrdem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	exibeframeLancarAbertura();            	
            }
        });
        mnPrincipal.add(mniAbrirOrdem);
        
        mniLancarExecucao = new JMenuItem("Lan\u00E7arExecu\u00E7\u00E3o");
        mniLancarExecucao.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		exibeframeLancarExecucao();
        	}
        });
        mnPrincipal.add(mniLancarExecucao);
        
        
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //exibeframeEstrategia();
        //exibeframeLancarAbertura();
        exibeframeLancarExecucao();
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

	
	private void exibeframeLancarAbertura()	{
        if(frameLancarAbertura == null){
        	frameLancarAbertura = new IFrameLancarAbertura();
        	frameLancarAbertura.setVisible(true);
            desktopPane.add(frameLancarAbertura);
        }
        else if(!frameLancarAbertura.isVisible()){
        	frameLancarAbertura.setVisible(true);
            desktopPane.add(frameLancarAbertura);
        }
	}
	
	private void exibeframeLancarExecucao()	{
        if(frameLancarExecucao == null){
        	frameLancarExecucao = new IFrameLancarExecucao();
        	frameLancarExecucao.setVisible(true);
            desktopPane.add(frameLancarExecucao);
        }
        else if(!frameLancarExecucao.isVisible()){
        	frameLancarExecucao.setVisible(true);
            desktopPane.add(frameLancarExecucao);
        }
	}
 
}