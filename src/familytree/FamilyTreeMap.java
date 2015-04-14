/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/**
 *
 * @author kevinbudd
 */
public class FamilyTreeMap {
    
    private final ObservableMap<Integer, FamilyMember> observableMap = FXCollections.observableHashMap();
    private static FamilyTreeMap instance = null;
    
    protected FamilyTreeMap() {
        
    }
    
    public static FamilyTreeMap getInstance() {
        if (instance == null) {
            instance = new FamilyTreeMap();
        }
        return instance;
    }
    
    public void addListener(MapChangeListener<? super Integer, ? super FamilyMember> ml) {
        observableMap.addListener(ml);
    }
    
    public void removelistener(MapChangeListener<? super Integer, ? super FamilyMember> ml) {
        observableMap.removeListener(ml);
    }
    
    public void addListener(InvalidationListener il) {
        observableMap.addListener(il);
    }
    
    public void removeListener(InvalidationListener il) {
        observableMap.removeListener(il);
    }
    
    public void addFamilyMember(FamilyMember fm) {
        FamilyMember familyMember = new FamilyMember(fm);
        observableMap.put(familyMember.hashCode(), familyMember);
    }
    
    public void updateFamilyMember(FamilyMember fm) {
        FamilyMember familyMember = new FamilyMember(fm);
        observableMap.put(familyMember.hashCode(), familyMember);
    }
    
    public void deleteFamilyMember(FamilyMember fm) {
        observableMap.remove(fm.hashCode());
    }
    
    public List<FamilyMember> getWholeFamily() {
        List<FamilyMember> copyList = new ArrayList<>();
        observableMap.values().stream().forEach((fm) -> 
                copyList.add(new FamilyMember(fm)));
        return copyList;
    }
    
    
}
