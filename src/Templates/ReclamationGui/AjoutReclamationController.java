/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.ReclamationGui;


import Entities.Feedback;
import Entities.Reclamation;

import Services.ServiceReclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private TextField prod;
    @FXML
    private TextField type;
    @FXML
    private TextField sujet;
    @FXML
    private TextField desc;
    @FXML
    private TextField etat;
    @FXML
    private TextField user;
    @FXML
    private Button ajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutReclamation(ActionEvent event) {
        ServiceReclamation r = new ServiceReclamation();
        
        r.ajouter(new Reclamation(type.getText(),prod.getText(),sujet.getText(),desc.getText(),etat.getText(),user.getText()));
        JOptionPane.showMessageDialog(null,"Personne Ajoutée");
    }
    
    
}
