/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import spreadsheet.exceptions.OperatorNotSuportedException;


/**
 *
 * @author msf7
 */
public class CellTest {
    
    Cell cell, cell2, cell3;
    Expression expr1, expr2, expr3, expr4, expr5, expr6;
    Expression referenceExpr,referenceExpr2;
    Expression exprNoValue;
    Expression compositeExpr;
    
    public CellTest() {
        
    }

    
    @Before
    public void setUp() throws OperatorNotSuportedException {

        cell = new Cell("a1", new NoValue());
        cell2 = new Cell("a2", new NoValue());
        cell3 = new Cell("a3", new NoValue());
        expr1 = new SomeValue(10.0);
        expr2 = new SomeValue(20.0);
        expr3 = new SomeValue(30.0);
        expr4 = new SomeValue(40.0);
        referenceExpr = new ReferenceCellExpression(cell);
        referenceExpr2 = new ReferenceCellExpression(cell3);
        exprNoValue = new NoValue();
        compositeExpr = new CompositeExpression(referenceExpr2, expr4, "+");
 
    }
    
    @Test
    public void testOneReferenceObserver() throws OperatorNotSuportedException {
        cell2.setExpr(referenceExpr);
        cell.setExpr(expr1);
        assertEquals(1, cell.countObservers());
    }
    
    
    @After
    public void tearDown() throws OperatorNotSuportedException {
        cell = new Cell("a1", new NoValue());
        cell2 = new Cell("a2", new NoValue());
        cell3 = new Cell("a3", new NoValue());
        expr1 = new SomeValue(10.0);
        expr2 = new SomeValue(20.0);
        expr3 = new SomeValue(30.0);
        expr4 = new SomeValue(40.0);
        referenceExpr = new ReferenceCellExpression(cell);
        exprNoValue = new NoValue();
        compositeExpr = new CompositeExpression(expr3, expr4, "+");
    }



    
}
