/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet.window;

import javax.swing.JFrame;
import javax.swing.JTable;


/**
 *
 * @author msf7
 */
public class SpreadSheetWindow{
    
    JFrame frame;
    JTable table;

    public SpreadSheetWindow(){
        frame = new JFrame();
        table = new JTable(new SpreadSheetTableModel());
        frame.add(table,0);
        frame.setTitle("SpreadSheet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLocationRelativeTo(frame.getRootPane());
        frame.pack();
        frame.setVisible(true); 
    }

    public static void main(String[] args){
        SpreadSheetWindow spreadSheetWindow;
        spreadSheetWindow = new SpreadSheetWindow();
    }
}
