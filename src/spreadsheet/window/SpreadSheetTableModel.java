/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet.window;

import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import spreadsheet.Cell;
import spreadsheet.SomeValue;
import static spreadsheet.SpreadSheet.*;
import spreadsheet.exceptions.OperatorNotSuportedException;

/**
 *
 * @author msf7
 */
public class SpreadSheetTableModel extends AbstractTableModel{
     
    private final String[] columnNames = new String[] {
            "a", "b", "c", "d","e"
    };
    private final  HashMap<String,Cell> columnClass;
 
    public SpreadSheetTableModel()
    {
        this.columnClass = getAllCells();
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return columnNames.length;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return get(columnNames[columnIndex]+rowIndex).getValue();
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        String value = (String) aValue;
        StringTokenizer token = new StringTokenizer(value,"*+/-%",true);
        try {
            if (!isReference(value) && token.countTokens()==1 ) {
                put(columnNames[columnIndex]+rowIndex, Float.valueOf(value));
            }else if(isReference(value) && token.countTokens()==1){
                put(columnNames[columnIndex]+rowIndex, value);
            }else{
                spreadsheet.Expression comExpr= generateCompositeExpressions(token);
                put(columnNames[columnIndex]+rowIndex, comExpr);
            }
        } catch (NumberFormatException | OperatorNotSuportedException e) {
            showMessageError(e);
        }

        fireTableDataChanged();
    }

    private spreadsheet.Expression generateCompositeExpressions(StringTokenizer token) throws OperatorNotSuportedException {
        
        String tk = token.nextToken();
        if (token.hasMoreTokens()) { 
            String operant = token.nextToken();
            switch (operant) {
                case "+":
                    if(isReference(tk))return sum(tk,generateCompositeExpressions(token));
                    else return sum(Float.valueOf(tk),generateCompositeExpressions(token));
                case "*":
                    if(isReference(tk))return mult(tk,generateCompositeExpressions(token));
                    else return mult(Float.valueOf(tk),generateCompositeExpressions(token));
                default:
                    throw new OperatorNotSuportedException();
            }
   
            }else{
                if(isReference(tk))return sum(tk,0);
                else return new SomeValue(Float.valueOf(tk));
           } 
    }
    
    private boolean isReference(String value){
        char[]array =value.toCharArray();
        for (int i = 0; i < value.length(); i++) {
            if (Character.isAlphabetic(array[i])) {
                return true;
            }
        }
        return false;
    }

    private void showMessageError(java.lang.Exception e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
    
}
