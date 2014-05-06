/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import static spreadsheet.SpreadSheet.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import spreadsheet.exceptions.OperatorNotSuportedException;

/**
 *
 * @author msf7
 */
public class ComplexSpreadSheetTest {
    
    public ComplexSpreadSheetTest() {
    }
    
    @Before
    public void setUp() throws OperatorNotSuportedException {
        put("c1", mult("a1", "b1"));
        put("c2", mult("a2", "b2"));
        put("c3", sum("c1", "c2"));
        
        put("a1", 10.0);put("b1", 20.0);
        put("a2", 30.0);put("b2", 40.0);
        
    }

    @Test
    public void chained_expressions(){
        assertEquals(new SomeValue(1400.0), get("c3"));

    }
    
    @Test
    public void chained_propagations() throws OperatorNotSuportedException{
        put("a1", "b1");
        assertEquals(new SomeValue(1600.0), get("c3"));
    }
    
    @After
    public void tearDown() {
        clear();
    }

}
