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
public class createimage {
    Connection conn = null;
    public static void main(String[] args) {
        String fdsf = (new myslqaccess()).accesssql("NbL00g00050.1");
        System.out.println(fdsf);
        
    }

    
   
    
    public String accesssql(String gene_name) {
           
           String returned_string = "";

        try {

            // Connect to MySQL using the DriverManager class

//            conn = DriverManager.getConnection("jdbc:mysql://resmysql02.qut.edu.au/benth_2023", "an_rw", "P.waterh0use!!");

           

            // Execute a query using the Connection and Statement objects

            Statement stmt = conn.createStatement();

            //ResultSet rs = stmt.executeQuery("SELECT * FROM tpm WHERE reference_mRNA_name = \"NbL00g00050.1\"");
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM tpm WHERE read_tissue_genome = \"NbLab360\" and reference_mRNA_name = \"" + gene_name + "\"");

            

            // Process the results using the ResultSet object
            
            

            while (rs.next()) {

                System.out.println(rs.getFloat("TPM") + ", " + rs.getString("read_tissue_name"));
                returned_string = returned_string + rs.getString("read_tissue_name") 
                        + "," + String.valueOf(rs.getFloat("TPM")) + "," + String.valueOf(rs.getFloat("NumReads"))+ " ";
                
            }
            
           
            

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                // Close the connection

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
        System.out.println(returned_string);
        return returned_string;
    }
    
}
