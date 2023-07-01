package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.UtilisateurDAO;
import model.Utilisateur;
import javax.swing.ImageIcon;



public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static String ay;
 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(224, 36, 80, 14);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(224, 120, 80, 14);
        contentPane.add(lblPassword);

        usernameField = new JTextField();
        usernameField.setBounds(224, 61, 200, 20);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(224, 145, 200, 20);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Se connecter");
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(128, 0, 0));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                Utilisateur utilisateur = utilisateurDAO.chercheParUsername(username);

                // Vérification des informations d'identification
                if (checkCredentials(username, password)&&(utilisateur.getRole()==0)) {
                    JOptionPane.showMessageDialog(Login.this, "connection effectuer avec succes", "Succés de connexion", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    showAdminPanel();
                } 
                else if (checkCredentials(username, password)&&(utilisateur.getRole()==1)) {
                    JOptionPane.showMessageDialog(Login.this, "connection effectuer avec succes", "Succés de connexion", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    showEtudiantPanel() ;
                    
                    }
                else if (checkCredentials(username, password)&&(utilisateur.getRole()==2)) { 
                    JOptionPane.showMessageDialog(Login.this, "connection effectuer avec succes", "Succés de connexion", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    showEnseignanatPanel() ;}
                else {
                	
                    
                    
                    if(username == null || username.length() < 3 || username.equals("") )
            		{
            			JOptionPane.showMessageDialog(null, "Votre Pseudo doit contenir au moin 3 character.", "Erreur", JOptionPane.ERROR_MESSAGE);
            		}else if(password == null ||  password.equals(""))
            		{
            			JOptionPane.showMessageDialog(null, "Mot de passe ne peut pas etre vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            		}
            		            		
            		else {
            			JOptionPane.showMessageDialog(Login.this, "mdp incorrect", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
            		}
                }
            }
        });
        
      
        
        
        
        
        btnLogin.setBounds(248, 189, 144, 25);
        contentPane.add(btnLogin);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 64, 64));
        panel.setBounds(0, 0, 214, 261);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Welcome");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblNewLabel.setBounds(0, 23, 163, 39);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("To Your");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblNewLabel_1.setBounds(107, 73, 163, 39);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Library");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBackground(new Color(128, 64, 64));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblNewLabel_2.setBounds(0, 132, 225, 32);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Application");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblNewLabel_3.setBounds(61, 186, 194, 44);
        panel.add(lblNewLabel_3);
    }

    private boolean checkCredentials(String username, String password) {
        // Vérifie les informations d'identification avec UtilisateurDAO ou tout autre moyen approprié
        // Retourne true si les informations d'identification sont valides, sinon retourne false
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur utilisateur = utilisateurDAO.chercheParUsername(username);
        ay=utilisateur.getNom();        
         
         
        
        

        if (utilisateur!=null && utilisateur.getMdp().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
  
   
    

    private void showAdminPanel() {
        // Code pour afficher la vue principale
        AdminPanel mainView = new AdminPanel();
        mainView.setVisible(true);
    }
    private void showEtudiantPanel() {
        // Code pour afficher la vue principale
    	EtudiantPanel mainView = new EtudiantPanel();
        mainView.setVisible(true);
    }
    private void showEnseignanatPanel() {
        // Code pour afficher la vue principale
    	EnseignantPanel mainView = new EnseignantPanel();
        mainView.setVisible(true);
    }
}