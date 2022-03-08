package pl.first.firstjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.first.firstjava.exceptions.EmptyListException;
import pl.first.firstjava.exceptions.SudokuFieldSetValueException;

public class SudokuField
        implements SudokuObservable, Comparable<SudokuField>, Serializable, Cloneable {
    private int value;
    private List<SudokuObserver> observers = new ArrayList<>();
    private ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");
    private boolean isEditable = false;

    public SudokuField() {
    }

    public SudokuField(int value) {
        if (value > 9 || value < 0) {
            throw new SudokuFieldSetValueException(bundle.getObject("outOfField").toString());
        } else {
            this.value = value;
        }
    }

    public SudokuField(SudokuObserver observer) {
        this.observers.add(observer);
    }

    public SudokuField(int value, SudokuObserver observer) {
        if (value > 9 || value < 0) {
            throw new SudokuFieldSetValueException(bundle.getObject("outOfField").toString());
        } else {
            this.value = value;
            this.observers.add(observer);
        }
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public boolean isEditable() {
        return isEditable;
    }


    public int getFieldValue() {
        return this.value;
    }

    public List<SudokuObserver> getObservers() {
        return observers;
    }

    @Override
    public void addObserver(SudokuObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(SudokuObserver observer) {
        if (this.observers.size() == 0) {
            throw new EmptyListException(bundle.getObject("emptyList").toString());
        } else {
            observers.remove(observer);
        }
    }

    public void setFieldValue(int value) {
        if (value > 9 || value < 0) {
            throw new SudokuFieldSetValueException(bundle.getObject("outOfField").toString());
        } else {
            this.value = value;
            this.observers.forEach(SudokuObserver::update);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("value", value).toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        try {
            SudokuField clone = (SudokuField) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException(bundle.getObject("cloneFailed").toString());
        }
    }

    @Override
    public int compareTo(SudokuField sudokuField) {
        if (sudokuField == null) {
            throw new NullPointerException();
        } else if (sudokuField.value == this.value) {
            return 0;
        } else if (this.value > sudokuField.value) {
            return -1;
        } else {
            return 1;
        }
    }
}
