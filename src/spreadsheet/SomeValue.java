/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

/**
 * Respresents a constant value.
 * @author msf7
 */
public class SomeValue implements MaybeValue{

    private final double value;
    
    /**
     *
     * @param value
     */
    public SomeValue(double value) {
       this.value = value;
    }

    /**
     * Check have value
     * @return boolean
     */
    @Override
    public boolean hasValue() {
        return true;
    }

    /**
     * Get the value
     * @return MaybeValue
     */
    @Override
    public MaybeValue evaluate() {
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.value) ^ 
                                 (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }

    /**
     * Get the value
     * @return double
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "SomeValue{" + "value=" + value + '}';
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SomeValue other = (SomeValue) obj;
        return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(other.value);
    } 
 
}
