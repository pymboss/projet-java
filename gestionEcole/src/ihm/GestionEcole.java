package ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GestionEcole {

	private JFrame frmGestionInformatique;
	private ParametreConnexion frmParametreConnexion;
	private ActionListener afficherMenuListener;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionEcole application = new GestionEcole();
					//affichage des paramètres de connexion à la base de données
					application.frmParametreConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionEcole() {
		initListener();
		initialize();
		
	}

	private void initListener() {
	
		afficherMenuListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			
			System.out.println("Elément de menu [" + event.getActionCommand()+ "] utilisé.");
			
			switch (event.getActionCommand())
			{
				
				case "Quitter" :  
					System.exit(0);
					break;
				case "Personne" :
					frmGestionInformatique.getContentPane().setLayout(null);
					frmGestionInformatique.setContentPane(new GestionPersonne(ConstGestion.WINDOW_HEIGHT,ConstGestion.WINDOW_WIDTH));
					frmGestionInformatique.repaint();
					break;
					
				case "Inscription" :
					frmGestionInformatique.getContentPane().setLayout(null);
					frmGestionInformatique.setContentPane(new GestionInscription(ConstGestion.WINDOW_HEIGHT,ConstGestion.WINDOW_WIDTH));
					frmGestionInformatique.repaint();
					break;	
					
				default:
					System.err.println ( "Option invalide" );
					break;
			};
		};	
      };
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmGestionInformatique = new JFrame();
		frmGestionInformatique.setTitle("Gestion Informatique d'une Ecole");
		frmGestionInformatique.setSize(ConstGestion.WINDOW_WIDTH,ConstGestion.WINDOW_HEIGHT);
		//impossible de la redimensionner
		frmGestionInformatique.setResizable(false);
		frmGestionInformatique.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centrer la fenetre
		frmGestionInformatique.setLocationRelativeTo(null);
		frmGestionInformatique.getContentPane().setLayout(null);
		
		//pour afficher une image
		JPanel panel = new JPanel();
		panel.setSize(ConstGestion.WINDOW_WIDTH,ConstGestion.WINDOW_HEIGHT);
		ImageIcon icone = new ImageIcon("images/logo_ece.png");;
		JLabel image = new JLabel(icone);
		image.setSize(panel.getWidth(),panel.getHeight());
		panel.add(image);
		panel.repaint();
		frmGestionInformatique.setContentPane(panel);

		// fenetre de connexion
		frmParametreConnexion = new ParametreConnexion(frmGestionInformatique,"Connection",true);

		
		//creation des menus
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		frmGestionInformatique.setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		mnFichier.setActionCommand("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter",'Q');
		mntmQuitter.setActionCommand("Quitter");
		mntmQuitter.addActionListener(afficherMenuListener);

		mnFichier.add(mntmQuitter);

		JMenu mnAdministration = new JMenu("Gestion des mises \u00E0 Jour");
		menuBar.add(mnAdministration);
		
		
		JMenuItem mntmEcole = new JMenuItem("Ecole", 'E');
		mntmEcole.setActionCommand("Ecole");
		mntmEcole.addActionListener(afficherMenuListener);
		mnAdministration.add(mntmEcole);
		
		
		JMenuItem mntmClasse = new JMenuItem("Classe",'C');
		mntmClasse.setActionCommand("Classe");
		mntmClasse.addActionListener(afficherMenuListener);
		mnAdministration.add(mntmClasse);
		
		
		JMenuItem mntmPersonne = new JMenuItem("Personne", 'P');
		mntmPersonne.setActionCommand("Personne");
		mntmPersonne.addActionListener(afficherMenuListener);
		mnAdministration.add(mntmPersonne);
		
		
		JMenuItem mntmInscription = new JMenuItem("Inscription", 'I');
		mntmInscription.setActionCommand("Inscription");
		mntmInscription.addActionListener(afficherMenuListener);
		mnAdministration.add(mntmInscription);
		
		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);
		
		JMenuItem mntmReportEtudiants = new JMenuItem("Liste des \u00E9tudiants inscrits");
		mnReporting.add(mntmReportEtudiants);
	    
	}    
}
