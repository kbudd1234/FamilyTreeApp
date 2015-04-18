/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author kevinbudd
 */
public class FamilyTreeFXMLController implements Initializable {
    
    private FamilyMember kevin = new FamilyMember("Kevin Budd", 31, "Lucy");
    private FamilyMember lucy = new FamilyMember("Lucy Mara", 28, "Kevin");
    private FamilyMember ever = new FamilyMember("Ever Budd", 1, "Single");
    private FamilyMember mike = new FamilyMember("Mike Budd", 56, "Lynn");
    private FamilyMember lynn = new FamilyMember("Lynn Budd", 56, "Mike");
    private FamilyMember jen = new FamilyMember("Jennifer Bredberg", 34, "Jim");
    private FamilyMember chris = new FamilyMember("Chris Budd", 33, "Kate");
    private FamilyMember kyle = new FamilyMember("Kyle Budd", 30, "Single");
    private FamilyMember ben = new FamilyMember("Ben Bredberg", 4, "Single");
    private FamilyMember madelyn = new FamilyMember("Madelyn Bredberg", 2, "Single");
    
    private TreeItem<FamilyMember> root;
    private TreeItem<FamilyMember> jenTree = new TreeItem<>(jen);
    private TreeItem<FamilyMember> chrisTree = new TreeItem<>(chris);
    private TreeItem<FamilyMember> kevinTree = new TreeItem<>(kevin);
    private TreeItem<FamilyMember> kyleTree = new TreeItem<>(kyle);
    
    
    
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtSpouseName;
    @FXML
    private TextField txtNumberOfChildren;
    @FXML
    private TextField txtNationality;
    @FXML
    private TextField txtStateOfResidence;
    @FXML
    private TreeView<FamilyMember> treeView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        populateFamilyTree();
        
    }    
    
    public void populateFamilyTree() {
        
        root = new TreeItem<>(mike);
        
        root.getChildren().addAll(jenTree, chrisTree, kevinTree, kyleTree);
        
        root.setExpanded(false);
        
        jenTree.getChildren().addAll(new TreeItem<>(ben), new TreeItem<>(madelyn));
        
        kevinTree.getChildren().add(new TreeItem<>(ever));
        
        treeView.setRoot(root);
        
    }
    
}
