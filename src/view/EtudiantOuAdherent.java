package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EtudiantOuAdherent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtudiantOuAdherent frame = new EtudiantOuAdherent();
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
	public EtudiantOuAdherent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 64, 64));
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Student");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				showAffichageEtudiant();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(27, 76, 146, 45);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enseignant");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(128, 128, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				showAffichageEnseignant();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_1.setBounds(223, 76, 190, 45);
		panel.add(btnNewButton_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(128, 128, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				showAdminIHM();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnBack.setBounds(140, 188, 146, 45);
		panel.add(btnBack);
	}
	 private void showAffichageEtudiant() {
	        // Code pour afficher la vue principale
		 AffichageEtudiant mainView = new AffichageEtudiant();
	        mainView.setVisible(true);
	    }
	 private void showAffichageEnseignant() {
	        // Code pour afficher la vue principale
		 AffichageEnseignant mainView = new AffichageEnseignant();
	        mainView.setVisible(true);
	    }
	 private void showAdminIHM() {
	        // Code pour afficher la vue principale
		 AdminIHM mainView = new AdminIHM();
	        mainView.setVisible(true);
	    }

}
