/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author kevinbudd
 */
public class FamilyMemberCellFactory extends TreeCell<FamilyMember> {
    
    Node leafIcon = new ImageView("familytree/leaf.png");
    Node branchIcon = new ImageView("familytree/branch.png");
    private final Tooltip tooltip;
    TreeCell<FamilyMember> treeCell = new TreeCell<>();
    private TextField textField = new TextField();
    
    public FamilyMemberCellFactory (){
        this.tooltip = new Tooltip();
    }
    
    /*
    @Override
        public void startEdit() {
            super.startEdit();
 
            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().getName());
            setGraphic(getTreeItem().getGraphic());
        }
    */
    
    @Override
    public void updateItem(FamilyMember item, boolean empty) {
        super.updateItem(item, empty);
        
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
                setText(getString());
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
 

