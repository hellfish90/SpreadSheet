/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import spreadsheet.exceptions.OperatorNotSuportedException;

/**
 * Represents a Sheet and contains a Map of cells.
 * @author msf7
 */
public class Sheet {
    
    private static HashMap<String,Cell> CELLS;
    private final int SIZE;
    private static final ArrayList<String> matrix = new ArrayList(Arrays.asList(
                                                    "a","b","c","d","e","f"));
    
    /**
     *
     * @param SIZE
     */
    public Sheet(int SIZE){
        this.SIZE = SIZE;
        this.initSheet();
    }
   
    /**
     * Gets the value of a cell.
     * @param name
     * @return MaybeValue
     */
    public MaybeValue get(String name){ 
        return CELLS.get(name).getValue();  
    }
    
    /**
     * Put the expression in a cell.
     * @param name
     * @param expr
     * @throws OperatorNotSuportedException
     */
    public void putExpressionInCell(String name, Expression expr) 
                                            throws OperatorNotSuportedException{     
        CELLS.get(name).setExpr(expr);        
    }
    
    /**
     * Get all Cells
     * @return HashMap<String, Cell>
     */
    public HashMap<String, Cell> getAllCells(){
        return CELLS;
    }
    
    /**
     * Get a cell by name.
     * @param name
     * @return
     */
    public Cell getCell(String name){
        return CELLS.get(name);
    }
    
    /**
     * Init the Map of Cells.
     */
    public final void initSheet(){
        CELLS = new HashMap<>();     
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                addNewCell(x,y);
            }
        }
    }
    
    private void addNewCell(int x, int y){
        try {
            String key = matrix.get(x)+String.valueOf(y);
            Cell cell = new Cell(key,new NoValue());
            CELLS.put(key, cell);
        } catch (OperatorNotSuportedException ex) {
            Logger.getLogger(Sheet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
