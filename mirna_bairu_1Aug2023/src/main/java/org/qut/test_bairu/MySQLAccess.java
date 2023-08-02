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
public class MySQLAccess {
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) {
        MySQLAccess mySQLAccess = new MySQLAccess();
        String tpmData = mySQLAccess.getTPMData("NbL00g00010.1");
        String gff3Data = mySQLAccess.getGFF3Data("nbL00g00050.1");
        String labData = mySQLAccess.getLabData("1");
        String qldData = mySQLAccess.getQldData("1");
        System.out.println("TPM Data: " + tpmData);
        System.out.println("GFF3 Data: " + gff3Data);
        System.out.println("LAB Data: " + labData);
        System.out.println("QLD Data: " + qldData);
        mySQLAccess.close();
    }

    public MySQLAccess() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://resmysql02.qut.edu.au/benth_2023", "an_rw", "P.waterh0use!!");
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTPMData(String geneName) {
        String tpmData = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM tpm WHERE read_tissue_genome = 'NbLab360' AND reference_mRNA_name = '" + geneName + "'");
            while (rs.next()) {
                float tpm = rs.getFloat("TPM");
                String readTissueName = rs.getString("read_tissue_name");
                float numReads = rs.getFloat("NumReads");
                tpmData += readTissueName + "," + tpm + "," + numReads + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tpmData;
    }

    public String getGFF3Data(String geneName) {
        String gff3Data = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM gff3 WHERE attributes LIKE '%" + geneName + "%'");
            while (rs.next()) {
                String attributes = rs.getString("attributes");
                String[] splitAttributes = attributes.split(";");
                boolean noNote = true;
                for (String attr : splitAttributes) {
                    String[] temp = attr.split("=");
                    if (temp[0].equals("ID")) {
                        gff3Data += temp[1] + "=";
                    } else if (temp[0].equals("Note")) {
                        gff3Data += temp[1] + ";";
                        noNote = false;
                    }
                }
                if (noNote) {
                    gff3Data += "NA;";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gff3Data;
    }
    
    //"SELECT * FROM lab360_mirna WHERE id = '" + geneName + "'"
    public String getLabData(String geneName) {
        String labData = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM lab360_mirna WHERE id = '" + geneName + "'");
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                labData += sequence + "," + precursor_seq + "," + structure + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labData;
    }
    
    //"SELECT * FROM qld183_mirna_updated WHERE id = '" + geneName + "'"
    public String getQldData(String geneName) {
        String qldData = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM qld183_mirna_updated WHERE id = '" + geneName + "'");
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                qldData += sequence + "," + precursor_seq + "," + structure + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qldData;
    }

    public void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
