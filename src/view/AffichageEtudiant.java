package view;

import java.awt.EventQueue;
import java.sql.*;
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

import dao.UtilisateurDAO;

import model.Utilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;

public class AffichageEtudiant extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTable table;
	private JTextField searchField;
	private JTextField adresseField;
	private JTextField sectionField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffichageEtudiant frame = new AffichageEtudiant();
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
	public AffichageEtudiant() {
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
		
		JLabel lblNewLabel = new JLabel("Etudiant");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(386, 11, 144, 30);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 45, 450, 307);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 25, 81, 28);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1_1 = new JLabel("UserName");
		lblNewLabel_1_1.setBounds(10, 64, 110, 28);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setBounds(10, 107, 97, 28);
		panel_1.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		nameField = new JTextField();
		nameField.setBounds(209, 25, 202, 28);
		panel_1.add(nameField);
		nameField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(209, 64, 202, 28);
		panel_1.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(209, 107, 202, 28);
		panel_1.add(passwordField);
		passwordField.setColumns(10);
		
	

		
		
		
		
		
		
		
		
		///////////////////////
		
		
		
		JButton btnExit = new JButton("Log out");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(128, 128, 255));
		btnExit.setBounds(130, 241, 179, 55);
		panel_1.add(btnExit);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				showLogin();
			}
		});
		
	////////////////	
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(128, 128, 255));
		btnClear.setBounds(319, 241, 110, 55);
		panel_1.add(btnClear);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 20));
///////////////////
		btnClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        nameField.setText("");
		        usernameField.setText("");
		        passwordField.setText("");
		        adresseField.setText("");
		        sectionField.setText("");
		    }
		});



		//
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "title", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 362, 451, 55);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(28, 17, 276, 28);
		panel_2.add(searchField);
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//
		JPanel panel_3 = new JPanel();
		//----JPanel
		JPanel panel_31 = new JPanel();
		panel_31.setBounds(466, 45, 420, 307);
		panel.add(panel_31);
		panel_31.setLayout(null);
		// JTable avec modèle 
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"name", "username", "password","adresse","section"});
		table.setModel(model);
		// Obtenir la liste de students à afficher
		UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();
		List<Utilisateur> students = UtilisateurDAO.getAllEtudiant();
		// Parcourir la liste de students et ajouter les données au modèle de tableau
		for (Utilisateur student : students) {
		    model.addRow(new Object[]{student.getNom(), student.getUsername(), student.getMdp(),student.getAdresse(),student.getFiliere()});
		}
		// Créer un JScrollPane et y ajouter la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 400, 267); 
		// JScrollPane
		panel_31.add(scrollPane);
		
		
		
		
		
		
		
		
		
		
		///--------------
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(128, 128, 255));
		////
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String input = searchField.getText();
		        List<Utilisateur> students = null;
		        UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();

		        if (input != null && input.trim().length() > 0) {
		            try {
		                students = UtilisateurDAO.chercheUtilisateur(input);
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		        } else {
		            students = UtilisateurDAO.getAllEtudiant();
		        }

		        DefaultTableModel model = new DefaultTableModel();
		        model.setColumnIdentifiers(new Object[]{"name", "usename", "mdp","adresse","filiere"});
		        if (students != null) {
		            for (Utilisateur student : students) {
		                model.addRow(new Object[]{student.getNom(), student.getUsername(), student.getMdp(),student.getAdresse(),student.getFiliere()});
		            }
		        }
		        
		        JTable table = new JTable(model);
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setBounds(10, 10, 400, 270);
		        panel_31.add(scrollPane);
		    }
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(317, 17, 114, 28);
		panel_2.add(btnNewButton_1);
//----------------------------------------------------------------		
//----------------------------------------------------------------update
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(128, 128, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(622, 362, 110, 55);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow == -1) {
		            // No row selected, show an error message
		            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            int modelRow = table.convertRowIndexToModel(selectedRow);
		            String name = (String) table.getModel().getValueAt(modelRow, 0); // Assuming titre is in the first column
		            String username = (String) table.getModel().getValueAt(modelRow, 1);
		            String mdp = (String) table.getModel().getValueAt(modelRow, 2);
		            String adresse = (String) table.getModel().getValueAt(modelRow, 3);
		            String filiere = (String) table.getModel().getValueAt(modelRow, 4);
		            byte role = 0;
		            String grade = null;
		            String dep = null;
		            

		            Utilisateur utilisateur = new Utilisateur(name,mdp, role,filiere,dep,adresse,grade,username);

		            UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();
		            int num = UtilisateurDAO.getNUM(utilisateur); // Retrieve the nE using UtilisateurDAO method

		            UtilisateurDAO.supprimer(num);

		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.removeRow(modelRow);

		            JOptionPane.showMessageDialog(null, "student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});





//////////////---ajouter aux imput

ListSelectionListener selectionListener = new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Vérifiez que la sélection a été ajustée et qu'une ligne est sélectionnée
        if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
            // Obtenez l'indice de ligne sélectionné dans le modèle
            int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());

            // Obtenez les valeurs des colonnes correspondantes dans le modèle
            int modelRow = table.convertRowIndexToModel(selectedRow);
            String name = (String) table.getModel().getValueAt(modelRow, 0); // Assuming titre is in the first column
            String username = (String) table.getModel().getValueAt(modelRow, 1);
            String mdp = (String) table.getModel().getValueAt(modelRow, 2);
            String adresse = (String) table.getModel().getValueAt(modelRow, 3);
            String filiere = (String) table.getModel().getValueAt(modelRow, 4);
            byte role = 0;
            String grade = null;
            String dep = null; // Supposons que np soit dans la troisième colonne

            // Mettez à jour les champs avec les valeurs correspondantes
            nameField.setText(name);
            usernameField.setText(username);
            passwordField.setText(mdp);
            adresseField.setText(adresse);
            sectionField.setText(filiere);
        }
    }
};

// Ajoutez le ListSelectionListener au tableau
table.getSelectionModel().addListSelectionListener(selectionListener);


JButton btnUpdate = new JButton("Update");
btnUpdate.setForeground(new Color(255, 255, 255));
btnUpdate.setBackground(new Color(128, 128, 255));
btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
btnUpdate.setBounds(487, 362, 110, 55);
panel.add(btnUpdate);



btnUpdate.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            // No row selected, show an error message
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
        	int modelRow = table.convertRowIndexToModel(selectedRow);
            String name = (String) table.getModel().getValueAt(modelRow, 0); // Assuming titre is in the first column
            String username = (String) table.getModel().getValueAt(modelRow, 1);
            String mdp = (String) table.getModel().getValueAt(modelRow, 2);
            String adresse = (String) table.getModel().getValueAt(modelRow, 3);
            String filiere = (String) table.getModel().getValueAt(modelRow, 4);
            Utilisateur student = new Utilisateur(name,mdp, 0,filiere,null,adresse,null,username);

            UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();
            int num = UtilisateurDAO.getNUM(student); // Retrieve the nE using UtilisateurDAO method

            String name2 = nameField.getText();
            String username2 = usernameField.getText();
            String mdp2 = passwordField.getText();
            String adresse2 = adresseField.getText();
            String filiere2 = sectionField.getText();

            Utilisateur student2 = new Utilisateur(name2, mdp2, 1,filiere2,null,adresse2,null,username2);

            UtilisateurDAO.updateEtudiant(num, student2);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setValueAt(name2, modelRow, 0);
            model.setValueAt(username2, modelRow, 1);
            model.setValueAt(mdp2,modelRow, 2);
            model.setValueAt(adresse2,modelRow, 3);
            model.setValueAt(filiere2,modelRow, 4);
            
            nameField.setText("");
	        usernameField.setText("");
	        passwordField.setText("");
	        adresseField.setText("");
	        sectionField.setText("");

            JOptionPane.showMessageDialog(null, "Update successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});


JButton btnNewButton = new JButton("Save");
btnNewButton.setForeground(new Color(255, 255, 255));
btnNewButton.setBackground(new Color(128, 128, 255));
btnNewButton.setBounds(10, 241, 110, 55);
panel_1.add(btnNewButton);
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
adresseField = new JTextField();
adresseField.setBounds(209, 150, 202, 28);
panel_1.add(adresseField);
adresseField.setColumns(10);
sectionField = new JTextField();
sectionField.setColumns(10);
sectionField.setBounds(209, 189, 202, 28);
panel_1.add(sectionField);
JLabel lblNewLabel_2 = new JLabel("Adresse");
lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_2.setBounds(10, 148, 110, 25);
panel_1.add(lblNewLabel_2);
JLabel lblNewLabel_2_1 = new JLabel("Section");
lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
lblNewLabel_2_1.setBounds(10, 189, 97, 21);
panel_1.add(lblNewLabel_2_1);
JButton btnNewButton_2 = new JButton("Back");
btnNewButton_2.setForeground(new Color(255, 255, 255));
btnNewButton_2.setBackground(new Color(128, 128, 255));
btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
btnNewButton_2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		dispose();
		showEtudiantOuAdherent();
	}
	
});
btnNewButton_2.setBounds(762, 362, 95, 55);
panel.add(btnNewButton_2);

btnNewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String username = usernameField.getText();
        String mdp = passwordField.getText();
        String adresse = adresseField.getText();
        String section = sectionField.getText();

        Utilisateur student = new Utilisateur(name, mdp,1,section,null,adresse,null,username);

        UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();
        UtilisateurDAO.ajouterEtudiant(student);

        // Ajouter les données dans le tableau
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] { student.getNom(), student.getUsername(), student.getMdp(),student.getAdresse(),student.getFiliere() });

        // Réinitialiser les champs de texte
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        adresseField.setText("");
        sectionField.setText("");

        JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
});


	}
	private void showLogin() {
        // Code pour afficher la vue principale
	 Login mainView = new Login();
        mainView.setVisible(true);
    }
	private void showEtudiantOuAdherent() {
        // Code pour afficher la vue principale
		EtudiantOuAdherent mainView = new EtudiantOuAdherent();
        mainView.setVisible(true);
	}
}