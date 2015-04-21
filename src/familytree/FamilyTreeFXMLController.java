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
    //private FamilyMember lucy = new FamilyMember("Lucy Mara", 28, "Kevin", "American", "Illinois");
    private FamilyMember ever = new FamilyMember("Ever Budd", 1, "Single", "American", "Illinois");
    //private FamilyMember mike = new FamilyMember("Mike Budd", 56, "Lynn", "American", "Illinois");

    private FamilyMember jen = new FamilyMember("Jennifer Bredberg", 34, "Jim", "American", "Illinois");
    private FamilyMember chris = new FamilyMember("Chris Budd", 33, "Kate", "American", "Illinois");
    private FamilyMember kyle = new FamilyMember("Kyle Budd", 30, "Single", "American", "Illinois");
    private FamilyMember ben = new FamilyMember("Ben Bredberg", 4, "Single", "American", "Illinois");
    private FamilyMember madelyn = new FamilyMember("Madelyn Bredberg", 2, "Single", "American", "Illinois");

    private TreeItem<FamilyMember> root;

    private TreeItem<FamilyMember> jenTree = new TreeItem<>(jen);
    private TreeItem<FamilyMember> chrisTree = new TreeItem<>(chris);
    private TreeItem<FamilyMember> kevinTree = new TreeItem<>(kevin);
    private TreeItem<FamilyMember> kyleTree = new TreeItem<>(kyle);
    private TreeItem<FamilyMember> lynnTree = new TreeItem<>(lynn);
    private TreeItem<FamilyMember> jodyTree = new TreeItem<>(jody);
    private TreeItem<FamilyMember> jeanineTree = new TreeItem<>(jeanine);
    private TreeItem<FamilyMember> christineTree = new TreeItem<>(christine);
    private TreeItem<FamilyMember> kellyTree = new TreeItem<>(kelly);
    private TreeItem<FamilyMember> scottTree = new TreeItem<>(scott);
    private TreeItem<FamilyMember> kevinDawsonTree = new TreeItem<>(kevinDawson);
    private TreeItem<FamilyMember> bradleyTree = new TreeItem<>(bradley);
    private TreeItem<FamilyMember> bridgetTree = new TreeItem<>(bridget);
    private TreeItem<FamilyMember> erinTree = new TreeItem<>(erin);
    private TreeItem<FamilyMember> jenniferTree = new TreeItem<>(jennifer);
    private TreeItem<FamilyMember> kurtTree = new TreeItem<>(kurt);

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        populateFamilyTree();

        treeView.setRoot(root);

        treeView.getSelectionModel().selectedItemProperty().addListener(treeSelectionListener);

        treeView.setEditable(true);

        treeView.setCellFactory((TreeView<FamilyMember> fm) -> new FamilyMemberCellFactory());

    }

    public void populateFamilyTree() {

        kevin.getListOfChildren().add(ever);
        jen.getListOfChildren().addAll(ben, madelyn);
        lynn.getListOfChildren().addAll(jen, chris, kevin, kyle);
        jody.getListOfChildren().addAll(bridget, erin, jennifer, kurt);
        christine.getListOfChildren().addAll(kelly, scott, kevinDawson, bradley);
        gerry.getListOfChildren().addAll(lynn, jody, jeanine, christine);
        
        
        root = new TreeItem(gerry);
       
        root.getValue().getListOfChildren().forEach((fm) -> {
            root.getChildren().add(new TreeItem<FamilyMember>((FamilyMember) fm));
        });
        /*
        root.getChildren().forEach((fm) -> {
            fm.getChildren().add(new TreeItem<FamilyMember>((FamilyMember) fm));
        });
        */
        /*
        
        for(int i = 0; i < root.getValue().getListOfChildren().size(); i++)
        {
            root.getChildren().add(new TreeItem(root.getValue().getListOfChildren().get(i)));
        }   
            
        */
        
        /*
        root.getChildren().addAll(lynnTree, jodyTree, jeanineTree, christineTree);
        root.setExpanded(false);

        lynnTree.getChildren().addAll(jenTree, chrisTree, kevinTree, kyleTree);
        jenTree.getChildren().addAll(new TreeItem<>(ben), new TreeItem<>(madelyn));
        kevinTree.getChildren().add(new TreeItem<>(ever));

        jodyTree.getChildren().addAll(bridgetTree, erinTree, jenniferTree, kurtTree);

        christineTree.getChildren().addAll(kellyTree, scottTree, kevinDawsonTree, bradleyTree);
        */
    }

    private final ChangeListener<TreeItem<FamilyMember>> treeSelectionListener
            = (ov, oldValue, newValue) -> {
                TreeItem<FamilyMember> treeItem = newValue;

                if (treeItem == null || treeItem.equals(treeView.getRoot())) {
                    clearTextFields();
                    return;
                }

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
        
        if(selectedFamilyMember.equals(root.getValue()))
        {
            txtName.setText(root.getValue().getName());
            txtAge.setText(String.valueOf(root.getValue().getAge()));
            txtSpouseName.setText(root.getValue().getSpouseName());
            txtNumberOfChildren.setText(String.valueOf(root.getValue().getListOfChildren().size()));
            txtNationality.setText(root.getValue().getNationality());
            txtStateOfResidence.setText(root.getValue().getStateOfResidence());
        }
        else
        {
            txtName.textProperty().bindBidirectional(fm.nameProperty());
            txtAge.textProperty().bindBidirectional(new SimpleIntegerProperty(fm.ageProperty().getValue()), format);
            txtSpouseName.textProperty().bindBidirectional(fm.spouseNameProperty());
            txtNumberOfChildren.textProperty().bindBidirectional(new SimpleIntegerProperty(fm.listOfChildrenProperty().size()), format);
            txtNationality.textProperty().bindBidirectional(fm.nationalityProperty());
            txtStateOfResidence.textProperty().bindBidirectional(fm.stateOfResidenceProperty());
        }
    }

    @FXML
    private void btnUpdate_Click(ActionEvent event) {

    }

}
