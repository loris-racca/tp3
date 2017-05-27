package fr.univ_amu.iut.exercice1;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PropertyExampleTest {
    private PrintStream out;
    private PropertyExample propertyExample;

    @Before
    public void setUp() throws Exception {
        propertyExample = new PropertyExample();
        propertyExample.setAnInt(1024);
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @Test
    public void createProperty() throws Exception {
        propertyExample.createProperty();

        verify(out).println();
        verify(out).println("anIntProperty = IntegerProperty [value: 1024]");
        verify(out).println("anIntProperty.get() = 1024");
        verify(out).println("anIntProperty.getValue() = 1024");
    }

    @Test
    public void addAndRemoveInvalidationListener() throws Exception {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.setAnInt(1024);

        propertyExample.addAndRemoveInvalidationListener();

        verify(out).println();
        verify(out).println("Added invalidation listener.");
        verify(out).println("Calling anIntProperty.set(2048).");
        verify(out).println("The observable has been invalidated: IntegerProperty [value: 2048].");
        verify(out).println("Calling anIntProperty.setValue(3072).");
        verify(out).println("The observable has been invalidated: IntegerProperty [value: 3072].");
        verify(out).println("Removed invalidation listener.");
        verify(out).println("Calling anIntProperty.set(4096).");
    }

    @Test
    public void addAndRemoveChangeListener() throws Exception {

        propertyExample.addAndRemoveChangeListener();

        verify(out).println();
        verify(out).println("Added change listener.");
        verify(out).println("Calling anIntProperty.set(5120).");
        verify(out).println("The observableValue has changed: oldValue = 1024, newValue = 5120");
        verify(out).println("Removed change listener.");
        verify(out).println("Calling anIntProperty.set(6144).");
    }

}