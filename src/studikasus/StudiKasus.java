/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studikasus;

/**
 *
 * @author ACER-PC
 */

import com.sun.java.accessibility.util.SwingEventMonitor;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class StudiKasus extends JFrame {
    //deklarasi variable checkBoxSelected
//    private boolean checkBoxSelected;
    
    public StudiKasus(){
        //deklarasi pengisian variable checkBoxSelected dengan nilai false
//        this.checkBoxSelected = false;
        //deklarasi fungsi exit ketika di close
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //deklarasi header label
        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);
        
        //deklarasi controlPanel sebagai JPanel dan layout
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        //deklarasi dan penyuntingan ukuran JPanel, deklarasi layout grid
        JPanel panel = new JPanel();
        panel.setSize(400, 400);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        
        //deklarasi label nama
        JLabel labelNama = new JLabel("Nama: ");
        JTextField textFieldNama = new JTextField();
        
        //deklarasi label radio
        JLabel labelRadio = new JLabel("Jenis Kelamin: ");
        //deklarasi radio button
        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        
        //deklarasi grup radio button
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        
        //deklarasi label nomor hp
        JLabel labelNomorHP = new JLabel("Nomor HP: ");
        JTextField textFieldNomorHP = new JTextField();
        
        //deklarasi check box
        JLabel labelAlamat = new JLabel("Alamat: ");
        JTextArea textAreaAlamat = new JTextArea();
        
        //deklarasi button
        JButton button = new JButton("Simpan");
        
        JButton buttonDelete = new JButton("Hapus");
        
        JButton buttonUbah = new JButton("Ubah");
        
        //deklarasi table
        javax.swing.JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        
        //deklarasi pemanggilan Latihan7Model
        StudiKasusModel tableModel = new StudiKasusModel();
        table.setModel(tableModel);
        
        //deklarasi text area output
//        JTextArea txtOutput = new JTextArea("");
        
//        checkBox.addItemListener(new ItemListener(){
//           public void itemStateChanged(ItemEvent e){
//               //deklarasi state variable checkBoxSelected
//               checkBoxSelected = e.getStateChange()==1;
//           } 
//        });
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  int confirmation1 = JOptionPane.showConfirmDialog(StudiKasus.this,
                        "Apakah yakin anda ingin menambahkan data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    //pengkondisian JOptionPane menggunakan variable confirmation
                    if(confirmation1 == JOptionPane.YES_OPTION)
                    {
                        //deklarasi variable jenis kelamin
                        String jenisKelamin = "";
                        //pengkondisian radio button
                        if(radioButton1.isSelected()){
                            jenisKelamin = radioButton1.getText();
                        }
                        if(radioButton2.isSelected()){
                            jenisKelamin = radioButton2.getText();
                        }

                        //deklarasi variable nama
                        String nama = textFieldNama.getText();
                        //deklarasi variable nomorHP
                        String nomorHP = textFieldNomorHP.getText();
                        //deklarasi variable alamat
                        String alamat = textAreaAlamat.getText();
                        
                        if("".equals(nama)){
                            JOptionPane.showMessageDialog(StudiKasus.this, "Nama belum diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if("".equals(nomorHP)){
                            JOptionPane.showMessageDialog(StudiKasus.this, "Nomor HP belum diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if("".equals(alamat)){
                            JOptionPane.showMessageDialog(StudiKasus.this, "Alamat belum diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            tableModel.add(new ArrayList<>(Arrays.asList(nama, jenisKelamin, nomorHP, alamat)));
                            try {
                                FileWriter Writer = new FileWriter("Biodata.txt", true);
                                Writer.write(nama + " " + jenisKelamin + " " + nomorHP + " " + alamat);
                                Writer.write(System.getProperty("line.separator"));
                                Writer.close();
                                JOptionPane.showMessageDialog(StudiKasus.this, "Berhasil Menyimpan Data", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            catch(Exception e2){
                                JOptionPane.showMessageDialog(StudiKasus.this, "Gagal Menyimpan Data", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                        
            }
        });
        
        buttonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               StudiKasusModel tableModel = (StudiKasusModel) table.getModel();
                if(table.getSelectedRowCount() == 1){
                    tableModel.removeRow(table.getSelectedRow());
                    JOptionPane.showMessageDialog(StudiKasus.this, "Berhasil Dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(table.getRowCount() == 0){
                        JOptionPane.showMessageDialog(StudiKasus.this, "Tabel Kosong.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(StudiKasus.this, "Pilih satu baris dari tabel untuk dihapus.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        
        
        buttonUbah.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                StudiKasusModel tableModel = (StudiKasusModel) table.getModel();
                if(table.getSelectedRowCount() == 1){
                        String nama = textFieldNama.getText();
                        String jenisKelamin = "";
                        if(radioButton1.isSelected()){
                            jenisKelamin = radioButton1.getText();
                        }
                        if(radioButton2.isSelected()){
                            jenisKelamin = radioButton2.getText();
                        }
                        String nomorHP = textFieldNomorHP.getText();
                        String alamat = textAreaAlamat.getText();
                        
                        tableModel.setValueAt(nama, table.getSelectedRow(), 0);
                        tableModel.setValueAt(jenisKelamin, table.getSelectedRow(), 1);
                        tableModel.setValueAt(nomorHP, table.getSelectedRow(), 2);
                        tableModel.setValueAt(alamat, table.getSelectedRow(), 3);
                        
                        try {
                                FileWriter Writer = new FileWriter("Biodata.txt", true);
                                Writer.write(nama + " " + jenisKelamin + " " + nomorHP + " " + alamat);
                                Writer.write(System.getProperty("line.separator"));
                                Writer.close();
                                JOptionPane.showMessageDialog(StudiKasus.this, "Berhasil Diubah", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            catch(Exception e3){
                                JOptionPane.showMessageDialog(StudiKasus.this, "Gagal Diubah", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                } else {
                    if(table.getRowCount() == 0){
                        JOptionPane.showMessageDialog(StudiKasus.this, "Tabel Kosong.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(StudiKasus.this, "Pilih satu baris dari tabel untuk diubah.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent e){
               int confirmation2 = JOptionPane.showConfirmDialog(null, "Apakah anda yaking ingin keluar dari program?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
               if(confirmation2 == JOptionPane.YES_OPTION){
                   System.exit(0);
               }
           } 
        });
        
        //deklarasi penyuntingan posisi dan ukuran grid untuk setiap komponen
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(headerLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelNama, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(textFieldNama, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(labelNomorHP, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(textFieldNomorHP, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelRadio, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(radioButton1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(radioButton2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(labelAlamat, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(textAreaAlamat, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(button, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(buttonDelete, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(buttonUbah, gbc);
        
        gbc.ipady = 80;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        panel.add(scrollableTable, gbc);
        
        
        
        //deklarasi penambahan controlPanel
        controlPanel.add(panel);
        
        //deklarasi penyuntingan ukuran grid
        this.setLayout(new GridLayout(1, 1));
        //deklarasi pemanggilan komponen
//        this.add(headerLabel);
        this.add(controlPanel);
        //deklarasi penyuntingan ukuran app
        this.setSize(500, 750);
    }
    
    public static void main(String[] args) {
        //deklarasi menjalankan program
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                StudiKasus h = new StudiKasus();
                h.setVisible(true);
            }
        });
    }
    
}