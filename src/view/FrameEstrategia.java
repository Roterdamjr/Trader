package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class FrameEstrategia extends JFrame {

	private JPanel contentPane;
	private JTextField txtAtivo;
	private JTextField txtStart;
	private JTextField txtStop;
	private JTextField textField_3;
	private JTextField txtGainParcial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEstrategia frame = new FrameEstrategia();
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
	public FrameEstrategia() {
		setTitle("Estrat\u00E9gia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_grid = new JPanel();
		contentPane.add(panel_grid);
		panel_grid.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel_grid.add(panel);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblNewLabel = new JLabel("Ativo");
		panel.add(lblNewLabel);
		
		txtAtivo = new JTextField();
		panel.add(txtAtivo);
		txtAtivo.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_grid.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_grid.add(panel_2);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		JLabel lblNewLabel_1 = new JLabel("Start");
		panel_2.add(lblNewLabel_1);
		
		txtStart = new JTextField();
		panel_2.add(txtStart);
		txtStart.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_grid.add(panel_3);
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		
		JLabel lblNewLabel_2 = new JLabel("Stop           ");
		panel_3.add(lblNewLabel_2);
		
		txtStop = new JTextField();
		panel_3.add(txtStop);
		txtStop.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_grid.add(panel_4);
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		JLabel txtGain = new JLabel("Gain");
		panel_4.add(txtGain);
		
		textField_3 = new JTextField();
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_grid.add(panel_5);
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		
		JLabel lblNewLabel_3 = new JLabel("Gain Parcial");
		panel_5.add(lblNewLabel_3);
		
		txtGainParcial = new JTextField();
		panel_5.add(txtGainParcial);
		txtGainParcial.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_6.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_6, BorderLayout.SOUTH);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setForeground(Color.BLUE);
		panel_6.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setForeground(Color.BLUE);
		panel_6.add(btnSalvar);
	}

}
