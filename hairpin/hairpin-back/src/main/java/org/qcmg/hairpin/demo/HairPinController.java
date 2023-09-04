package org.qcmg.hairpin.demo;

import java.awt.image.BufferedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.ui.Model;

@Controller
public class HairPinController {
	
	@Autowired
	private  HairPinService hairPinService;
	
	private Lab360Repository labRepo;

//init
	public HairPinController (Lab360Repository lab){
		super();
		this.labRepo = lab;
		
//		//debug
//		Lab360 entity = new Lab360(1, 156, "CUGACAGAAGAGAGaGAGCAC(CUGACAGAAGAGAGUGAGCAC)", 
//				"ttggaattcatagaGTTTGCTCTTTGATCATGTCcgtctctctgcatttcaggaacactaactctgggagattttgcaagattatgatgatcatctttaatgtaggcatgatcacctcctttaaccatccacggcagagttggtgaaatgccggttgaattgtgcagaaggttttgtgagtgggCtgacagaagagagagagcaCaagag",
//				"..............((((.((((((..(((.(((((.((((((((((((((((..(((((((((((....................................................................))))))))))).....))...))))..))))))).........)))))))))))..))))))...)))).......",		
//				"NbLab360C08",	"+",	87606248);
//
//		Lab360 entity2 = new Lab360(2, 156, "CUGACAGAAGAGAGaGAGCAC(CUGACAGAAGAGAGUGAGCAC)", 
//				"ttggaattcatagaGTTTGCTCTTTGATCATGTCcgtctctctgcatttcaggaacactaactctgggagattttgcaagattatgatgatcatctttaatgtaggcatgatcacctcctttaaccatccacggcagagttggtgaaatgccggttgaattgtgcagaaggttttgtgagtgggCtgacagaagagagagagcaCaagag",
//				"..............((((.((((((..(((.(((((.((((((((((((((((..(((((((((((....................................................................))))))))))).....))...))))..))))))).........)))))))))))..))))))...)))).......",		
//				"NbLab360C08",	"+",	87606248);
//
//		//save twice
//		labRepo.save(entity);
//		labRepo.save(entity2);
	}

	// http://localhost:8080/rawdata?geneId=1
	@GetMapping("/rawdata1")
	@ResponseBody
    public String processGeneId1(@Valid @RequestParam("geneId") String geneId) {       
		String rawdata = hairPinService.getLabData(geneId);
        return rawdata;
    }

		
	// http://localhost:8080/miRNA 
	@RequestMapping(value="miRNA1",method = RequestMethod.GET)
    public String loadmiRNAPage() {    
       return "genome";
    }

	
	@RequestMapping(value="miRNA1",method = RequestMethod.POST)
	public String processmiRNA( @RequestParam String mirnaid, Model model) {
			
		String rawdata = hairPinService.getLabData( mirnaid);
		
		if(rawdata == null ||  rawdata.length() == 0) {
			model.addAttribute("found","no related microRNA found in database for " + mirnaid);
			 model.addAttribute("rawdata",rawdata);

			 return "genome";
		}
		
		BufferedImage image = hairPinService.createDiagram(rawdata);
		String encodedImage = hairPinService.encodeToBase64String(image, "png")	;
		 model.addAttribute("image", encodedImage);
		 model.addAttribute("mirnaid",mirnaid);		 
		 return "genome";
    }

	
    @GetMapping("/circle21")
    public String drawCircle(Model model) {
    	
		String rawdata = hairPinService.getLabData( "1");
		BufferedImage image = hairPinService.createDiagram(rawdata);
		String encodedImage = hairPinService.encodeToBase64String(image, "png")	;
   	
        // Pass the byte array to the model
        model.addAttribute("circleImage", encodedImage);
        model.addAttribute("rawdata", rawdata);

        return "cycle2"; // Return the name of the JSP file
    }	
				

	
}
