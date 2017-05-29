package fr.univ_amu.iut.exercice1;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class PropertyExample {
    private ChangeListener changeListener;
    private InvalidationListener invalidationListener;

    private IntegerProperty anIntProperty;

    public static void main(String[] args) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createProperty();
        propertyExample.addAndRemoveInvalidationListener();
        propertyExample.addAndRemoveChangeListener();
    }

    void createProperty() {
        throw new RuntimeException("Not yet implemented !");
    }

    void addAndRemoveInvalidationListener() {
        throw new RuntimeException("Not yet implemented !");
    }

    void addAndRemoveChangeListener() {
        throw new RuntimeException("Not yet implemented !");
    }


    public int getAnInt() {
        return anIntProperty.get();
    }

    public void setAnInt(int anInt) {
        if (anIntProperty == null)
            anIntProperty = new SimpleIntegerProperty();
        this.anIntProperty.set(anInt);
    }

    public IntegerProperty anIntProperty() {
        return anIntProperty;
    }

}