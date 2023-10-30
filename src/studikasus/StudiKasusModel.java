/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studikasus;

/**
 *
 * @author ACER-PC
 */
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

class StudiKasusModel extends AbstractTableModel{
    //deklarasi array columnNames dengan tipe string
    private String[] columnNames = {"Nama", "Jenis Kelamin", "Nomor HP", "Alamat"};
    //deklarasi array list dengan tipe string
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
    
    //deklarasi getter jumlah kolom
    public int getColumnCount(){
        return columnNames.length;
    }
    
    //deklarasi getter jumlah baris
    public int getRowCount(){
        return data.size();
    }
    
    //deklarasi getter nama kolom
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    //deklarasi getter nilai variable
    public Object getValueAt(int row, int col){
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }
    
    //deklarasi kondisi nilai variable
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    //deklarasi penyimpanan nilai variable yang diambil getter
    public void add(ArrayList<String> value)
    {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
    
    public void removeRow(int row)
    {
        data.remove(row);
        fireTableRowsDeleted(data.size() - 1, data.size() -1);
    }
}
