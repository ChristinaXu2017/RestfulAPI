
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
  <title>Benthmania</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <script src="index.js"></script>
  <link href="newcss.css" rel="stylesheet" type="text/css">
</head>

<body>
  <div class="gradient-section">
    <div class="created-by">Nicotiana benthamiana Genome & Transcriptome</div>
    <div class="image-section">
      <img src="/image/realworld.png" alt="Your Image">
    </div>
  </div>
  

      <div class="image-section2">
        <img src="/image/homepage1.png" alt="Image 1" width="400" height="200">
      </div>
      <div class="image-section2">
        <img src="/image/homepage2.png" alt="Image 2" width="400" height="200">
      </div>
    </div>

  <header>
    <nav>
      <ul class="horizontal-menu">
        <li><a href="#">HOME PAGE</a>
          <ul class="dropdown-menu">
            <li><a href="./index.html">home page</a></li>
            <li><a href="https://sefapps02.qut.edu.au/atlas/tREX5.php">Version 5</a></li>
            <li><a href="https://sefapps02.qut.edu.au/atlas/tREX6.php">Version 6</a></li>
          </ul>
        </li>
        <li><a href="#">ATLAS</a>
          <ul class="dropdown-menu">
            <li><a href="./gene.jsp">gene finder</a></li>
            <li><a href="./genome.jsp">microRNA</a></li>
          </ul>
        </li>
        <li><a href="#">DOWNLOAD</a>
            <ul class="dropdown-menu">
                <li><a href="./download.html">DOWNLOAD</a></li>
                <li><a href="#">Subsection 3.2</a></li>
                <li><a href="#">Subsection 3.3</a></li>
            </ul>
        </li>
      </ul>
    </nav>
  </header>

   
        <h1>Displaying MicroRNA hairpin structure</h1>
        

        <h2>${rawdata} (rawdata)</h2>
<!--    <h2>${sequence} (sequence)</h2>
        <h2>${symbols} (symbols)</h2>
 -->	
        <div style="text-align: center;">
 	        
	        <form method="post">
	        	MicroRNA ID/Order ID: <input type="text" name="mirnaid">
	        	<input type="submit">
	        </form>   
	        
	        <h2>${found}</h2>
	        <div style="text-align: center;">
	            <div style="display: inline-block;">
	       	         <img class="image-main" id="img_1_gene" src="data:image/png;base64, ${image}" width="1400" height="2000">
	            </div>
       		</div>
	           
	         <script>
   			     // Call the JavaScript function and pass the value from the Spring model
   			     convertToImage(${image});
   			 </script>	          
             
         </div>
	
</body>	


</html>
