/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author kevinbudd
 */
public class FamilyTreeFXMLController implements Initializable {

    private FamilyMember gerry = new FamilyMember("Gerry DeGraff", 83, "Richard", "American", "Illinois");
    private FamilyMember lynn = new FamilyMember("Lynn Budd", 56, "Mike", "American", "Illinois");
    private FamilyMember jody = new FamilyMember("Jody Land", 54, "Jay", "American", "Illinois");
    private FamilyMember jeanine = new FamilyMember("Jeanine DeGraff", 52, "Single", "American", "Florida");
    private FamilyMember christine = new FamilyMember("Christine Dawson", 50, "Dan", "American", "Illinois");
    private FamilyMember kelly = new FamilyMember("Kelly Dawson", 24, "Single", "American", "Illinois");
    private FamilyMember scott = new FamilyMember("Scott Dawson", 22, "Single", "American", "Illinois");
    private FamilyMember kevinDawson = new FamilyMember("Kevin Dawson", 20, "Single", "American", "Illinois");
    private FamilyMember bradley = new FamilyMember("Bradley Dawson", 18, "Single", "American", "Illinois");
    private FamilyMember kevin = new FamilyMember("Kevin Budd", 31, "Lucy", "American", "Illinois");
    private FamilyMember bridget = new FamilyMember("Bridget Land", 19, "Single", "American", "Illinois");
    private FamilyMember erin = new FamilyMember("Erin Land", 17, "Single", "American", "Illinois");
    private FamilyMember jennifer = new FamilyMember("Jennifer Land", 15, "Single", "American", "Illinois");
    private FamilyMember kurt = new FamilyMember("Kurt Land", 12, "Single", "American", "Illinois");
    private FamilyMember lucy = new FamilyMember("Lucy Mara", 28, "Kevin", "American", "Illinois");
    private FamilyMember ever = new FamilyMember("Ever Budd", 1, "Single", "American", "Illinois");
    private FamilyMember mike = new FamilyMember("Mike Budd", 56, "Lynn", "American", "Illinois");
    private FamilyMember jen = new FamilyMember("Jennifer Bredberg", 34, "Jim", "American", "Illinois");
    private FamilyMember chris = new FamilyMember("Chris Budd", 33, "Kate", "American", "Illinois");
    private FamilyMember kyle = new FamilyMember("Kyle Budd", 30, "Single", "American", "Illinois");
    private FamilyMember ben = new FamilyMember("Ben Bredberg", 4, "Single", "American", "Illinois");
    private FamilyMember madelyn = new FamilyMember("Madelyn Bredberg", 2, "Single", "American", "Illinois");

    private TreeItem<FamilyMember> root = null;

    private FamilyMember selectedFamilyMember = null;
    
    private NumberFormat format = NumberFormat.getInstance();

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
    @FXML
    private Button btnUpdate;
    
    private TreeItem<FamilyMember> branch;
    private TreeItem<FamilyMember> leaf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        kevin.getListOfChildren().add(ever);
        jen.getListOfChildren().addAll(ben, madelyn);
        lynn.getListOfChildren().addAll(jen, chris, kevin, kyle);
        jody.getListOfChildren().addAll(bridget, erin, jennifer, kurt);
        christine.getListOfChildren().addAll(kelly, scott, kevinDawson, bradley);
        gerry.getListOfChildren().addAll(lynn, jody, jeanine, christine);
        
        root = new TreeItem<>(gerry);
        
        familyTreeGenerator(gerry);

        treeView.setRoot(root);

        treeView.getSelectionModel().selectedItemProperty().addListener(treeSelectionListener);

        treeView.setEditable(true);

        treeView.setCellFactory((TreeView<FamilyMember> fm) -> new FamilyMemberCellFactory());

    }
    
    public void familyTreeGenerator(FamilyMember member){
        
        if(root == null)
        {
            root = new TreeItem<>(member);
        }
        else
        {
            if(member.getListOfChildren().size() != 0)
            {
                branch = new TreeItem<>((FamilyMember) member);
                member.getListOfChildren().forEach((fm) -> {
                    familyTreeGenerator((FamilyMember) fm);

                });
            }
            else
            {
                branch.getValue().getListOfChildren().forEach((fm) -> {
                    leaf = new TreeItem<>((FamilyMember) fm);
                    branch.getChildren().add(leaf);
                });
            }
        root.getChildren().add(branch);
        }
        
    }
    
    /*
    public void populateFamilyTree() {

        root.getValue().getListOfChildren().stream().forEach((fm) -> {
            root.getChildren().add(new TreeItem<>((FamilyMember) fm));
        });
        
        try{
        for (int i = 0; i < root.getChildren().size(); i++)
        {
           for (int j = 0; j < root.getChildren().get(i).getValue().getListOfChildren().size(); j++)
           {
            root.getChildren().get(i).getChildren().add(new TreeItem(root.getChildren().get(i).getValue().getListOfChildren().get(j)));
            
           }
        }
        } catch(NullPointerException ex) {
            
        }
    }
    */
    
    
    private final ChangeListener<TreeItem<FamilyMember>> treeSelectionListener
            = (ov, oldValue, newValue) -> {
                TreeItem<FamilyMember> treeItem = newValue;

                selectedFamilyMember = new FamilyMember(treeItem.getValue());
                
                familyMemberTextFieldBindings(selectedFamilyMember);

            };

    private void clearTextFields() {
        txtName.setText("");
        txtAge.setText("");
        txtSpouseName.setText("");
        txtNumberOfChildren.setText("");
        txtNationality.setText("");
        txtStateOfResidence.setText("");
    }

    private void familyMemberTextFieldBindings(FamilyMember fm) {
        
        txtName.textProperty().bindBidirectional(fm.nameProperty());
        txtAge.textProperty().bindBidirectional(new SimpleIntegerProperty(fm.ageProperty().getValue()), format);
        txtSpouseName.textProperty().bindBidirectional(fm.spouseNameProperty());
        txtNumberOfChildren.textProperty().bindBidirectional(new SimpleIntegerProperty(fm.listOfChildrenProperty().size()), format);
        txtNationality.textProperty().bindBidirectional(fm.nationalityProperty());
        txtStateOfResidence.textProperty().bindBidirectional(fm.stateOfResidenceProperty());
    }

    @FXML
    private void btnUpdate_Click(ActionEvent event) {

    }

}
