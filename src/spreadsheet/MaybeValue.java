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
public interface MaybeValue extends Expression{

    /**
     * Check have value
     * @return boolean
     */
    public boolean hasValue();

    /**
     * Get the value
     * @return
     */
    public double getValue();
}
