/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import spreadsheet.exceptions.OperatorNotSuportedException;

/**
 *
 * @author msf7
 */
public class ExpressionTest {
    
    Expression expr1;
    Expression expr2;
    Expression expr3;
    Expression expr4;
    Expression expr5;
    Expression expr6;
    Expression expr7;
    Expression exprNoValue;
    Expression compositeExpr;


    
    @Before
    public void setUp() throws OperatorNotSuportedException {
        
        expr1 = new SomeValue(10.0);
        Cell cell = new Cell("a1", expr1);
        expr2 = new SomeValue(20.0);
        expr3 = new SomeValue(30.0);
        expr4 = new SomeValue(40.0);
        expr7 = new ReferenceCellExpression(cell);
        exprNoValue = new NoValue();
        
    }

    @Test 
    public void NoValueInReferenceExpression() throws OperatorNotSuportedException{
       Cell cell = new Cell("a1", exprNoValue);
       expr7 = new ReferenceCellExpression(cell);
       compositeExpr = new CompositeExpression(expr1, expr7, "+");
       assertEquals(new NoValue(), compositeExpr.evaluate());
    }
    
    
    @Test 
    public void NoValueInCompostExpression() throws OperatorNotSuportedException{
       compositeExpr = new CompositeExpression(expr1, exprNoValue, "+");
       assertEquals(new NoValue(), compositeExpr.evaluate());
    }
    
    @Test
    public void OneLevelPlusReferenceCellExpression() 
                                            throws OperatorNotSuportedException{
        compositeExpr = new CompositeExpression(expr2, expr7, "+");
        assertEquals(new SomeValue(30.0), compositeExpr.evaluate());
    }
    
    @Test
    public void OneLevelProductReferenceCellExpression() 
                                            throws OperatorNotSuportedException{
        compositeExpr = new CompositeExpression(expr2, expr7, "*");
        assertEquals(new SomeValue(200.0), compositeExpr.evaluate());
    }    
    
    
    @Test
    public void TwoLevelTwoPlusAndOneProductTwoExpression() 
                                            throws OperatorNotSuportedException{
        expr5 = new CompositeExpression(expr1, expr2, "+");
        expr6 = new CompositeExpression(expr3, expr4, "+");
        compositeExpr = new CompositeExpression(expr5, expr6, "*");
        assertEquals(new SomeValue(2100.0), compositeExpr.evaluate());
    }
        
    @Test
    public void TwoLevelPlusTwoExpression() throws OperatorNotSuportedException{
        expr5 = new CompositeExpression(expr1, expr2, "+");
        expr6 = new CompositeExpression(expr3, expr4, "+");
        compositeExpr = new CompositeExpression(expr5, expr6, "+");
        assertEquals(new SomeValue(100.0), compositeExpr.evaluate());
    }  
    
    @Test
    public void TwoLevelProductTwoExpression() 
                                            throws OperatorNotSuportedException{
        expr5 = new CompositeExpression(expr1, expr2, "*");
        expr6 = new CompositeExpression(expr3, expr4, "*");
        compositeExpr = new CompositeExpression(expr5, expr6, "*");
        assertEquals(new SomeValue(240000.0), compositeExpr.evaluate());
    }
    
    
    @Test
    public void OneLevelProductTwoExpression() throws OperatorNotSuportedException{
        compositeExpr =  new CompositeExpression(expr1, expr2, "*");
        assertEquals(new SomeValue(200.0), compositeExpr.evaluate());
    }    
    
    @Test
    public void OneLevelPlusTwoExpressionPlus() throws OperatorNotSuportedException{
        compositeExpr =  new CompositeExpression(expr1, expr2, "+");
        assertEquals(new SomeValue(30.0), compositeExpr.evaluate());
    }
        
}
