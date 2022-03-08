package pl.first.firstjava.model;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class SudokuCheckPart implements Serializable, Cloneable {
    private SudokuField[] fields;
    private ResourceBundle bundle = ResourceBundle.getBundle("pl.first.firstjava.Language");

    public SudokuCheckPart(SudokuField[] fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields[j].getFieldValue() == fields[i].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fields", fields).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuCheckPart that = (SudokuCheckPart) o;

        return new EqualsBuilder().append(fields, that.fields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }

    @Override
    public SudokuCheckPart clone() throws CloneNotSupportedException {
        try {
            SudokuCheckPart clone = (SudokuCheckPart) super.clone();
            for (int i = 0; i < 9; i++) {
                clone.fields[i] = this.fields[i].clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException(bundle.getObject("cloneFailed").toString());
        }
    }
}
