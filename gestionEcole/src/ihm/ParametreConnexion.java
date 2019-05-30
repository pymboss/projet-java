package ihm;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ParametreConnexion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7766817812197287035L;
	private JPanel contentPane;
	private JTextField textUrl;
	private JPasswordField txtUser;
	private JPasswordField txtPassword;
	
	
	private JLabel lblError;
	
	
		/**
	 * Create the frame.
	 */
	public ParametreConnexion( JFrame parentFrame, String title, boolean modal) {
		
		super(parentFrame,title,modal);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setBounds(100, 100, 357, 178);
		
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUrl = new JLabel("Url base de donn\u00E9es :");
		lblUrl.setBounds(12, 16, 132, 13);
		lblUrl.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblUrl);
		
		textUrl = new JTextField();
		textUrl.setBounds(148, 12, 167, 20);
		textUrl.setColumns(10);
		contentPane.add(textUrl);
		
		txtUser = new JPasswordField();
		txtUser.setBounds(148, 36, 167, 20);
		txtUser.setColumns(10);
		contentPane.add(txtUser);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(12, 43, 50, 13);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblPassword);
		
		JLabel lblUser = new JLabel("User :");
		lblUser.setBounds(12, 64, 132, 13);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblUser);
		txtPassword = new JPasswordField();
		txtPassword.setBounds(148, 60, 167, 20);
		txtPassword.setColumns(10);
		contentPane.add(txtPassword);
		
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(244, 84, 71, 23);
		contentPane.add(btnOk);
		
		lblError = new JLabel("");
		lblError.setBounds(31, 114, 284, 14);
		lblError.setVisible(false);
		contentPane.add(lblError);
		
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				try {
//					Connexion.createConnection(textUrl.getText(), String.valueOf(txtUser.getPassword()), String.valueOf(txtPassword.getPassword()));
     				getParent().setVisible(true);
					setVisible(false);
//				} catch (Exception e) {
//					lblError.setText("Erreur de connection de la base de données");
//					lblError.setForeground(Color.RED);
//					lblError.setVisible(true);
//				}
			}
		});
	}
}
