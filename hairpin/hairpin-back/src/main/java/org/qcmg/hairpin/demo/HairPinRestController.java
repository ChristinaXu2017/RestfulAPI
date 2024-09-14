package org.qcmg.hairpin.demo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.qcmg.hairpin.favorite.Favorite;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HairPinRestController {
	
	private  HairPinService hairPinService;	

	//init
	public HairPinRestController (Lab360Repository lab, HairPinService server){
		super();
		this.hairPinService = server;
	}
	
	
	@GetMapping(value="/miRNA/{mid}")
	public List<Favorite>  processmiRNA1( @PathVariable String mid) {
				
		List<LAB360> labs = hairPinService.getLabs(mid);

		List<Favorite> hairpins = new ArrayList<>();
		for(LAB360 lab : labs) {
			BufferedImage image = hairPinService.createDiagram(lab.toString());
			String encodedImage = hairPinService.encodeToBase64String(image, "png")	;
			//set order as 0 since this one won't add into dababase
			Favorite fa = new Favorite(0, lab.getPureNumber(), lab.getId(), null, encodedImage );
			hairpins.add(fa);		
		}
			 
		 return hairpins;
    }
	
	@GetMapping(value="/test/{mid}")
	public String processTest( @PathVariable String mid) {
						
		List<LAB360> labs = hairPinService.getLabs("1");
		
		List<String> images = new ArrayList<>();
		for(LAB360 lab : labs) {
			BufferedImage image = hairPinService.createDiagram(lab.toString());
			String encodedImage = hairPinService.encodeToBase64String(image, "png")	;
			images.add(encodedImage);			
		}
			 
		 return images.get(0);
    }
	
	//use to check whether the token on http header works or not
	//http://localhost:5050/basicauth
	@GetMapping(path = "/basicauth")
	public String basicAuthCheck() {
		return "Success"; 
	}	 
	
}

@ConfigurationProperties(prefix = "poxy-service")
Record  PoxyServiceCongfiguration(String url, String user, String key) {}



