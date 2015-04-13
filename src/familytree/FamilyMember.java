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
import javafx.collections.ObservableList;

/**
 *
 * @author kevinbudd
 */
public class FamilyMember {
    private final StringProperty name = new SimpleStringProperty(this, "name", "");
    private final IntegerProperty age = new SimpleIntegerProperty(this, "age", 0);
    private final StringProperty spouseName = new SimpleStringProperty(this, "spouseName", "");
    private final ListProperty<FamilyMember> listOfChildren = new SimpleListProperty(this, "listOfChildren", null);
    private final StringProperty nationality = new SimpleStringProperty(this, "nationality", "");
    private final StringProperty stateOfResidence = new SimpleStringProperty(this, "stateOfResidence", "");

    
    public FamilyMember() {
        this("", 0);
    }
    
    public FamilyMember(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }
    
    public FamilyMember(FamilyMember familyMember) {
        this.name.set(familyMember.getName());
        this.age.set(familyMember.getAge());
        this.spouseName.set(familyMember.getSpouseName());
        this.listOfChildren.set(familyMember.getListOfChildren());
        this.nationality.set(familyMember.getNationality());
        this.stateOfResidence.set(familyMember.getStateOfResidence());
    }
    
    private String getName() {
        return name.get();
    }

    private void setName(String value) {
        name.set(value);
    }

    private StringProperty nameProperty() {
        return name;
    }
    
    private int getAge() {
        return age.get();
    }

    private void setAge(int value) {
        age.set(value);
    }

    private IntegerProperty ageProperty() {
        return age;
    }
    private String getSpouseName() {
        return spouseName.get();
    }

    private void setSpouseName(String value) {
        spouseName.set(value);
    }

    private StringProperty spouseNameProperty() {
        return spouseName;
    }
    
    private ObservableList getListOfChildren() {
        return listOfChildren.get();
    }

    private void setListOfChildren(ObservableList value) {
        listOfChildren.set(value);
    }

    private ListProperty listOfChildrenProperty() {
        return listOfChildren;
    }
    
    private String getNationality() {
        return nationality.get();
    }

    private void setNationality(String value) {
        nationality.set(value);
    }

    private StringProperty nationalityProperty() {
        return nationality;
    }
    
    private String getStateOfResidence() {
        return stateOfResidence.get();
    }

    private void setStateOfResidence(String value) {
        stateOfResidence.set(value);
    }

    private StringProperty stateOfResidenceProperty() {
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
        return "FamilyMember{" + "name=" + name + ", age=" + age + ", spouseName=" + spouseName + '}';
    }
    
    public String printInfo() {
        return name + "\n" +
                age + "\n" +
                spouseName + "\n" +
                "number of Children: " + listOfChildren.size() + "\n" +
                nationality + "\n" +
                stateOfResidence;
    }
    
    
}
