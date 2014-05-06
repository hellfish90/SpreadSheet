/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import java.util.HashMap;
import spreadsheet.exceptions.OperatorNotSuportedException;


/**
 *
 * @author msf7
 */
public class SpreadSheet {

    private static int SIZE = 5;
    private static final Sheet SHEET = new Sheet(SIZE);

    /**
     *
     * @param size
     */
    public SpreadSheet(int size){
        SIZE= size;
    }

    /**
     * 
     * @param expr1
     * @param expr2
     * @return
     */
    public static Expression sum(Expression expr1, Expression expr2){
        return new CompositeExpression(expr1, expr2, "+");
    }
    
    /**
     *
     * @param expr1
     * @param value2
     * @return
     */
    public static Expression sum(Expression expr1, double value2){
        SomeValue sValue = new  SomeValue(value2);
        
        return new CompositeExpression(expr1, sValue, "+");
    }
    
    /**
     *
     * @param expr1
     * @param ref2
     * @return
     */
    public static Expression sum(Expression expr1, String ref2){
        ReferenceCellExpression rExpr2 = new ReferenceCellExpression(SHEET.getCell(ref2));
        return new CompositeExpression(expr1, rExpr2, "+");
    }
    
    /**
     *
     * @param value1
     * @param expr2
     * @return
     */
    public static Expression sum(double value1, Expression expr2){
        SomeValue sValue= new SomeValue(value1);
        return new CompositeExpression(sValue, expr2, "+");
    }
    
    /**
     *
     * @param value1
     * @param value2
     * @return
     */
    public static Expression sum(double value1, double value2){
        SomeValue sValue1= new SomeValue(value1);
        SomeValue sValue2= new SomeValue(value2);
        return new CompositeExpression(sValue1, sValue2, "+");
    }
    
    /**
     *
     * @param value1
     * @param ref2
     * @return
     */
    public static Expression sum(double value1, String ref2){
        Cell cell1 = SHEET.getCell(ref2);
        SomeValue sValue1= new SomeValue(value1);
        ReferenceCellExpression refexp2 = new ReferenceCellExpression(cell1);
        return sum(sValue1, refexp2);
    }
    
    /**
     *
     * @param ref1
     * @param expr2
     * @return
     */
    public static Expression sum(String ref1, Expression expr2){
        Cell cell1 = SHEET.getCell(ref1);
        ReferenceCellExpression refexp1 = new ReferenceCellExpression(cell1);
        return sum(refexp1, expr2);
    }
    
    /**
     *
     * @param ref1
     * @param value2
     * @return
     */
    public static Expression sum(String ref1, double value2){
        Cell cell1 = SHEET.getCell(ref1);
        ReferenceCellExpression refexp1 = new ReferenceCellExpression(cell1);
        return sum(refexp1, value2);
    }
    
    /**
     *
     * @param ref1
     * @param ref2
     * @return
     */
    public static Expression sum(String ref1, String ref2){
        Cell cell1 = SHEET.getCell(ref1);
        Cell cell2 = SHEET.getCell(ref2);
        ReferenceCellExpression refexp1 = new ReferenceCellExpression(cell1);
        ReferenceCellExpression refexp2 = new ReferenceCellExpression(cell2);
        return sum(refexp1, refexp2);
    }
    
    /**
     *
     * @param expr1
     * @param expr2
     * @return
     */
    public static Expression mult(Expression expr1, Expression expr2){
        return new CompositeExpression(expr1, expr2, "*");
    }
    
    /**
     *
     * @param expr1
     * @param value2
     * @return
     */
    public static Expression mult(Expression expr1, double value2){
        SomeValue sValue = new  SomeValue(value2);
        return new CompositeExpression(expr1, sValue, "*");
    }
    
    /**
     *
     * @param expr1
     * @param ref2
     * @return
     */
    public static Expression mult(Expression expr1, String ref2){
        ReferenceCellExpression rExpr2 = new ReferenceCellExpression(SHEET.getCell(ref2));
        return new CompositeExpression(expr1, rExpr2, "*");
    }
    
    /**
     *
     * @param value1
     * @param expr2
     * @return
     */
    public static Expression mult(double value1, Expression expr2){
        SomeValue sValue= new SomeValue(value1);
        return new CompositeExpression(sValue, expr2, "*");
    }
    
    /**
     *
     * @param value1
     * @param value2
     * @return
     */
    public static Expression mult(double value1, double value2){
        SomeValue sValue1= new SomeValue(value1);
        SomeValue sValue2= new SomeValue(value2);
        return new CompositeExpression(sValue1, sValue2, "*");
    }
    
    /**
     *
     * @param value1
     * @param ref2
     * @return
     */
    public static Expression mult(double value1, String ref2){
        Cell cell1 = SHEET.getCell(ref2);
        SomeValue sValue1= new SomeValue(value1);
        ReferenceCellExpression refexp2 = new ReferenceCellExpression(cell1);
        return mult(sValue1, refexp2);
    }
    
    /**
     *
     * @param ref1
     * @param expr2
     * @return
     */
    public static Expression mult(String ref1, Expression expr2){
        Cell cell1 = SHEET.getCell(ref1);
        ReferenceCellExpression refexp1 = new ReferenceCellExpression(cell1);
        return mult(refexp1, expr2);
    }
    
    /**
     *
     * @param ref1
     * @param value2
     * @return
     */
    public static Expression mult(String ref1, double value2){
        Cell cell1 = SHEET.getCell(ref1);
        ReferenceCellExpression refexp1 = new ReferenceCellExpression(cell1);
        return mult(refexp1, value2);
    }
    
    /**
     *
     * @param ref1
     * @param ref2
     * @return
     */
    public static Expression mult(String ref1, String ref2){
        Cell cell1 = SHEET.getCell(ref1);
        Cell cell2 = SHEET.getCell(ref2);
        ReferenceCellExpression refexp1 = new ReferenceCellExpression(cell1);
        ReferenceCellExpression refexp2 = new ReferenceCellExpression(cell2);
        return mult(refexp1, refexp2);
    }

    /**
     *
     * @param name
     * @return
     */
    public static MaybeValue get(String name){
        return SHEET.get(name);
    }
    
    /**
     *
     * @return
     */
    public static HashMap<String,Cell> getAllCells(){
        return SHEET.getAllCells();
    }

    /**
     *
     * @param name
     * @param expr
     * @throws OperatorNotSuportedException
     */
    public static void put(String name, Expression expr) 
                                            throws OperatorNotSuportedException{
        SHEET.putExpressionInCell(name, expr);
    }
    
    /**
     *
     * @param name
     * @param value
     * @throws OperatorNotSuportedException
     */
    public static void put(String name, double value) throws OperatorNotSuportedException{
        SHEET.putExpressionInCell(name, new SomeValue(value));
    }
    
    /**
     *
     * @param name
     * @param refname
     * @throws OperatorNotSuportedException
     */
    public static void put(String name, String refname) throws OperatorNotSuportedException{
        Cell refCell = SHEET.getCell(refname);
        SHEET.getCell(name).setExpr(new ReferenceCellExpression(refCell));
    }

    /**
     *
     */
    public static void clear(){
        SHEET.initSheet();
    }

}
