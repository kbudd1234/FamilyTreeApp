/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import javafx.scene.control.TreeItem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "familyMembers")
public class FamilyMemberWrapper {

    private TreeItem<FamilyMember> familyTreeRoot;

    @XmlElement(name = "Member")
    public TreeItem<FamilyMember> getFamilyTreeRoot() {
        return familyTreeRoot;
    }

    public void setFamilyTreeRoot(TreeItem<FamilyMember> familyTreeRoot) {
        this.familyTreeRoot = familyTreeRoot;
    }
}
