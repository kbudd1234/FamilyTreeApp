/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import javafx.scene.Node;
import javafx.scene.control.TreeCell;

/**
 *
 * @author kevinbudd
 */
public class FamilyMemberCellFactory extends TreeCell<FamilyMember> {
    
    public FamilyMemberCellFactory (){
    }
    
    private Node graphic;
        
    @Override
    public void updateItem(FamilyMember item, boolean empty) {
        super.updateItem(item, empty);
        
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(getString());
            setGraphic(getTreeItem().getGraphic());
            
        }
                
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
 

