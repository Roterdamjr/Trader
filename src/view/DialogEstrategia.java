package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Estrategia;
import utilitarios.Utilitario;
import dao.EstrategiaDao;

public class DialogEstrategia extends JDialog {

	
	private JTextField txtAtivo;
	private JTextField txtStart;
	private JTextField txtStop;
	private JTextField txtGain;
	private JTextField txtGainParcial;
	JLabel lblMsg;
	private final JPanel contentPanel = new JPanel();
	
	IFrameEstrategias pai;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			DialogEstrategia dialog = new DialogEstrategia(IFrameEstrategias pai);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public DialogEstrategia(IFrameEstrategias pai) {
		setTitle("Estrat\u00E9gia");
		setBounds(100, 100, 400, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 2, 0, 0));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			JLabel lblNewLabel = new JLabel("Ativo");
			panel.add(lblNewLabel);
			
			txtAtivo = new JTextField();
			txtAtivo.setText("z");
			panel.add(txtAtivo);
			txtAtivo.setColumns(10);
			contentPanel.add(panel);
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
		}
		
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			
			JLabel lblNewLabel_1 = new JLabel("Start");
			panel.add(lblNewLabel_1);
			
			txtStart = new JTextField();
			txtStart.setText("10,2");
			panel.add(txtStart);
			txtStart.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			JLabel lblNewLabel_2 = new JLabel("Stop    ");
			panel.add(lblNewLabel_2);
			
			txtStop = new JTextField();
			txtStop.setText("8,6");
			panel.add(txtStop);
			txtStop.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			JLabel lbl = new JLabel("Gain ");
			panel.add(lbl);
			
			txtGain = new JTextField();
			panel.add(txtGain);
			txtGain.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel);
		JLabel lblNewLabel_3 = new JLabel("Parcial");
		panel.add(lblNewLabel_3);
		
		txtGainParcial = new JTextField();
		panel.add(txtGainParcial);
		txtGainParcial.setColumns(10);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						salvar();
					}
				});
				
				JButton btnSugerir = new JButton("Sugerir");
				btnSugerir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						sugerir();
					}
				});
				buttonPane.add(btnSugerir);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLUE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblMsg = new JLabel("New label");
		lblMsg.setForeground(Color.BLUE);
		panel_1.add(lblMsg);
		
		this.pai=pai;
	}
	
	private void salvar(){
		
		
		try{
			/*Estrategia neg= new Estrategia(
					txtAtivo.getText(),
					Utilitario.converteStringParaBigDecimal(txtStart.getText()),
					Utilitario.converteStringParaBigDecimal(txtStop.getText()),
					Utilitario.converteStringParaBigDecimal(txtGain.getText()),
					Utilitario.converteStringParaBigDecimal(txtGainParcial.getText()),
					null,null,null
					);

			new EstrategiaDao().inserir(neg);
			
			*/
			
			Object linha=new Object[]{txtAtivo.getText(), 
					Utilitario.converteStringParaBigDecimal(txtStart.getText()),
					Utilitario.converteStringParaBigDecimal(txtStop.getText()),
					Utilitario.converteStringParaBigDecimal(txtGain.getText()),
					Utilitario.converteStringParaBigDecimal(txtGainParcial.getText()),
					null					
			};
		
			//pai.incluirEstrategia(linha);
			
			//limpa
			txtAtivo.setText("");
			txtStart.setText("");
			txtStop.setText("");
			txtGain.setText("");
			txtGainParcial.setText("");			
		}catch(Exception e){
			lblMsg.setText("Erro ao salvar!");
		}
		
		

	}

	private void sugerir(){
		BigDecimal start = Utilitario.converteStringParaBigDecimal(txtStart.getText());
		BigDecimal stop=Utilitario.converteStringParaBigDecimal(txtStop.getText());

		BigDecimal gain=start.multiply(new BigDecimal("4")).subtract(		
				stop.multiply(new BigDecimal("3"))
		);
		
		BigDecimal gainParcial=start.multiply(new BigDecimal("2")).subtract(		
				stop
		);
		
		System.out.println("gain "+gain);
		System.out.println("gainParcial "+gainParcial);
/*		
		float gain=4*start-3*stop;
		float gainParcial=2*start-stop;
*/
		
		
		txtGain.setText( 
				gain.toString().replace(".", ",")
				);
		txtGainParcial.setText( 
				gainParcial.toString().replace(".", ",")
				);
	}
	
	private void cancelar(){
		dispose();
	}
}