/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import spreadsheet.exceptions.OperatorNotSuportedException;

/**
 * Represents an expression.
 * @author msf7
 */
public interface Expression {

    /**
     * Evaluate expression.
     * @return SomeValue or NoValue
     * @throws OperatorNotSuportedException
     */
    public MaybeValue evaluate()throws OperatorNotSuportedException;
    
}
