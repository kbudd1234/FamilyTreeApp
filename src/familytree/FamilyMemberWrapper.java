/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "familyMembers")
public class FamilyMemberWrapper {

    private FamilyMember familyTreeRoot;

    @XmlElement(name = "Member")
    public FamilyMember getFamilyTreeRoot() {
        return familyTreeRoot;
    }

    public void setFamilyTreeRoot(FamilyMember familyTreeRoot) {
        this.familyTreeRoot = familyTreeRoot;
    }
}
