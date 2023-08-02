/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.qut.test_bairu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bairu
 */
public class myslqaccess {
    Connection conn = null;
    Statement stmt = null;

    public static void main(String[] args) {
        String fdsf = (new myslqaccess()).accesssql("NbL00g00010.1");
        System.out.println(fdsf);
    }

    public myslqaccess() {
        // Call the init method to initialize the connection and statement
        init();
    }

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://resmysql02.qut.edu.au/benth_2023", "an_rw", "P.waterh0use!!");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(myslqaccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(myslqaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String accesssql(String gene_name) {
        String returned_string = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM tpm WHERE read_tissue_genome = 'NbLab360' and reference_mRNA_name = '" + gene_name + "'");
            while (rs.next()) {
                System.out.println(rs.getFloat("TPM") + ", " + rs.getString("read_tissue_name"));
                returned_string = returned_string + rs.getString("read_tissue_name")
                        + "," + String.valueOf(rs.getFloat("TPM")) + "," + String.valueOf(rs.getFloat("NumReads")) + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(returned_string);
        if (returned_string.isEmpty()) {
            System.out.println("returned string is empty");
        }
        return returned_string;
    }
}
