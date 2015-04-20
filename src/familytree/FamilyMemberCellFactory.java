/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;

/**
 *
 * @author kevinbudd
 */
public class FamilyMemberCellFactory extends TreeCell<FamilyMember> {
    
    public FamilyMemberCellFactory (){
    }
    
    private TextField textField;
    
    private void createTextField() {
            textField = new TextField(getItem().getName());
            
        }
 
    
    
    @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem().getName());
            setGraphic(getTreeItem().getGraphic());
        }
    
    static FamilyTreeFXMLController fm = new FamilyTreeFXMLController();
    
    public static void main(String[] args) {
        
        
        
        try {
        Tooltip printInfoToolTip = new Tooltip(fm.selectedFamilyMember().printInfo());
        } catch (NullPointerException ex) {
            
        }
    }
    
    


    
    
}
