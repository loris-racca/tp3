package fr.univ_amu.iut.exercice1;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PropertyExample {
    private final ChangeListener changeListener = (ObservableValue observableValue, Object oldValue, Object newValue) ->
            System.out.println("The observableValue has changed: oldValue = " + oldValue + ", newValue = " + newValue);
    private final InvalidationListener invalidationListener = observable ->
            System.out.println("The observable has been invalidated: " + observable + ".");
    private IntegerProperty anIntProperty;

    public static void main(String[] args) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createProperty();
        propertyExample.addAndRemoveInvalidationListener();
        propertyExample.addAndRemoveChangeListener();
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

    void createProperty() {
        anIntProperty = new SimpleIntegerProperty(1024);
        System.out.println();
        System.out.println("anIntProperty = " + anIntProperty);
        System.out.println("anIntProperty.get() = " + anIntProperty.get());
        System.out.println("anIntProperty.getValue() = " + anIntProperty.getValue());
    }

    void addAndRemoveInvalidationListener() {
        System.out.println();

        anIntProperty.addListener(invalidationListener);
        System.out.println("Added invalidation listener.");

        System.out.println("Calling anIntProperty.set(2048).");
        anIntProperty.set(2048);

        System.out.println("Calling anIntProperty.setValue(3072).");
        anIntProperty.setValue(Integer.valueOf(3072));

        anIntProperty.removeListener(invalidationListener);
        System.out.println("Removed invalidation listener.");

        System.out.println("Calling anIntProperty.set(4096).");
        anIntProperty.set(4096);
    }

    void addAndRemoveChangeListener() {
        System.out.println();

        anIntProperty.addListener(changeListener);
        System.out.println("Added change listener.");

        System.out.println("Calling anIntProperty.set(5120).");
        anIntProperty.set(5120);

        anIntProperty.removeListener(changeListener);
        System.out.println("Removed change listener.");

        System.out.println("Calling anIntProperty.set(6144).");
        anIntProperty.set(6144);
    }
}