package org.qcmg.hairpin.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LAB360 {
	
	@Id	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//camel case the column name for findby method in repository
	//ok: "La360 findByPureNumber(Integer pureNumber);"  
	//error:  "La360 findByPure_number(Integer pure_number);"  
	@Column(name="pure_number")
	private Integer pureNumber;
	
	private String sequence;
	
	private String precursor_seq;
	
	private String structure;
	
	private String chr;
	
	private String strand;
	
	private Integer start;
	
	public LAB360() {} //this default constructor is compulsory, otherwise can't autoWire
	public LAB360(int id, Integer pure_number, String sequence, String precursor_seq, String structure, String chr,
			String strand, Integer start) {
		super();
		this.id = id;
		this.pureNumber = pure_number;
		this.sequence = sequence;
		this.precursor_seq = precursor_seq;
		this.structure = structure;
		this.chr = chr;
		this.strand = strand;
		this.start = start;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPureNumber() {
		return pureNumber;
	}

	public String getSequence() {
		return sequence;
	}

	public String getPrecursor_seq() {
		return precursor_seq;
	}

	public String getStructure() {
		return structure;
	}

	public String getChr() {
		return chr;
	}

	public String getStrand() {
		return strand;
	}

	public int getStart() {
		return start;
	}

	@Override
	public String toString() {
		
		return sequence + "," + precursor_seq + "," + structure + "," + chr + "," + strand + "," + start;
	}	
	
	




	
	
	
	/*
	 * 
	 *     //"SELECT * FROM TEST360_mirna WHERE id = '" + geneName + "'"
    public String getLabData(String geneName) {
        String labData = "";
        try {
            String query1 = "SELECT * FROM TEST360_mirna WHERE id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(query1);
            pstmt1.setString(1, geneName);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                String chr = rs.getString("chr");
                String strand = rs.getString("strand");
                String start = rs.getString("start");
                labData += sequence + "," + precursor_seq + "," + structure
                        + "," + chr + "," + strand + "," + start + " ";
                        
                        
      public String getLabDataPureNum(String geneName) {
        String labData = "";
        try {
            String query2 = "SELECT * FROM TEST360_mirna WHERE pure_number = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, geneName);
            ResultSet rs = pstmt2.executeQuery();
            while (rs.next()) {
                String sequence = rs.getString("sequence");
                String precursor_seq = rs.getString("precursor_seq");
                String structure = rs.getString("structure");
                String chr = rs.getString("chr");
                String strand = rs.getString("strand");
                String start = rs.getString("start");
                labData += sequence + "," + precursor_seq + "," + structure
                        + "," + chr + "," + strand + "," + start + " ";
            }                      
  
	 */
	
	
	
	
	
	

}
