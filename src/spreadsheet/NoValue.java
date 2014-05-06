/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

/**
 * Respresents NullValue
 * @author msf7
 */
public class NoValue implements MaybeValue{

    /**
     * Check have value
     * @return boolean
     */
    @Override
    public boolean hasValue() {
        return false;
    }

    /**
     * Get the value
     * @return
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
    public double getValue() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "NoValue";
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        
        return obj instanceof NoValue;
        
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
}
