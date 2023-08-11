package org.qcmg.hairpin.helloworld;

import org.qcmg.hairpin.demo.HairPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

@Controller
public class CircleController {
	@Autowired
	private  HairPinService hairPinService;

    @GetMapping("/circle")
    public String drawCircle(Model model) {
    	
		BufferedImage image = createDiagram();
		String encodedImage = encodeToBase64String(image, "png");

    	
        // Pass the byte array to the model
        model.addAttribute("circleImage", encodedImage);

//        //debug
//		String rawdata = hairPinService.getLabData( "1");
//		
//	       String[] splitbyspace = rawdata.split(" ");
//
//	        for (int genome = 0; genome < splitbyspace.length; genome++) {
//
//	            String[] instances = splitbyspace[genome].split(",");
//
//	            String red_sequence = instances[0].replaceAll("\\(.*?\\)", "").toUpperCase().replaceAll("U", "T");
//	   		 model.addAttribute("red_sequence", red_sequence);
//	   		 model.addAttribute("instances0", instances[0]);
//	   		model.addAttribute("splitbyspace0", splitbyspace[0]);
//	   		
//	   		
//	   		
//	   		
//            String sequence = instances[1].toUpperCase();
//            String symbols = instances[2].replaceAll("\\s", "");
//	   		 model.addAttribute("sequence", sequence);
//	   		model.addAttribute("symbols", symbols);
//
//	        }
//		
//		 model.addAttribute("rawdata", rawdata);
		 
		
        return "cycle2"; // Return the name of the JSP file
    }		 
		 
	@GetMapping(path = "/basicauth")
	public String basicAuthCheck() {
		return "Success"; 
	}	 
		 
    	// to ./src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    	@RequestMapping("say-hello-jsp")
    	//@ResponseBody  //return jsp but not string to browser
    	public String sayHelloJsp() {
    		return "sayHello";
    	}
    	
    	@RequestMapping("gene-jsp")
    	public String genJsp() {
    		return "gene";
    	}
    	

    	@RequestMapping("say-hello-html")
    	@ResponseBody  //return string to browser
    	public String sayHelloHtml() {
    		StringBuffer sb = new StringBuffer();
    		sb.append("<html>");
    		sb.append("<head>");
    		sb.append("<title> My First HTML Page - Changed</title>");
    		sb.append("</head>");
    		sb.append("<body>");
    		sb.append("My first html page with body - Changed");
    		sb.append("</body>");
    		sb.append("</html>");
    		
    		return sb.toString();
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
   
    
	public BufferedImage createDiagram() {
	       // Create a BufferedImage to draw the circle
        int width = 100;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Get the Graphics2D object to draw
        Graphics2D g2d = image.createGraphics();

        // Draw a small circle on the BufferedImage
        g2d.setColor(Color.RED);
        g2d.fillOval(25, 25, 50, 50);
        // Clean up the Graphics2D object
        g2d.dispose();

		return image;
		
	} 
    
    
}