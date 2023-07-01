package view;
import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dao.EmpruntDAO;
import dao.ExemplaireDAO;
import dao.LivreDAO;
import model.Emprunt;
import model.Exemplaire;
import model.Livre;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
public class AffichageEmprunt extends JFrame {
private JPanel contentPane;
private JTextField beneficiaryField;
private JTable table;
/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
AffichageEmprunt frame = new AffichageEmprunt();
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
public AffichageEmprunt() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 920, 492);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
JPanel panel = new JPanel();
panel.setBackground(new Color(128, 64, 64));
panel.setBounds(10, 10, 896, 445);
contentPane.add(panel);
panel.setLayout(null);
JLabel lblNewLabel = new JLabel("Emprunt");
lblNewLabel.setForeground(new Color(255, 255, 255));
lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
lblNewLabel.setBounds(368, 10, 176, 37);
panel.add(lblNewLabel);
JPanel panel_1 = new JPanel();
panel_1.setBorder(new TitledBorder(null, "title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
panel_1.setBounds(10, 60, 450, 292);
panel.add(panel_1);
panel_1.setLayout(null);
JLabel lblNewLabel_1 = new JLabel("beneficiary");
lblNewLabel_1.setBounds(10, 10, 140, 28);
panel_1.add(lblNewLabel_1);
lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
///////////////////////
JButton btnExit = new JButton("Exit");
btnExit.setForeground(new Color(255, 255, 255));
btnExit.setBackground(new Color(0, 128, 192));
btnExit.setBounds(173, 215, 110, 55);
panel_1.add(btnExit);
btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
btnExit.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
System.exit(0);
}
});
//
///-------------tableau
JPanel panel_3 = new JPanel();
//----JPanel
JPanel panel_31 = new JPanel();
panel_31.setBounds(466, 62, 420, 290);
panel.add(panel_31);
panel_31.setLayout(null);
// JTable avec modèle 
JTable table = new JTable();
DefaultTableModel model = new DefaultTableModel();
model.setColumnIdentifiers(new Object[]{"id_utilisateur", "id_exemplaire", "date_debut", "date_retour"});
table.setModel(model);
// Obtenir la liste des emprunts à afficher
EmpruntDAO empruntDAO = new EmpruntDAO(null);
List<Emprunt> emprunts = empruntDAO.getAll();
// Parcourir la liste des emprunts et ajouter les données au modèle de tableau
for (Emprunt emprunt : emprunts) {
model.addRow(new Object[]{emprunt.getid_utilisateur(), emprunt.getid_exemplaire(), emprunt.getDate_debut(), emprunt.getDate_retour()});
}
// Créer un JScrollPane et y ajouter la JTable
JScrollPane scrollPane = new JScrollPane(table);
scrollPane.setBounds(10, 10, 400, 270);
// JScrollPane
panel_31.add(scrollPane);
//---------------------------------------------------------------- 
//----------------------------------------------------------------update
//////////////---ajouter aux imput
ListSelectionListener selectionListener = new ListSelectionListener() {
@Override
public void valueChanged(ListSelectionEvent e) {
// Vérifiez que la sélection a été ajustée et qu'une ligne est sélectionnée
if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
// Obtenez l'indice de ligne sélectionné dans le modèle
int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
// Obtenez les valeurs des colonnes correspondantes dans le modèle
String auteur = (String) table.getModel().getValueAt(selectedRow, 0); // Supposons que l'auteur soit dans la première colonne
String titre = (String) table.getModel().getValueAt(selectedRow, 1); // Supposons que le titre soit dans la deuxième colonne
int np = (int) table.getModel().getValueAt(selectedRow, 2); // Supposons que np soit dans la troisième colonne
// Mettez à jour les champs avec les valeurs correspondantes
beneficiaryField.setText(auteur);
}
}
};
// Ajoutez le ListSelectionListener au tableau
table.getSelectionModel().addListSelectionListener(selectionListener);
// Rest of your code...
//btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
//btnNewButton.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// String beneficiary = beneficiaryField.getText();
// Livre selectedLivre = (Livre) livre_combobox.getSelectedItem();
// Exemplaire selectedExemplaire = (Exemplaire) n_exemplaire_combobox.getSelectedItem();
// String date_debut = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
// String date_fin = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
//
// Emprunt emprunt = new Emprunt(date_debut, date_fin, beneficiary, selectedLivre.getId(), selectedExemplaire.getId());
//
// EmpruntDAO empruntDAO = new EmpruntDAO();
// empruntDAO.ajouter(emprunt);
//
// // Ajouter les données dans le tableau
// DefaultTableModel model = (DefaultTableModel) table.getModel();
// model.addRow(new Object[]{selectedLivre.getAuteur(), selectedLivre.getTitre(), selectedExemplaire.getNumero()});
//
// // Réinitialiser les champs de texte
// beneficiaryField.setText("");
// livre_combobox.setSelectedIndex(0);
// n_exemplaire_combobox.setSelectedIndex(0);
// dateChooser.setDate(null);
// dateChooser_1.setDate(null);
//
// JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
// }
//});
//-------------------------------------------------------------------------------------------------------
//----benrficiaire
beneficiaryField = new JTextField();
beneficiaryField.setBounds(209, 10, 202, 28);
panel_1.add(beneficiaryField);
beneficiaryField.setColumns(10);




//Code pour la JComboBox livre_combobox
JComboBox<Livre> livre_combobox = new JComboBox<>();
livre_combobox.setBounds(209, 48, 202, 21);
panel_1.add(livre_combobox);

LivreDAO livreDAO = new LivreDAO();
List<Livre> livreList = livreDAO.getAll();
DefaultComboBoxModel<Livre> comboBoxModel = new DefaultComboBoxModel<>(livreList.toArray(new Livre[0]));
livre_combobox.setModel(comboBoxModel);

//Code pour la JComboBox n_exemplaire_combobox
JComboBox<Exemplaire> n_exemplaire_combobox = new JComboBox<>();
n_exemplaire_combobox.setBounds(209, 89, 202, 21);
panel_1.add(n_exemplaire_combobox);

livre_combobox.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
     Livre selectedLivre = (Livre) livre_combobox.getSelectedItem();
     
     int nE = livreDAO.getNE(selectedLivre); // Utiliser getNE à partir de LivreDAO
     
     ExemplaireDAO exemplaireDAO = new ExemplaireDAO();
     List<Exemplaire> exemplaireList = exemplaireDAO.disponibleExemplaires(nE);
     DefaultComboBoxModel<Exemplaire> exemplaireComboBoxModel = new DefaultComboBoxModel<>(exemplaireList.toArray(new Exemplaire[0]));
     n_exemplaire_combobox.setModel(exemplaireComboBoxModel);
 }
});





//-----date
JDateChooser dateChooser = new JDateChooser();
dateChooser.setBounds(209, 119, 202, 19);
panel_1.add(dateChooser);
JDateChooser dateChooser_1 = new JDateChooser();
dateChooser_1.setBounds(209, 144, 202, 19);
panel_1.add(dateChooser_1);
//--------------------------stock
JComboBox<String> stock_combobox = new JComboBox<>();
stock_combobox.setBounds(209, 176, 202, 21);
panel_1.add(stock_combobox);
livre_combobox.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
Livre selectedLivre = (Livre) livre_combobox.getSelectedItem();
int nE = livreDAO.getNE(selectedLivre); 
int nbrDispo = livreDAO.nbrDispoExemplaire(Integer.toString(nE)); 
DefaultComboBoxModel<String> stockComboBoxModel = new DefaultComboBoxModel<>();
if (nbrDispo > 0) {
stockComboBoxModel.addElement("Disponible (" + nbrDispo + ")");
} else {
stockComboBoxModel.addElement("Indisponible");
}
stock_combobox.setModel(stockComboBoxModel);
}
});
//
///////////////////////-------------------------------------save boutton
//JButton btnNewButton = new JButton("effectuer un emprunt");
//btnNewButton.setBounds(22, 215, 110, 55);
//panel_1.add(btnNewButton);
//btnNewButton.addActionListener(new ActionListener() {
//@Override
//public void actionPerformed(ActionEvent e) {
//// Get the values from the fields
//String beneficiary = beneficiaryField.getText();
//Livre selectedLivre = (Livre) livre_combobox.getSelectedItem();
//int nExemplaire = n_exemplaire_combobox.getSelectedIndex() + 1; // Assuming the index starts from 1
//Date dateDebut = (Date) dateChooser.getDate();
//Date dateRetour = (Date) dateChooser_1.getDate();
//String stock = (String) stock_combobox.getSelectedItem();
//// Create an Emprunt object
//Emprunt emprunt = new Emprunt(dateDebut, dateRetour, nExemplaire, beneficiary);
//// Insert the Emprunt object into the database
//EmpruntDAO empruntDAO = new EmpruntDAO(null); // Replace null with your database connection
//empruntDAO.ajouter(emprunt);
//// Refresh the table
//DefaultTableModel model = (DefaultTableModel) table.getModel();
//model.addRow(new Object[]{emprunt.getid_utilisateur(), emprunt.getid_exemplaire(), emprunt.getDate_debut(), emprunt.getDate_retour()});
//}
//});
//JComboBox<Utilisateur> n_exemplaire_combobox = new JComboBox<>();
//n_exemplaire_combobox.setBounds(209, 89, 202, 21);
//panel_1.add(n_exemplaire_combobox);
//
//UtilisateurDAO utilisateurDAO = new UtilisateurDAO(); // Créez une instance de UtilisateurDAO
//List<Utilisateur> utilisateurList = utilisateurDAO.getAll(); // Appelez getAll() sur l'instance de UtilisateurDAO pour obtenir la liste d'utilisateurs
//
//DefaultComboBoxModel<Utilisateur> comboBoxModel2 = new DefaultComboBoxModel<>(utilisateurList.toArray(new Utilisateur[0]));
//n_exemplaire_combobox.setModel(comboBoxModel2);
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
JLabel lblNewLabel_1_2_1 = new JLabel("n exemplaire");
lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_1_2_1.setBounds(10, 81, 168, 28);
panel_1.add(lblNewLabel_1_2_1);
JLabel lblNewLabel_1_2_2 = new JLabel("livre");
lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_1_2_2.setBounds(10, 43, 168, 28);
panel_1.add(lblNewLabel_1_2_2);
JLabel lblNewLabel_1_2_1_1 = new JLabel("start");
lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_1_2_1_1.setBounds(11, 115, 168, 28);
panel_1.add(lblNewLabel_1_2_1_1);
JLabel lblNewLabel_1_2_1_2 = new JLabel("end");
lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_1_2_1_2.setBounds(9, 138, 168, 28);
panel_1.add(lblNewLabel_1_2_1_2);
JLabel lblNewLabel_1_2_1_3 = new JLabel("stock");
lblNewLabel_1_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_1_2_1_3.setBounds(10, 170, 168, 28);
panel_1.add(lblNewLabel_1_2_1_3);
JButton adder = new JButton("ajouter");
adder.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	        // Récupérer les valeurs des champs de saisie
	        String beneficiary = beneficiaryField.getText();
	        Livre selectedLivre = (Livre) livre_combobox.getSelectedItem();
	        java.util.Date startDate = dateChooser.getDate();
	        java.util.Date returnDate = dateChooser_1.getDate();

	        // Convertir les objets java.util.Date en java.sql.Date
	        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
	        java.sql.Date sqlReturnDate = new java.sql.Date(returnDate.getTime());

	        // TODO: Insérer le code pour envoyer les valeurs à la base de données ici
	        
	        // Créer un nouvel objet Emprunt avec les valeurs récupérées
	        Emprunt emprunt = new Emprunt(sqlStartDate, sqlReturnDate, selectedLivre.getnE(), selectedLivre.getnE());
	        
	        // Appeler la méthode d'ajout de l'EmpruntDAO
	        EmpruntDAO empruntDAO = new EmpruntDAO(livreDAO);
	        empruntDAO.ajouter(emprunt);
	        int nE = selectedLivre.getnE();
	        // Ajouter les valeurs dans le modèle de tableau
	        model.addRow(new Object[]{beneficiary, nE, sqlStartDate, sqlReturnDate});

	        // Effacer les champs de saisie après l'ajout
	        beneficiaryField.setText("");
	        livre_combobox.setSelectedIndex(0);
	        dateChooser.setDate(null);
	        dateChooser_1.setDate(null);
	    }
	});
adder.setForeground(Color.WHITE);
adder.setFont(new Font("Tahoma", Font.BOLD, 20));
adder.setBackground(new Color(0, 128, 192));
adder.setBounds(40, 215, 110, 55);
panel_1.add(adder);
//Ajouter un ActionListener au bouton d'ajout ou à tout autre événement déclencheur
//par exemple, si vous avez un bouton nommé "btnAjouter", vous pouvez utiliser :
//Ajouter un ActionListener au bouton d'ajout ou à tout autre événement déclencheur
//Ajouter un ActionListener au bouton d'ajout ou à tout autre événement déclencheur






}
}

