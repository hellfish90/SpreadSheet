/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import spreadsheet.exceptions.OperatorNotSuportedException;

/**
 * Represents a cell and contains a reference, an expression and a value. 
 * @author msf7
 */
public class Cell extends Observable implements Observer{
    
    private final String ref;
    private MaybeValue value;
    private Expression expr;
     
    /**
     *
     * @param ref
     * @param expr
     * @throws OperatorNotSuportedException
     */
    public Cell(String ref,Expression expr) throws OperatorNotSuportedException {
        this.ref = ref;
        this.expr = expr;
        this.value = expr.evaluate();
        
    }

    /**
     * Get reference.
     * @return
     */
    public String getRef() {
        return ref;
    }

    /**
     * Get the value of the cell.
     * @return MaybeValue
     */
    public MaybeValue getValue() {
        return value;
    }
    
    /**
     * Get the object Expression of this cell.
     * @return Expression
     */
    public Expression getExpr() {
        return expr;
    }

    /**
     * Assign an expression.
     * @param expr
     * @throws OperatorNotSuportedException
     */
    public void setExpr(Expression expr) throws OperatorNotSuportedException {  
        this.expr = expr;
        addObserversCell(expr);
        setChanged();
        notifyObservers();       
        value = expr.evaluate();
    }

    /**
     * Update the value of the cell.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg){
        try {
            value = expr.evaluate();
        } catch (OperatorNotSuportedException ex) {
            Logger.getLogger(Cell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.ref);
        hash = 83 * hash + Objects.hashCode(this.value);
        hash = 83 * hash + Objects.hashCode(this.expr);
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
        final Cell other = (Cell) obj;
        if (!Objects.equals(this.ref, other.ref)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.expr, other.expr);
    }

    private void addObserversCell(Expression expr) {
        
        addObserversCellRec(this,expr);

    }
    
    private void addObserversCellRec(Cell cell, Expression expression){      

        if(expression instanceof CompositeExpression){
            CompositeExpression exprC = (CompositeExpression) expression; 
            addObserversCellRec(cell,exprC.getExpr1());
            addObserversCellRec(cell,exprC.getExpr2());    
        }  
        if (expression instanceof ReferenceCellExpression) {
            ReferenceCellExpression exprR = (ReferenceCellExpression) expression;
            addObserversCellRec(exprR.getCell(),exprR.getCell().getExpr());
        }
         if (expression instanceof MaybeValue) {
             if (!cell.equals(this)) {
               cell.addObserver(this); 
             }
            
         }
     }
        
}
