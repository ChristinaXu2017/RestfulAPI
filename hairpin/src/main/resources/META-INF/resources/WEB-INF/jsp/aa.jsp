
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
      <img src="../../image/realworld.png" alt="Your Image">
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


    <body>
        <h1>Displaying MicroRNA hairpin structure</h1>
        
        <div style="text-align: center;">
 	        <h2>Model("image") is ${image} </h2>
	        <h2>Model("geneid") is ${mirnaid} </h2>	  
	        
	        <form>
	        	Name: <input type="text" name="name">
	        	Password: <input type="password" name="password">
	        	<input type="submit">
	        </form>      
        
	
	
        </div>

	</body>
</html>