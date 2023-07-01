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

import dao.LivreDAO;

import model.Livre;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;

public class AffichageLivre extends JFrame {

	private JPanel contentPane;
	private JTextField auteurField;
	private JTextField titreField;
	private JTextField npField;
	private JTable table;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffichageLivre frame = new AffichageLivre();
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
	public AffichageLivre() {
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
		
		JLabel lblNewLabel = new JLabel("Book");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(414, 10, 99, 23);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 43, 450, 309);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Auteur");
		lblNewLabel_1.setBounds(10, 24, 81, 28);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1_1 = new JLabel("Titre");
		lblNewLabel_1_1.setBounds(10, 78, 81, 28);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_1_2 = new JLabel("nP");
		lblNewLabel_1_2.setBounds(10, 130, 81, 28);
		panel_1.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		auteurField = new JTextField();
		auteurField.setBounds(209, 29, 202, 28);
		panel_1.add(auteurField);
		auteurField.setColumns(10);
		
		titreField = new JTextField();
		titreField.setBounds(209, 83, 202, 28);
		panel_1.add(titreField);
		titreField.setColumns(10);
		
		npField = new JTextField();
		npField.setBounds(209, 135, 202, 28);
		panel_1.add(npField);
		npField.setColumns(10);
		
	

		
		
		
		
		
		
		
		
		///////////////////////
		
		
		
		JButton btnExit = new JButton("Log out");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(128, 128, 255));
		btnExit.setBounds(173, 215, 110, 55);
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
		btnClear.setBounds(316, 215, 110, 55);
		panel_1.add(btnClear);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 20));
///////////////////
		btnClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        auteurField.setText("");
		        titreField.setText("");
		        npField.setText("");
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
		searchField.setBounds(22, 17, 282, 28);
		panel_2.add(searchField);
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//
		JPanel panel_3 = new JPanel();
		//----JPanel
		JPanel panel_31 = new JPanel();
		panel_31.setBounds(466, 44, 420, 308);
		panel.add(panel_31);
		panel_31.setLayout(null);
		// JTable avec modèle 
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"Auteur", "Titre", "nP"});
		table.setModel(model);
		// Obtenir la liste de livres à afficher
		LivreDAO livreDAO = new LivreDAO();
		List<Livre> livres = livreDAO.getAll();
		// Parcourir la liste de livres et ajouter les données au modèle de tableau
		for (Livre livre : livres) {
		    model.addRow(new Object[]{livre.getAuteur(), livre.getTitre(), livre.getnP()});
		}
		// Créer un JScrollPane et y ajouter la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 400, 269); 
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
		        List<Livre> livres = null;
		        LivreDAO livreDAO = new LivreDAO();

		        if (input != null && input.trim().length() > 0) {
		            try {
		                livres = livreDAO.chercheLivre(input);
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		        } else {
		            livres = livreDAO.getAll();
		        }

		        DefaultTableModel model = new DefaultTableModel();
		        model.setColumnIdentifiers(new Object[]{"Auteur", "Titre", "nP"});
		        if (livres != null) {
		            for (Livre livre : livres) {
		                model.addRow(new Object[]{livre.getAuteur(), livre.getTitre(), livre.getnP()});
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

			
			
			
			
		
		////
//----------------------------------------------------------------supprimer
		
		

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(128, 128, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(620, 362, 110, 55);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow == -1) {
		            // No row selected, show an error message
		            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            int modelRow = table.convertRowIndexToModel(selectedRow);
		            String titre = (String) table.getModel().getValueAt(modelRow, 1); // Assuming titre is in the first column
		            String auteur = (String) table.getModel().getValueAt(modelRow, 0); // Assuming auteur is in the second column
		            int nP = (int) table.getModel().getValueAt(modelRow, 2); // Assuming nP is in the third column

		            Livre livre = new Livre(titre, auteur, nP);

		            LivreDAO livreDAO = new LivreDAO();
		            int nE = livreDAO.getNE(livre); // Retrieve the nE using LivreDAO method

		            livreDAO.supprimer(nE);

		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.removeRow(modelRow);

		            JOptionPane.showMessageDialog(null, "Livre deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});
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
            auteurField.setText(auteur);
            titreField.setText(titre);
            npField.setText(String.valueOf(np));
        }
    }
};

// Ajoutez le ListSelectionListener au tableau
table.getSelectionModel().addListSelectionListener(selectionListener);


JButton btnUpdate = new JButton("Update");
btnUpdate.setForeground(new Color(255, 255, 255));
btnUpdate.setBackground(new Color(128, 128, 255));
btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
btnUpdate.setBounds(488, 362, 110, 55);
panel.add(btnUpdate);



btnUpdate.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            // No row selected, show an error message
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            String titre = (String) table.getModel().getValueAt(modelRow, 1); // Assuming titre is in the second column
            String auteur = (String) table.getModel().getValueAt(modelRow, 0); // Assuming auteur is in the first column
            int nP = (int) table.getModel().getValueAt(modelRow, 2); // Assuming nP is in the third column

            Livre livre = new Livre(titre, auteur, nP);

            LivreDAO livreDAO = new LivreDAO();
            int nE = livreDAO.getNE(livre); // Retrieve the nE using LivreDAO method

            String auteur2 = auteurField.getText();
            String titre2 = titreField.getText();
            String np2 = npField.getText();

            Livre livre2 = new Livre(auteur2, titre2, Integer.parseInt(np2));

            livreDAO.update(nE, livre2);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setValueAt(auteur2, modelRow, 0);
            model.setValueAt(titre2, modelRow, 1);
            model.setValueAt(Integer.parseInt(np2), modelRow, 2);
            
            auteurField.setText("");
	        titreField.setText("");
	        npField.setText("");

            JOptionPane.showMessageDialog(null, "Update successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});


JButton btnNewButton = new JButton("Save");
btnNewButton.setForeground(new Color(255, 255, 255));
btnNewButton.setBackground(new Color(128, 128, 255));
btnNewButton.setBounds(22, 215, 110, 55);
panel_1.add(btnNewButton);
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
JButton btnNewButton_2 = new JButton("Back");
btnNewButton_2.setForeground(new Color(255, 255, 255));
btnNewButton_2.setBackground(new Color(128, 128, 255));
btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
btnNewButton_2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		dispose();
		showAdminIHM();
	}
});
btnNewButton_2.setBounds(753, 362, 99, 55);
panel.add(btnNewButton_2);

btnNewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String auteur = auteurField.getText();
        String titre = titreField.getText();
        String np = npField.getText();

        Livre livre = new Livre(auteur, titre, Integer.parseInt(np));

        LivreDAO livreDAO = new LivreDAO();
        livreDAO.ajouter(livre);

        // Ajouter les données dans le tableau
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] { livre.getAuteur(), livre.getTitre(), livre.getnP() });

        // Réinitialiser les champs de texte
        auteurField.setText("");
        titreField.setText("");
        npField.setText("");

        JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
});


	}
	private void showLogin() {
        // Code pour afficher la vue principale
	 Login mainView = new Login();
        mainView.setVisible(true);
    }
	private void showAdminIHM() {
        // Code pour afficher la vue principale
		AdminIHM mainView = new AdminIHM();
        mainView.setVisible(true);
	}
}