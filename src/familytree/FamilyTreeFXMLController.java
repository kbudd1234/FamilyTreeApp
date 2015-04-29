/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author kevinbudd
 */
public class FamilyTreeFXMLController implements Initializable {
    private FamilyMember blankRoot = new FamilyMember("Family Tree");
    private TreeItem<FamilyMember> newRoot;
    private TreeItem<FamilyMember> root = null;
    private FamilyMember selectedFamilyMember = null;
    private TreeItem<FamilyMember> selectedTreeItem = null;
    private NumberFormat format = NumberFormat.getInstance();
    //private NumberFormat formatStringToInt
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
    @FXML
    private Button btnAddMember;
    @FXML
    private MenuItem mnuSave;
    @FXML
    private MenuItem mnuSaveAs;
    @FXML
    private MenuItem btnExit;
    @FXML
    private MenuItem mnuOpen;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem mnuInfo;
    @FXML
    private MenuItem mnuAddNewMember;
    @FXML
    private MenuItem mnuDelete;
    
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
    @FXML
    private MenuItem mnuNew;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        lynn.getListOfChildren().addAll(jen, chris, kevin, kyle);
        jen.getListOfChildren().addAll(ben, madelyn);
        kevin.getListOfChildren().add(ever);
        jody.getListOfChildren().addAll(bridget, erin, jennifer, kurt);
        christine.getListOfChildren().addAll(kelly, scott, kevinDawson, bradley);
        gerry.getListOfChildren().addAll(lynn, jody, jeanine, christine);
        
        
        root = new TreeItem<>(blankRoot);
        
        familyTreeGenerator(root);

        treeView.setRoot(root);

        treeView.getSelectionModel().selectedItemProperty().addListener(treeSelectionListener);
        
        treeView.setEditable(true);

        treeView.setCellFactory((TreeView<FamilyMember> currentFamilyMember) -> new FamilyMemberCellFactory());
        
    }
    
    public void familyTreeGenerator(TreeItem<FamilyMember> member){
        
        if(member.getValue().getListOfChildren().size() !=0)
        { 
            for (Object listOfChildren : member.getValue().getListOfChildren()) 
            {
                TreeItem<FamilyMember> temp = new TreeItem<>((FamilyMember) listOfChildren);
                
                member.getChildren().add(temp);
                
                familyTreeGenerator(temp);
            }
        }
        
    }
    
    private final ChangeListener<TreeItem<FamilyMember>> treeSelectionListener
            = (ov, oldValue, newValue) -> {
                TreeItem<FamilyMember> treeItem = newValue;
                
                selectedTreeItem = (TreeItem<FamilyMember>) newValue;

                selectedFamilyMember = treeItem.getValue();
                
                if (oldValue!=null){
                    ((TreeItem<FamilyMember>)oldValue).getValue().nameProperty().unbindBidirectional(txtName.textProperty());
                    txtName.clear();
                    txtAge.textProperty().unbindBidirectional(((TreeItem<FamilyMember>)oldValue).getValue().ageProperty());
                    txtAge.clear();
                    ((TreeItem<FamilyMember>)oldValue).getValue().spouseNameProperty().unbindBidirectional(txtSpouseName.textProperty());
                    txtSpouseName.clear();
                    ((TreeItem<FamilyMember>)oldValue).getValue().nationalityProperty().unbindBidirectional(txtNationality.textProperty());
                    txtNationality.clear();
                    ((TreeItem<FamilyMember>)oldValue).getValue().stateOfResidenceProperty().unbindBidirectional(txtStateOfResidence.textProperty());
                    txtStateOfResidence.clear();
                    
                    
                    
                }
                if (newValue!=null){
                    txtName.setText(((TreeItem<FamilyMember>)newValue).getValue().nameProperty().getValue());
                    ((TreeItem<FamilyMember>)newValue).getValue().nameProperty().bindBidirectional(txtName.textProperty());
                    txtAge.setText((((TreeItem<FamilyMember>)newValue).getValue().ageProperty().getValue()).toString());
                    txtAge.textProperty().bindBidirectional(new SimpleIntegerProperty((((TreeItem<FamilyMember>)newValue).getValue().ageProperty().getValue())), format);
                    txtSpouseName.setText(((TreeItem<FamilyMember>)newValue).getValue().spouseNameProperty().getValue());
                    ((TreeItem<FamilyMember>)newValue).getValue().spouseNameProperty().bindBidirectional(txtSpouseName.textProperty());
                    txtNationality.setText(((TreeItem<FamilyMember>)newValue).getValue().nationalityProperty().getValue());
                    ((TreeItem<FamilyMember>)newValue).getValue().nationalityProperty().bindBidirectional(txtNationality.textProperty());
                    txtStateOfResidence.setText(((TreeItem<FamilyMember>)newValue).getValue().stateOfResidenceProperty().getValue());
                    ((TreeItem<FamilyMember>)newValue).getValue().stateOfResidenceProperty().bindBidirectional(txtStateOfResidence.textProperty());
                }
                
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

    //
    //######################################################################################
    //
    // Event handlers for buttons and menu Items section
    //
    
    
    @FXML
    private void btnUpdate_Click(ActionEvent event) {
        
        //treeView.getRoot();
        
    }
    
    @FXML
    private void btnAddMember_Clicked(ActionEvent event) {
        
        TreeItem<FamilyMember> parent = treeView.getSelectionModel().getSelectedItem();
        
        if (parent==null) 
          parent = treeView.getRoot();
        
        FamilyMember newMember = new FamilyMember("new member");
        
        parent.getValue().getListOfChildren().add(newMember);
        
        TreeItem<FamilyMember> newMemberTreeItem = new TreeItem<>(newMember);
        
        parent.getChildren().add(newMemberTreeItem);
        
        parent.setExpanded(true);
        
    }
    
    @FXML
    private void btnDelete_Click(ActionEvent event) {
        
        TreeItem<FamilyMember> parent = treeView.getSelectionModel().getSelectedItem().getParent();
        TreeItem<FamilyMember> familyMember = treeView.getSelectionModel().getSelectedItem();
        familyMember.getParent().getChildren().remove(familyMember);
        parent.getValue().getListOfChildren().remove(familyMember.getValue());
        
    }
    
     @FXML
    private FamilyMember handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "ROOT files (*.root)", "*.root");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());
        
        try{
             loadFamilyTreeRootFromFile(file);
        } catch (Exception ex){
        }
        
        return root.getValue();
        
    }
    
    
    @FXML
    private void handleSave() {
        File file = getFamilyTreeRootFilePath();
        if (file != null) {
            saveFamilyTreeRootToFile(file);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "ROOT files (*.root)", "*.root");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            if (!file.getPath().endsWith(".root")) {
                file = new File(file.getPath() + ".root");
            }
            saveFamilyTreeRootToFile(file);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleMnuInfo(ActionEvent event) {
       
        
    }

    @FXML
    private void handleMnuAddNewMember(ActionEvent event) {
        
        TreeItem<FamilyMember> parent = treeView.getSelectionModel().getSelectedItem();
        
        if (parent==null) 
          parent = treeView.getRoot();
        
        FamilyMember newMember = new FamilyMember("new member");
        
        parent.getValue().getListOfChildren().add(newMember);
        
        TreeItem<FamilyMember> newMemberTreeItem = new TreeItem<>(newMember);
        
        parent.getChildren().add(newMemberTreeItem);
        
        parent.setExpanded(true);
        
    }

    @FXML
    private void handleMnuDelete(ActionEvent event) {
        
        TreeItem<FamilyMember> parent = treeView.getSelectionModel().getSelectedItem().getParent();
        TreeItem<FamilyMember> familyMember = treeView.getSelectionModel().getSelectedItem();
        familyMember.getParent().getChildren().remove(familyMember);
        parent.getValue().getListOfChildren().remove(familyMember.getValue());
        
    }

    //
    //######################################################################################
    //
    //  Saving to and retrieving from a file Section
    //
    //  return the file path of the saved data if one is saved
    public File getFamilyTreeRootFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(FamilyTreeFXMLController.class);
        String filePath = prefs.get("filePath", null);
        
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
       
    }

    //save the file path of the saved data
    public void setFamilyTreeRootFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(FamilyTreeFXMLController.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

        } else {
            prefs.remove("filePath");

        }
    }
    
    public void saveFamilyTreeRootToFile(File file) {
        try{
            JAXBContext context = JAXBContext.newInstance(FamilyMemberWrapper.class);
            
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FamilyMemberWrapper saveWrapper = new FamilyMemberWrapper();
            
            saveWrapper.setFamilyTreeRoot(root.getValue());

            m.marshal(saveWrapper, file);

            setFamilyTreeRootFilePath(file);
            
        }
         catch (Exception ex){
            
        }
    }
    
    public void loadFamilyTreeRootFromFile(File file) {
        
        try {
            JAXBContext context = JAXBContext.newInstance(FamilyMemberWrapper.class);
            
            Unmarshaller um = context.createUnmarshaller();
            
            FamilyMemberWrapper LoadWrapper = (FamilyMemberWrapper) um.unmarshal(file);

            root = new TreeItem<>(LoadWrapper.getFamilyTreeRoot());
            
            familyTreeGenerator(root);

            treeView.setRoot(root);
            
            setFamilyTreeRootFilePath(file);
            
        } catch (Exception ex) { 

        }
        
    }

    @FXML
    private void handleNew(ActionEvent event) {
        
        FamilyMember newRootFamilyMember = new FamilyMember("New Family");
        newRoot = new TreeItem<>(newRootFamilyMember);
        treeView.setRoot(newRoot);
        
        
    }

    
    
}
