/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import spreadsheet.exceptions.OperatorNotSuportedException;
import java.util.Objects;

/**
 * Represents an expression that references a cell.
 * @author msf7
 */
public class ReferenceCellExpression implements Expression{

    private final Cell cell;

    /**
     *
     * @param cell
     */
    public ReferenceCellExpression(Cell cell) {
        this.cell = cell;
    }

    /**
     * Evaluate the expression.
     * @return SomeValue or NoValue
     * @throws OperatorNotSuportedException
     */
    @Override
    public MaybeValue evaluate() {
        try {
            return cell.getExpr().evaluate();
        } catch (OperatorNotSuportedException ex) {
            return new NoValue();
        }
    }

    /**
     * Get the cell that reference.
     * @return
     */
    public Cell getCell() {
        return cell;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.cell);
        return hash;
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
        final ReferenceCellExpression other = (ReferenceCellExpression) obj;
        return Objects.equals(this.cell, other.cell);
    }
    
}
