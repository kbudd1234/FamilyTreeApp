/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author kevinbudd
 */
@XmlRootElement
public class FamilyMember {
    private final StringProperty name = new SimpleStringProperty(this, "name", "");
    private final StringProperty age = new SimpleStringProperty(this, "age", "");
    private final StringProperty spouseName = new SimpleStringProperty(this, "spouseName", "");
    private final ListProperty<FamilyMember> listOfChildren = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final StringProperty nationality = new SimpleStringProperty(this, "nationality", "");
    private final StringProperty stateOfResidence = new SimpleStringProperty(this, "stateOfResidence", "");

    
    public FamilyMember() {
        //this("", 0, "single", "American", "Unknown" );
        this("Family Tree");
    }
    
    public FamilyMember(String name) {
        this.name.set(name);
    }
    
    public FamilyMember(String name, String age, String spouse, String nationality, String stateOfResidence) {
        this.name.set(name);
        this.age.set(age);
        this.spouseName.set(spouse);
        this.nationality.set(nationality);
        this.stateOfResidence.set(stateOfResidence);
    }
    
    public FamilyMember(FamilyMember familyMember) {
        this.name.set(familyMember.getName());
        this.age.set(familyMember.getAge());
        this.spouseName.set(familyMember.getSpouseName());
        this.listOfChildren.set(familyMember.getListOfChildren());
        this.nationality.set(familyMember.getNationality());
        this.stateOfResidence.set(familyMember.getStateOfResidence());
    }
    
    public String getName() {
        return name.get();
    }
    @XmlElement
    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    public String getAge() {
        return age.get();
    }
    @XmlElement
    public void setAge(String value) {
        age.set(value);
    }

    public StringProperty ageProperty() {
        return age;
    }
    public String getSpouseName() {
        return spouseName.get();
    }
    @XmlElement
    public void setSpouseName(String value) {
        spouseName.set(value);
    }

    public StringProperty spouseNameProperty() {
        return spouseName;
    }
    
    public ObservableList getListOfChildren() {
        return listOfChildren.get();
    }
    @XmlElement
    public void setListOfChildren(ObservableList value) {
        listOfChildren.set(value);
    }

    public ListProperty listOfChildrenProperty() {
        return listOfChildren;
    }
    
    public String getNationality() {
        return nationality.get();
    }
    @XmlElement
    public void setNationality(String value) {
        nationality.set(value);
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }
    
    public String getStateOfResidence() {
        return stateOfResidence.get();
    }
    @XmlElement
    public void setStateOfResidence(String value) {
        stateOfResidence.set(value);
    }

    public StringProperty stateOfResidenceProperty() {
        return stateOfResidence;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.age);
        hash = 67 * hash + Objects.hashCode(this.spouseName);
        hash = 67 * hash + Objects.hashCode(this.listOfChildren);
        hash = 67 * hash + Objects.hashCode(this.nationality);
        hash = 67 * hash + Objects.hashCode(this.stateOfResidence);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FamilyMember other = (FamilyMember) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.spouseName, other.spouseName)) {
            return false;
        }
        if (!Objects.equals(this.listOfChildren, other.listOfChildren)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (!Objects.equals(this.stateOfResidence, other.stateOfResidence)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-15s", getName());
    }
    
    public String printInfo() {
        return getName() + "\n" +
                getAge() + "\n" +
                getSpouseName() + "\n" +
                "number of Children: " + listOfChildren.size() + "\n" +
                getNationality() + "\n" +
                getStateOfResidence();
    }
    
    
}
