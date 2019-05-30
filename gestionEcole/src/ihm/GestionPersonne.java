package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bean.Personne;
import dao.Connexion;
import dao.PersonneDao;
import model.PersonneModel;

public class GestionPersonne extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7766817812197287035L;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JRadioButton rdbtnEtudiant;
	private JRadioButton rdbtnEnseignant;
	private JPanel panelDetail;
	private JButton btnEnregistrer;
	private JButton btnCreer;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnRechercher;
	private JButton btnAnnuler;
	private JTable tableau;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PersonneDao personneDao;
	private ActionListener executerAction;
	private Personne personneSelected;


	public GestionPersonne(int width,int height) {

		super();
		personneDao = new PersonneDao();
		setLayout(null);
		setSize(new Dimension(height, width));
		setPreferredSize(new Dimension(height, width));
		initListener();
		initTable();
		initDetail();

	}


	private void initDetail() 
	{

		JLabel lblDetail = new JLabel("D\u00E9tail");
		lblDetail.setBounds(175, 8, 204, 19);
		lblDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetail.setForeground(Color.RED);
		lblDetail.setFont(new Font("Tahoma", Font.ITALIC, 15));

		//Identifiant
		JLabel lblIdentifiant = new JLabel("Identifiant : ");
		lblIdentifiant.setBounds(120, 50, 86, 20);
		txtId = new JTextField();
		txtId.setBounds(180, 50, 86, 20);
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setColumns(10);

		txtNom = new JTextField();
		txtNom.setBounds(180, 100, 86, 20);
		txtNom.setColumns(10);

		txtPrenom = new JTextField();
		txtPrenom.setBounds(180, 150, 86, 20);
		txtPrenom.setColumns(10);

		// Type
		JPanel panelSelection = new JPanel();
		panelSelection.setBounds(120, 200, 200, 100);
		panelSelection.setBorder(new TitledBorder(null, "Type :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rdbtnEtudiant = new JRadioButton("Etudiant", true);
		rdbtnEnseignant = new JRadioButton("Enseignant");
		buttonGroup.add(rdbtnEtudiant);
		buttonGroup.add(rdbtnEnseignant);
		panelSelection.add(rdbtnEtudiant);
		panelSelection.add(rdbtnEnseignant);
		panelSelection.repaint();


		panelDetail = new JPanel();
		panelDetail.setBorder(new LineBorder(new Color(255, 200, 0), 3, true));
		panelDetail.setBounds(50, 302, 702, 400);
		panelDetail.setLayout(null);

		panelDetail.add(lblDetail);
		panelDetail.add(lblIdentifiant);
		panelDetail.add(txtId);

		//Nom
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(120, 100, 86, 20);
		panelDetail.add(lblNom);
		panelDetail.add(txtNom);

		//Prenom
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setBounds(120, 150, 86, 20);
		panelDetail.add(lblPrnom);
		panelDetail.add(txtPrenom);
		panelDetail.add(panelSelection);
		panelDetail.repaint();

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


		JLabel lblGestionDesPersonnes = new JLabel("Gestion des personnes");
		lblGestionDesPersonnes.setBounds(175, 39, 318, 39);
		lblGestionDesPersonnes.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesPersonnes.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblGestionDesPersonnes.setForeground(Color.RED);
		add(lblGestionDesPersonnes);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(75, 100, 611, 139);
		add(scrollPane);

		tableau = new JTable(new PersonneModel(new ArrayList<Personne>()));
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
					personneSelected = new Personne();
					personneSelected.setId(Integer.parseInt(tableau.getModel().getValueAt(rowIndex, 0).toString()));
					personneSelected.setNom(tableau.getModel().getValueAt(rowIndex, 1).toString());
					personneSelected.setPrenom(tableau.getModel().getValueAt(rowIndex, 2).toString());
					personneSelected.setType(tableau.getModel().getValueAt(rowIndex, 3).toString());
					;
				}

			}
		}
				);
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setActionCommand("Rechercher");
		btnRechercher.setBounds(100, 250, ConstGestion.BUTTON_WIDTH, ConstGestion.BUTTON_HEIGHT);
		btnRechercher.addActionListener(executerAction);
		add(btnRechercher);

		// Creation d'une personne
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

		// Destruction d'une personne
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
					tableau.setModel(new PersonneModel(personneDao.findAll(Connexion.getConnection())));
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
						personneDao.delete(Connexion.getConnection(), personneSelected);
						//si on veut rafraichir le tableau
						//tableau.repaint();
						tableau.setModel(new PersonneModel(personneDao.findAll(Connexion.getConnection())));
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
						txtId.setText(personneSelected.getId().toString());
						txtNom.setText(personneSelected.getNom());
						txtPrenom.setText(personneSelected.getPrenom());
						rdbtnEtudiant.setSelected((personneSelected.isEtudiant()) ? true:false);
						rdbtnEnseignant.setSelected((personneSelected.isEtudiant()) ? false:true);
						panelDetail.repaint();
						panelDetail.setVisible(true);
						
					}	
					
					btnRechercher.setEnabled(false);
					btnSupprimer.setEnabled(false);
					btnCreer.setEnabled(false);
					btnModifier.setEnabled(false);
					break;	

				case "Enregistrer" :	
					Personne personneEnregistree = new Personne(txtNom.getText(),txtPrenom.getText(),(rdbtnEtudiant.isSelected()==true) ? "E":"P");
					if (txtId.getText().isEmpty()) 
					{
						// Creation
						personneDao.insert(Connexion.getConnection(), personneEnregistree);
					}	
					else
					{	
						personneEnregistree.setId(Integer.parseInt(txtId.getText()));
						personneDao.update(Connexion.getConnection(), personneEnregistree);
					}
					//si on veut recharcher le tableau
					tableau.repaint();
					tableau.setModel(new PersonneModel(personneDao.findAll(Connexion.getConnection())));
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
		txtNom.setText("");
		txtPrenom.setText("");
		rdbtnEtudiant.setSelected(true);
		rdbtnEnseignant.setSelected(false);
	}
}