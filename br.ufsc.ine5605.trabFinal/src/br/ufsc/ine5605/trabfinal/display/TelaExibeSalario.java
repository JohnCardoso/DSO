package br.ufsc.ine5605.trabfinal.display;

import br.ufsc.ine5605.trabfinal.controllers.ControladorExibeSalario;
import br.ufsc.ine5605.trabfinal.controllers.ControladorFuncionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.lang.Exception;

public class TelaExibeSalario extends JFrame {

	private static final long serialVersionUID = -326516504164144527L;
	private ControladorExibeSalario ctrlExibeSal;
	private JLabel lbvalor1;
	private JLabel lbvalor2;
	private String matricula;
	private String falta;
	private String hora;

	public TelaExibeSalario(ControladorExibeSalario owner) throws Exception {
		super("Holerite Digital");
		this.ctrlExibeSal = owner;

	}

	private void init() throws Exception {

		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		lbvalor1 = new JLabel("Sua expectativa de vencimentos é: ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 5, 0, 0);
		lbvalor1.revalidate();
		lbvalor1.repaint();
		container.add(lbvalor1, constraints);

		lbvalor2 = new JLabel();
		try {
			lbvalor2.setText("R$: " + 
					String.valueOf(ControladorFuncionario.getCtrlFuncionario().calculaSalario(matricula, falta, hora)));

		} catch (Exception exception) {
			System.out.println(exception);
		}
		lbvalor2.setHorizontalAlignment(JTextField.CENTER);
		constraints.gridx = 1;
		constraints.gridy = 0;
		lbvalor2.setPreferredSize(new Dimension(100, 25));
		lbvalor2.revalidate();
		lbvalor2.repaint();
		container.add(lbvalor2, constraints);

		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public void passaValores(String mat, String faltas, String horas) {

		this.falta = faltas;
		this.matricula = mat;
		this.hora = horas;

		try {
			init();
			lbvalor1.revalidate();
			lbvalor1.repaint();

			lbvalor2.revalidate();
			lbvalor2.repaint();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}