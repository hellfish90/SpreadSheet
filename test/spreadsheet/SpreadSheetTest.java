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
public class SpreadSheetTest {
    
    Expression expr1;
    Expression expr2;

    
    @Before
    public void setUpSheet() throws OperatorNotSuportedException{
        put("a3", mult("a1", "a2"));
    }
    
    @Test
    public void
    cell_has_no_value_if_depends_on_empty_cells(){
        assertFalse(get("a3").hasValue());
    }
    
    @Test
    public void getValueOnceCell() throws OperatorNotSuportedException{
        expr1 = new SomeValue(20.0);
        expr2 = new SomeValue(20.0);
        put("a1", new CompositeExpression(expr1, expr2, "+"));
        assertEquals(new SomeValue(40.0), get("a1"));
    }
    
    @Test
    public void recalculation_works() throws OperatorNotSuportedException{
        put("a1", 42.0);
        put("a2", 20.0);
        assertEquals(new SomeValue(840.0), get("a3"));
    }
    
    @After
    public void clearSheet(){
        clear();
    }
    
}
