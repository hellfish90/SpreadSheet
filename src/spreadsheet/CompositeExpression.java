/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import spreadsheet.exceptions.OperatorNotSuportedException;



/**
 * Represents a Composite Expression and contains a two expressions and one
 * operant.
 * @author msf7
 */
public class CompositeExpression implements Expression{

    private final Expression expr1;
    private final Expression expr2;
    private final String op;
    
    /**
     *
     * @param expr1
     * @param expr2
     * @param op
     */
    public CompositeExpression(Expression expr1, Expression expr2, String op) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.op = op;
    }   

    /**
     * Evaluate the expression.
     * @return SomeValue or NoValue
     * @throws OperatorNotSuportedException
     */
    @Override
    public MaybeValue evaluate()throws OperatorNotSuportedException {
        
        if(expr1 instanceof NoValue) return new NoValue();
        if(expr2 instanceof NoValue) return new NoValue();

        return evaluateRec(expr1,expr2,op); 
    }

    /**
     * Get first Expression.
     * @return Expression
     */
    public Expression getExpr1() {
        return expr1;
    }

    /**
     * Get second Expression.
     * @return Expression
     */
    public Expression getExpr2() {
        return expr2;
    }

    /**
     * Get operator 
     * @return String
     */
    public String getOp() {
        return op;
    }

    private MaybeValue binaryOperation(Expression exprR1, Expression exprR2, 
                                        String opR) 
                                        throws OperatorNotSuportedException {
        
        double first = ((SomeValue) exprR1).getValue();
        double second =((SomeValue) exprR2).getValue();
        
        switch(opR){  
            case "+":
                return new SomeValue(first + second);
            case "*":
                return new SomeValue(first * second);
            default:
                throw new OperatorNotSuportedException();
        }
    }

    private MaybeValue evaluateRec(Expression exprR1, Expression exprR2, 
                                    String opR) 
                                    throws OperatorNotSuportedException {
        
        if(exprR1 instanceof SomeValue && exprR2 instanceof SomeValue){
            return binaryOperation(exprR1,exprR2,opR);
        }
        
        if (exprR1 instanceof CompositeExpression ||
            exprR1 instanceof ReferenceCellExpression) {
            return evaluateRec(exprR1.evaluate(), exprR2, opR);
        }
        
        if (exprR2 instanceof CompositeExpression ||
            exprR2 instanceof ReferenceCellExpression) {
            return evaluateRec(exprR1, exprR2.evaluate(), opR);
        }
        
        return new NoValue();
    }
    
}
