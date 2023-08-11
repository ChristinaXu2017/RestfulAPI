package org.qcmg.hairpin.demo;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HairPinService {
	
	@Autowired
	private Lab360Repository labRepo;
	
	static { }
		
	public HairPinService() {}
	
	
    // Set the color for drawing the text
    Color blackColor = Color.BLACK;
    Color redColor = Color.RED;
    
    
    public List<LAB360> getLabs(String geneId) {
    	
    	List<LAB360> labs = new ArrayList<>();
    	//query where pure_number_number = ?
    	if(geneId.startsWith("mir")) {  
    		int pure_number = -1;
    		try {
	    		pure_number = Integer.parseInt(geneId.substring(3));
	    	}catch(NumberFormatException e) {
	    		//now pure_number_number = -1  		
	    	}
    		labs = labRepo.findByPureNumber(pure_number);    		
    	} else {
    		int id = -1;
    		try {
	    		id = Integer.parseInt(geneId);
	    	}catch(NumberFormatException e) {
	    		//now pure_number_number = -1  		
	    	}
    		LAB360 lab = labRepo.findById(id).orElse(null); 
    		if(lab != null) labs.add(lab);  		
    	}
    	
    	return labs;   	
    }


    //"SELECT * FROM TEST360_mirna WHERE id = '" + geneName + "'"
    public String getLabData(String geneId) {
    	
    	List<LAB360> labs = new ArrayList<LAB360>();
    	//query where pure_number_number = ?
    	if(geneId.startsWith("mir")) {  
    		int pure_number = -1;
    		try {
	    		pure_number = Integer.parseInt(geneId.substring(3));
	    	}catch(NumberFormatException e) {
	    		//now pure_number_number = -1  		
	    	}
    		labs = labRepo.findByPureNumber(pure_number);    		
    	} else {
    		int id = -1;
    		try {
	    		id = Integer.parseInt(geneId);
	    	}catch(NumberFormatException e) {
	    		//now pure_number_number = -1  		
	    	}
    		LAB360 lab = labRepo.findById(id).orElse(null); 
    		if(lab != null) labs.add(lab);  		
    	}
    	
    	String labData = "";
    	for(LAB360 lab : labs) {
    		labData += lab.toString() + " ";
    	}
    	    	
        return labData;
    }

		
	private void draw_symbols2(Graphics2D g2d,String sequence, String symbols, int startIndex, int endIndex, int genome, int i, int j, int column_no) {
        while (i < j) {
            //for (int column_no = 0; column_no < width; column_no++) {
            char symbol_i = symbols.charAt(i);
            char symbol_j = symbols.charAt(j);
            char sequence_i = sequence.charAt(i);
            char sequence_j = sequence.charAt(j);

            if (symbol_i == '.') {
                //draw sequence.charAt(i) in row 1 column column_no
                if ((i >= startIndex) & (i < endIndex)) {
                    g2d.setColor(redColor);
                } else {
                    g2d.setColor(blackColor);
                }
                g2d.drawString(String.valueOf(sequence_i), 10 * column_no, genome * 165 + 1 * 15);
                i = i + 1;
                if (symbol_j == '.') {
                    //draw sequence.charAt(j) in row 5 column column_no
                    if ((j >= startIndex) & (j < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence_j), 10 * column_no, genome * 165 + 5 * 15);
                    j = j - 1;
                }
            } else if ((symbol_i == '(') & (symbol_j == ')')) {
                //draw sequence.charAt(i) in row 2 column column_no
                if ((i >= startIndex) & (i < endIndex)) {
                    g2d.setColor(redColor);
                } else {
                    g2d.setColor(blackColor);
                }
                g2d.drawString(String.valueOf(sequence_i), 10 * column_no, genome * 165 + 2 * 15);
                //draw sequence.charAt(j) in row 4 column column_no
                if ((j >= startIndex) & (j < endIndex)) {
                    g2d.setColor(redColor);
                } else {
                    g2d.setColor(blackColor);
                }
                g2d.drawString(String.valueOf(sequence_j), 10 * column_no, genome * 165 + 4 * 15);
                //draw | in row 3 column column_no
                g2d.setColor(blackColor);
                g2d.drawString(String.valueOf('|'), 10 * column_no, genome * 165 + 3 * 15);
                i = i + 1;
                j = j - 1;

            } else if ((symbol_i == '(') & (symbol_j == '.')) {
                //draw sequence.charAt(j) in row 5 column column_no
                if ((j >= startIndex) & (j < endIndex)) {
                    g2d.setColor(redColor);
                } else {
                    g2d.setColor(blackColor);
                }
                g2d.drawString(String.valueOf(sequence_j), 10 * column_no, genome * 165 + 5 * 15);
                j = j - 1;
            }

            // Draw the symbol
            //g2d.drawString(String.valueOf(sequence.charAt(i)), i, row);
            column_no = column_no + 1;
        }	
 		
	}
	
	private void draw_symbols(Graphics2D g2d,String sequence, String symbols, int startIndex, int endIndex, int genome) {
        int i = 0;
        int j = symbols.length() - 1;
        int column_no = 0;
        //g2d.setColor(blackColor);

        char whileholder_i = symbols.charAt(i);
        char whileholder_j = symbols.charAt(j);

        if ((whileholder_i == '.') & (whileholder_j == '.')) {
            int difference = (symbols.length() - symbols.lastIndexOf(')') - 1) - symbols.indexOf('(');
            if (difference < 0) {
                difference = difference * (-1);
                while (column_no < difference) {
                    if ((i >= startIndex) & (i < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence.charAt(i)), 10 * column_no, genome * 165 + 1 * 15);
                    i = i + 1;
                    column_no = column_no + 1;
                }
            } else if (difference > 0) {
                while (column_no < difference) {
                    if ((j >= startIndex) & (j < endIndex)) {
                        g2d.setColor(redColor);
                    } else {
                        g2d.setColor(blackColor);
                    }
                    g2d.drawString(String.valueOf(sequence.charAt(j)), 10 * column_no, genome * 165 + 5 * 15);
                    j = j - 1;
                    column_no = column_no + 1;
                }
            }
        }
        //continue draw
       	draw_symbols2(g2d,  sequence,  symbols,  startIndex,  endIndex,  genome,  i,  j,  column_no);

	
	}
	
    public BufferedImage createDiagram(String input) {

        String[] splitbyspace = input.split(" ");

        int width = 2000;
        int height = splitbyspace.length * (150 + 15);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        for (int genome = 0; genome < splitbyspace.length; genome++) {

            String[] instances = splitbyspace[genome].split(",");
            String red_sequence = instances[0].replaceAll("\\(.*?\\)", "").toUpperCase().replaceAll("U", "T");

            String sequence = instances[1].toUpperCase();
            String symbols = instances[2].replaceAll("\\s", "");
            
            if (sequence.length() > width) {
                width = sequence.length();
            }

            String chr = instances[3];
            String strand = instances[4];
            int start = Integer.parseInt(instances[5]);
            int end = start + sequence.length();          
            // Set the font for drawing the symbols
            Font font = new Font("Arial", Font.PLAIN, 12);
            g2d.setFont(font);

            // Set the color for drawing the text
//            Color blackColor = Color.BLACK;
//            Color redColor = Color.RED;

            g2d.setColor(blackColor);

            int width_counter = 0;
            g2d.drawString("chr: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("chr: ");
            g2d.drawString(chr, width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth(chr) + 20;
            g2d.drawString("Strand: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("Strand: ");
            g2d.drawString(strand, width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth(strand) + 20;
            g2d.drawString("Start: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("Start: ");
            g2d.drawString(Integer.toString(start), width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth(Integer.toString(start)) + 20;
            g2d.drawString("End: ", width_counter, genome * 165 + 9 * 15);
            width_counter += g2d.getFontMetrics().stringWidth("End: ");
            g2d.drawString(Integer.toString(end), width_counter, genome * 165 + 9 * 15);

    //Find the starting and ending indices of the shared section
            int startIndex = sequence.indexOf(red_sequence);
            int endIndex = startIndex + red_sequence.length();           

            if (startIndex > -1) {
// Draw the black portion before the shared section
                g2d.setColor(blackColor);
                String beforeSharedSection = sequence.substring(0, startIndex);
                g2d.drawString(beforeSharedSection, 0, genome * 165 + 7 * 15);

// Draw the red shared section
                g2d.setColor(redColor);
                String sharedSection = sequence.substring(startIndex, endIndex);
                g2d.drawString(sharedSection, g2d.getFontMetrics().stringWidth(beforeSharedSection), genome * 165 + 7 * 15);

// Draw the black portion after the shared section
                g2d.setColor(blackColor);
                String afterSharedSection = sequence.substring(endIndex);
                g2d.drawString(afterSharedSection, g2d.getFontMetrics().stringWidth(beforeSharedSection)
                        + g2d.getFontMetrics().stringWidth(sharedSection), genome * 165 + 7 * 15);
            } else {
                g2d.setColor(blackColor);
                g2d.drawString(sequence, 0, genome * 165 + 7 * 15);
            }

// Draw the symbols
            draw_symbols(g2d,  sequence,  symbols,  startIndex,  endIndex,  genome);

          }

        g2d.dispose();

        return image;
    }

    public String encodeToBase64String(BufferedImage image, String type) {
        String encodedString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            encodedString = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }     
}