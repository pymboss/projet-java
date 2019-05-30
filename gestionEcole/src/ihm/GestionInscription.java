package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bean.Classe;
import bean.Inscription;
import bean.Personne;
import dao.ClasseDao;
import dao.Connexion;
import dao.InscriptionDao;
import dao.PersonneDao;
import model.InscriptionModel;

public class GestionInscription extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7766817812197287035L;
	private JTextField txtId;
	private JPanel panelDetail;
	private JButton btnEnregistrer;
	private JButton btnCreer;
	private JButton btnSupprimer;
	private JButton btnAnnuler;
	private JButton btnModifier;
	private JButton btnRechercher;
	private JComboBox<Classe> cbxClasse; 
	private JComboBox<Personne> cbxPersonne ;
	private JTable tableau;
	private InscriptionDao inscriptionDao;
	private PersonneDao personneDao;
	private ClasseDao classeDao;
	private ActionListener executerAction;
	private Inscription inscriptionSelected;
	private SimpleDateFormat sdf ;


	public GestionInscription(int width,int height) {

		super();
		inscriptionDao = new InscriptionDao();
		personneDao = new PersonneDao();
		classeDao = new ClasseDao();
		
		setLayout(null);
		setSize(new Dimension(height, width));
		setPreferredSize(new Dimension(height, width));
		sdf = new SimpleDateFormat("dd/MM/yy");
		initListener();
		initTable();
		initDetail();

	}


	private void initDetail() 
	{

		
		panelDetail = new JPanel();
		panelDetail.setBorder(new LineBorder(new Color(255, 200, 0), 3, true));
		panelDetail.setBounds(50, 302, 702, 400);
		panelDetail.setLayout(null);
		
		JLabel lblDetail = new JLabel("D\u00E9tail");
		lblDetail.setBounds(175, 8, 204, 19);
		lblDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetail.setForeground(Color.RED);
		lblDetail.setFont(new Font("Tahoma", Font.ITALIC, 15));
		panelDetail.add(lblDetail);
		
	
		
		//Identifiant
		JLabel lblIdentifiant = new JLabel("Identifiant : ");
		lblIdentifiant.setBounds(120, 50, 86, 20);
		panelDetail.add(lblIdentifiant);
		
		txtId = new JTextField();
		txtId.setBounds(180, 50, 86, 20);
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setColumns(10);
		panelDetail.add(txtId);
		
		
		//Classe
		JLabel lblClasse = new JLabel("Classe :");
		lblClasse.setBounds(120, 100, 86, 20);
		panelDetail.add(lblClasse);
		
		
		
		// a changer par une dao sur classe
		//ArrayList<Classe> listClasse = classeDao.findAll(Connexion.getConnection());
		DefaultComboBoxModel<Classe>  listClasse = new DefaultComboBoxModel<Classe> ();
		Classe classe1 = new Classe();
		classe1.setId(1);
		classe1.setNom("Test");
		listClasse.addElement(classe1);
		Classe classe2 = new Classe();
		classe2.setId(2);
		classe2.setNom("Test2");
		listClasse.addElement(classe2);
		cbxClasse = new JComboBox<Classe>(listClasse);
		cbxClasse.setBounds(180, 100, 150, 20);
		cbxClasse.setSelectedIndex(0);
		panelDetail.add(cbxClasse);
		
		
		//Etudiant
		JLabel lblEtudiant = new JLabel("Etudiant :");
		lblEtudiant.setBounds(120, 150, 86, 20);
		panelDetail.add(lblEtudiant);

		// a changer par une dao sur classe
		//Alimentation de la combo d'etudiant
		DefaultComboBoxModel<Personne>  listEtudiant = new DefaultComboBoxModel<Personne> ();
//		ArrayList<Personne> listPersonne = personneDao.findAll(Connexion.getConnection());
//		for (Personne personne : listPersonne) {
//			if (personne.isEtudiant())
//			listEtudiant.addElement(personne);
//		}
		Personne personne1 = new Personne();
		personne1.setId(1);
		personne1.setNom("Test");
		listEtudiant.addElement(personne1);
		Personne personne2 = new Personne();
		personne2.setId(2);
		personne2.setNom("Test2");
		listEtudiant.addElement(personne2);
		
		cbxPersonne = new JComboBox<Personne>(listEtudiant);
		cbxPersonne.setBounds(180, 150, 150, 20);
		cbxPersonne.setAutoscrolls(true);
		cbxPersonne.setSelectedIndex(0);
		panelDetail.add(cbxPersonne);

		
		//Enregister
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(450, 350,ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		btnEnregistrer.setActionCommand("Enregistrer");
		btnEnregistrer.addActionListener(executerAction);
		panelDetail.add(btnEnregistrer);
		
		
		//Annuler
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(300, 350,ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		btnAnnuler.setActionCommand("Annuler");
		btnAnnuler.addActionListener(executerAction);
		panelDetail.add(btnAnnuler);
		
		panelDetail.setVisible(false);
		add(panelDetail);
	

	}

	private void initTable() {


		JLabel lblGestionDesInscriptions = new JLabel("Gestion des inscriptions");
		lblGestionDesInscriptions.setBounds(175, 39, 318, 39);
		lblGestionDesInscriptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesInscriptions.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblGestionDesInscriptions.setForeground(Color.RED);
		add(lblGestionDesInscriptions);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(75, 100, 611, 139);
		add(scrollPane);

		tableau = new JTable(new InscriptionModel(new ArrayList<Inscription>()));
		tableau.setAutoscrolls(false);
		tableau.setAutoCreateRowSorter(true);
		tableau.setUpdateSelectionOnSort(false);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableau);
		add(tableau.getTableHeader(), BorderLayout.NORTH);


		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					final int rowIndex = tableau.getSelectedRow();
					if (rowIndex==-1)
					{	
						return;
					}	
					panelDetail.setVisible(false);
					inscriptionSelected = new Inscription();
					inscriptionSelected.setId(Integer.parseInt(tableau.getModel().getValueAt(rowIndex, 0).toString()));
					inscriptionSelected.getClasse().setNom(tableau.getModel().getValueAt(rowIndex, 1).toString());
					inscriptionSelected.getClasse().getNiveau().setNom(tableau.getModel().getValueAt(rowIndex, 2).toString());
					try {
						inscriptionSelected.getClasse().getAnneeScolaire().setDateDebut(sdf.parse(tableau.getModel().getValueAt(rowIndex, 3).toString()));
						inscriptionSelected.getClasse().getAnneeScolaire().setDateFin(sdf.parse(tableau.getModel().getValueAt(rowIndex, 4).toString()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					inscriptionSelected.getEtudiant().setNom(tableau.getModel().getValueAt(rowIndex, 5).toString());
					inscriptionSelected.getEtudiant().setPrenom(tableau.getModel().getValueAt(rowIndex, 6).toString());
				}					

			}
		}
				);
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setActionCommand("Rechercher");
		btnRechercher.setBounds(100, 250, ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		btnRechercher.addActionListener(executerAction);
		add(btnRechercher);

		// Creation  
		btnCreer = new JButton("Ajouter");
		btnCreer.setBounds(250, 250, ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		add(btnCreer);
		btnCreer.setActionCommand("Ajouter");
		btnCreer.addActionListener(executerAction);

		//mise a jour
		btnModifier = new JButton("Modifier");
		btnModifier.setBounds(400, 250,ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		add(btnModifier);
		btnModifier.setActionCommand("Modifier");
		btnModifier.addActionListener(executerAction);

		// Destructi 
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(550, 250,ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		add(btnSupprimer);
		btnSupprimer.setActionCommand("Supprimer");
		btnSupprimer.addActionListener(executerAction);

	}



	private void initListener() {

		executerAction = new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				System.out.println("Bouton [" + event.getActionCommand()+ "] utilisé.");

				switch (event.getActionCommand())
				{
				case "Rechercher" :  
					tableau.setModel(new InscriptionModel(inscriptionDao.findAll(Connexion.getConnection())));
					break;
				case "Ajouter" :
					btnRechercher.setEnabled(false);
					btnSupprimer.setEnabled(false);
					btnModifier.setEnabled(false);
					btnCreer.setEnabled(false);
					
					// on efface les valeurs qui pourraient rester
					effacerSaisie();
					panelDetail.repaint();
					panelDetail.setVisible(true);
					break;

				case "Supprimer" :
					
					if (tableau.getSelectedRowCount() == 0)
					{
						JOptionPane.showMessageDialog( null,"Vous devez sélectionner une ligne !!", "Erreur",
						                     JOptionPane.ERROR_MESSAGE );
					}
					else
					{	
						inscriptionDao.delete(Connexion.getConnection(), inscriptionSelected);
						//si on veut rafraichir le tableau
						//tableau.repaint();
						tableau.setModel(new InscriptionModel(inscriptionDao.findAll(Connexion.getConnection())));
					}	
					break;	

				case "Modifier" :	
					
					if (tableau.getSelectedRowCount() == 0)
					{
						JOptionPane.showMessageDialog( null,"Vous devez sélectionner une ligne !!", "Erreur",
						                     JOptionPane.ERROR_MESSAGE );
					}
					else
					{	
						
						txtId.setText(inscriptionSelected.getId().toString());
						cbxClasse.setSelectedItem(inscriptionSelected.getClasse());
						cbxPersonne.setSelectedItem(inscriptionSelected.getEtudiant());
						panelDetail.repaint();
						panelDetail.setVisible(true);
						
					}	
					
					btnRechercher.setEnabled(false);
					btnSupprimer.setEnabled(false);
					btnCreer.setEnabled(false);
					btnModifier.setEnabled(false);
					break;	

				case "Enregistrer" :
				
					Inscription inscriptionEnregistree= new Inscription();
					
					inscriptionEnregistree.getClasse().setId(cbxClasse.getItemAt(cbxClasse.getSelectedIndex()).getId());
					inscriptionEnregistree.getEtudiant().setId(cbxPersonne.getItemAt(cbxPersonne.getSelectedIndex()).getId());
							
					if (txtId.getText().isEmpty()) 
					{
						// Creation
						inscriptionDao.insert(Connexion.getConnection(), inscriptionEnregistree);
					}	
					else
					{	
						inscriptionEnregistree.setId(Integer.parseInt(txtId.getText()));
    					inscriptionDao.update(Connexion.getConnection(), inscriptionEnregistree);
					}
//					//si on veut recharcher le tableau
					tableau.repaint();
					tableau.setModel(new InscriptionModel(inscriptionDao.findAll(Connexion.getConnection())));
					btnRechercher.setEnabled(true);
					btnSupprimer.setEnabled(true);
					btnModifier.setEnabled(true);
					btnCreer.setEnabled(true);
					
					break;		

				case "Annuler" :
					effacerSaisie();
					tableau.repaint();
					panelDetail.repaint();
					panelDetail.setVisible(false);
					btnRechercher.setEnabled(true);
					btnSupprimer.setEnabled(true);
					btnModifier.setEnabled(true);
					btnCreer.setEnabled(true);
					break;		
					
				default:
					System.err.println ( "Option invalide" );
					break;
				};
			};	
		};
	}
	
	private void effacerSaisie()
	{
		txtId.setText("");
		cbxClasse.setSelectedIndex(0);
		cbxPersonne.setSelectedIndex(0);
	}
}