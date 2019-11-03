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
import javax.swing.border.LineBorder;

public class DialogEstrategia extends JDialog {

	BigDecimal start,gain,stop,gainParcial,risco,quantidade;
	private JTextField txtAtivo;
	private JTextField txtStart;
	private JTextField txtStop;
	private JTextField txtGain;
	private JTextField txtGainParcial;
	JLabel lblMsg;
	JLabel lblRisco;
	private final JPanel contentPanel = new JPanel();
	
	IFrameEstrategias pai;
	private JTextField txtAporte;
	private JTextField txtQuantidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogEstrategia dialog = new DialogEstrategia(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogEstrategia(IFrameEstrategias pai) {
		setModal(true);
		setTitle("Estrat\u00E9gia");
		setBounds(100, 100, 400, 335);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		getContentPane().setLayout(borderLayout);
		contentPanel.setBorder(new LineBorder(Color.BLUE));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 0, 5));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel_5);
		JLabel lblNewLabel = new JLabel("Ativo ");
		panel_5.add(lblNewLabel);
		
		txtAtivo = new JTextField();
		panel_5.add(txtAtivo);
		txtAtivo.setText("z");
		txtAtivo.setColumns(7);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(new LineBorder(Color.BLUE));
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Start   ");
		panel_3.add(lblNewLabel_1);
		
		txtStart = new JTextField();
		panel_3.add(txtStart);
		txtStart.setText("10,2");
		txtStart.setColumns(5);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_6);
		JLabel lblNewLabel_2 = new JLabel("Stop   ");
		panel_6.add(lblNewLabel_2);
		
		txtStop = new JTextField();
		panel_6.add(txtStop);
		txtStop.setText("8,6");
		txtStop.setColumns(5);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel.add(panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("Risco(%)  ");
		panel_7.add(lblNewLabel_5);
		
		 lblRisco = new JLabel("New label");
		panel_7.add(lblRisco);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_8.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel.add(panel_8);
		
		JButton btnSugerir = new JButton("Sugerir");
		panel_8.add(btnSugerir);
		btnSugerir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sugerir();
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_9.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_9);
		JLabel lblNewLabel_3 = new JLabel("Parcial   ");
		panel_9.add(lblNewLabel_3);
		
		txtGainParcial = new JTextField();
		panel_9.add(txtGainParcial);
		txtGainParcial.setColumns(5);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_10.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_10);
		JLabel lbl = new JLabel("Gain ");
		panel_10.add(lbl);
		
		txtGain = new JTextField();
		panel_10.add(txtGain);
		txtGain.setColumns(5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_11.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_11);
		
		JLabel lblNewLabel_6 = new JLabel("Aporte");
		panel_11.add(lblNewLabel_6);
		
		txtAporte = new JTextField();
		panel_11.add(txtAporte);
		txtAporte.setText("4000");
		txtAporte.setColumns(7);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_12.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_12);
		
		JLabel lblNewLabel_4 = new JLabel("Quantidade");
		panel_12.add(lblNewLabel_4);
		
		txtQuantidade = new JTextField();
		panel_12.add(txtQuantidade);
		txtQuantidade.setColumns(5);
		
		
		
		
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
				
				lblMsg = new JLabel("");
				buttonPane.add(lblMsg);
				lblMsg.setForeground(Color.BLACK);
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
		
		this.pai=pai;
	}
	
	private void salvar(){

		try{

			Object[] linha=new Object[]{txtAtivo.getText(), 
					txtAtivo.getText(),getStart(),getStop(), getGain(), getGainParcial(),
					getQuantidade(),getRisco()				
			};
		
			pai.incluirEstrategia(linha);
			
			//limpa
			txtAtivo.setText("");
			txtStart.setText("");
			txtStop.setText("");
			txtGain.setText("");
			txtGainParcial.setText("");	
			txtQuantidade.setText("");	
			lblRisco.setText("");
		}catch(Exception e){
			lblMsg.setText("Erro ao salvar!");
		}
	}

	private void sugerir(){

		start =getStart();
		stop=getStop();

		gain=start.multiply(new BigDecimal("4")).subtract(		
				stop.multiply(new BigDecimal("3"))
				);

		gainParcial=start.multiply(new BigDecimal("2")).subtract(
				stop
				);

		risco=new BigDecimal("1").subtract(
				stop.divide(start,BigDecimal.ROUND_UP)
				).multiply(new BigDecimal("100"));

		BigDecimal aporte=Utilitario.converteParaBigDecimal(txtAporte.getText());
		quantidade=aporte.divide(start,BigDecimal.ROUND_UP);

		setGain();
		setGainParcial();
		setQuantidade();
		setRisco();
	}
	
	private BigDecimal getStart(){
		return Utilitario.converteParaBigDecimal(txtStart.getText());
	}
	
	private BigDecimal getStop(){
		return Utilitario.converteParaBigDecimal(txtStop.getText());
	}
	
	private BigDecimal getGain(){
		return Utilitario.converteParaBigDecimal(txtGain.getText());
	}
	private void setGain(){
		txtGain.setText(Utilitario.converteBigDecimalParaString(gain));
	}
	
	private BigDecimal getGainParcial(){
		return Utilitario.converteParaBigDecimal(txtGainParcial.getText());
	}
	private void setGainParcial(){
		txtGainParcial.setText(Utilitario.converteBigDecimalParaString(gainParcial)	);
	}

	private BigDecimal getQuantidade(){
		return Utilitario.converteParaBigDecimal(txtQuantidade.getText());
	}
	private void setQuantidade(){
		txtQuantidade.setText(Utilitario.converteBigDecimalParaString(quantidade));
	}
	
	private BigDecimal getRisco(){
		return Utilitario.converteParaBigDecimal(lblRisco.getText());
	}
	private void setRisco(){
		lblRisco.setText(Utilitario.converteBigDecimalParaString(risco));
	}
	
	private void cancelar(){
		dispose();
	}
}