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
    private IFramePosition framePosition;

 
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
 /*
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                   screenSize.width  - inset*2,

                  screenSize.height - inset*2);*/
 
        setBounds(20,20,600,400);
        
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
        
        JMenuItem mniPosicao = new JMenuItem("Posi\u00E7\u00E3o");
        mniPosicao.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	exibeframePosition();            	
            }
        });
        mnPrincipal.add(mniPosicao);
        
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        exibeframeEstrategia();
       //exibeframePosition();
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
	
	private void exibeframePosition()	{
        if(framePosition == null){
            framePosition = new IFramePosition();
            framePosition.setVisible(true);
            desktopPane.add(framePosition);
        }
        else if(!framePosition.isVisible()){
            framePosition.setVisible(true);
            desktopPane.add(framePosition);
        }
	}
	

 
}