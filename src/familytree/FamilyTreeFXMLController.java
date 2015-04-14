/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.MapChangeListener;
import javafx.fxml.Initializable;

/**
 *
 * @author kevinbudd
 */
public class FamilyTreeFXMLController implements Initializable {
    
    final FamilyTreeMap familyTreeMap = FamilyTreeMap.getInstance();
    final FamilyMember kevin = new FamilyMember("Kevin Budd", 31, "Lucy");
    final FamilyMember ever = new FamilyMember("Ever Budd", 1, "Single");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //familyTreeMap.addListener(mapChangeListener);
        populateFamily();
        
        try {
        kevin.listOfChildrenProperty().add(ever);
        } catch (NullPointerException ex) {
        
        }
        System.out.println(kevin.listOfChildrenProperty().size());
        System.out.println(kevin.getListOfChildren());
        
    }    
    
    public void populateFamily() {
        familyTreeMap.addFamilyMember(kevin);
        familyTreeMap.addFamilyMember(ever);
        
        
    }
    
    private static final MapChangeListener<Integer, FamilyMember> mapChangeListener = (change) -> {
        if (change.wasAdded() && change.wasRemoved()) {
            System.out.println("\tUPDATED");
        } else if (change.wasAdded()) {
            System.out.println("\tADDED");
        } else if (change.wasRemoved()) {
            System.out.println("\tREMOVED");
        }
        System.out.println(change.getMap());
    };
    
}
