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
    private TreeItem<FamilyMember> root = null;
    private FamilyMember selectedFamilyMember = null;
    private TreeItem<FamilyMember> selectedTreeItem = null;
    private FamilyMember rootFamilyMember = null;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        root = new TreeItem<>(blankRoot);
        
        familyTreeGenerator(root);

        treeView.setRoot(root);

        treeView.getSelectionModel().selectedItemProperty().addListener(treeSelectionListener);

        treeView.setEditable(true);

        treeView.setCellFactory((TreeView<FamilyMember> fm) -> new FamilyMemberCellFactory());
        
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
                
                selectedTreeItem = newValue;

                selectedFamilyMember = treeItem.getValue();
                
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

    //
    //######################################################################################
    //
    // Event handlers for buttons and menu Items section
    //
    
    @FXML
    private void btnUpdate_Click(ActionEvent event) {
        
        //construct a new family member with updated attributes
        FamilyMember updatedMember = new FamilyMember();
        updatedMember.setName(txtName.getText());
        updatedMember.setAge(Integer.parseInt(txtAge.getText()));
        updatedMember.setSpouseName(txtSpouseName.getText());
        updatedMember.setListOfChildren(selectedFamilyMember.getListOfChildren());
        updatedMember.setNationality(txtNationality.getText());
        updatedMember.setStateOfResidence(txtStateOfResidence.getText());
        
        //treeView.getSelectionModel().getSelectedItem().setValue(updatedMember);
        treeView.getSelectionModel().getSelectedItem().setValue(updatedMember);
        
        TreeItem<FamilyMember> parent = treeView.getSelectionModel().getSelectedItem().getParent();
        
        int i = parent.getValue().getListOfChildren().indexOf(selectedFamilyMember);
        parent.getValue().getListOfChildren().remove(selectedTreeItem);
        parent.getValue().getListOfChildren().add(i, updatedMember);
        
        
    }

    @FXML
    private void btnAddMember_Clicked(ActionEvent event) {
        
        TreeItem<FamilyMember> parent = treeView.getSelectionModel().getSelectedItem();
        if (parent==null) {
          parent = treeView.getRoot();
        }
        
        final FamilyMember newMember = new FamilyMember("new member");
        
        final TreeItem<FamilyMember> newNode = new TreeItem<>(newMember);
        
        parent.getChildren().add(newNode);
        parent.getValue().getListOfChildren().add(newMember);
        parent.setExpanded(true);
        treeView.getSelectionModel().select(newNode);
        
        System.out.println(parent.getValue().getListOfChildren());
        System.out.println(root.getValue().getListOfChildren());
        
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
        if (parent==null) {
          parent = treeView.getRoot();
        }
        
        final FamilyMember newMember = new FamilyMember("new member");
        
        final TreeItem<FamilyMember> newNode = new TreeItem<>(newMember);
        parent.getChildren().add(newNode);
        parent.getValue().getListOfChildren().add(newMember);
        parent.setExpanded(true);
        treeView.getSelectionModel().select(newNode);
        
        System.out.println(parent.getValue().getListOfChildren());
        System.out.println(root.getValue().getListOfChildren());
        
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

            //rootFamilyMember = new FamilyMember(LoadWrapper.getFamilyTreeRoot());
            
            root = new TreeItem<>(new FamilyMember(LoadWrapper.getFamilyTreeRoot()));
            
            familyTreeGenerator(root);

            treeView.setRoot(root);
            
            setFamilyTreeRootFilePath(file);
            
        } catch (Exception ex) { 

        }
        
    }

    
    
}
