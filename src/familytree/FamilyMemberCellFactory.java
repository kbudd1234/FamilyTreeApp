/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.image.ImageView;

/**
 *
 * @author kevinbudd
 */
public class FamilyMemberCellFactory extends TreeCell<FamilyMember> {
    
    Node leafIcon = new ImageView("familytree/leaf.png");
    Node branchIcon = new ImageView("familytree/branch.png");
    private Tooltip tooltip = new Tooltip();
    
    public FamilyMemberCellFactory (){
        
        
    }
    
    @Override
    public void updateItem(FamilyMember item, boolean empty) {
        
        
        super.updateItem(item, empty);
        
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(getString());
            //setGraphic(getTreeItem().getGraphic());
            tooltip.setText(getItem().printInfo());
            setTooltip(tooltip);
            if(getItem().getListOfChildren().size() != 0)
                setGraphic(branchIcon);
            else
                setGraphic(leafIcon);
            
        }
                
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
 

