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

public class DispoLivre extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DispoLivre frame = new DispoLivre();
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
	public DispoLivre() {
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



		//
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "title", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(25, 362, 515, 55);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(22, 17, 318, 28);
		panel_2.add(searchField);
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//
		JPanel panel_3 = new JPanel();
		//----JPanel
		JPanel panel_31 = new JPanel();
		panel_31.setBounds(25, 44, 861, 308);
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
		scrollPane.setBounds(10, 11, 829, 269); 
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
		        scrollPane.setBounds(10, 11, 829, 269);
		        panel_31.add(scrollPane);
		    }
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(350, 17, 155, 28);
		panel_2.add(btnNewButton_1);
//----------------------------------------------------------------		
//----------------------------------------------------------------update





//////////////---ajouter aux imput



// Ajoutez le ListSelectionListener au tableau

JButton btnNewButton_2 = new JButton("Back");
btnNewButton_2.setForeground(new Color(255, 255, 255));
btnNewButton_2.setBackground(new Color(128, 128, 255));
btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
btnNewButton_2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		dispose();
		showAdherentIHM();
	}
});
btnNewButton_2.setBounds(753, 362, 99, 55);
panel.add(btnNewButton_2);
JButton btnNewButton = new JButton("Log out");
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
btnNewButton.setBackground(new Color(128, 128, 255));
btnNewButton.setForeground(new Color(255, 255, 255));
btnNewButton.setBounds(588, 362, 122, 55);
panel.add(btnNewButton);


	}
	private void showLogin() {
        // Code pour afficher la vue principale
	 Login mainView = new Login();
        mainView.setVisible(true);
    }
	private void showAdherentIHM() {
        // Code pour afficher la vue principale
		AdherentIHM mainView = new AdherentIHM();
        mainView.setVisible(true);
	}
}