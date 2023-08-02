<!DOCTYPE html>
<html>
<head>
  <title>Website with Dropdown Menus</title>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <div class="gradient-section">
    <div class="created-by">Nicotiana benthamiana Genome & Transcriptome</div>
    <div class="image-section">
      <img src=".\image\realworld.png" alt="Your Image">
    </div>
  </div>

  <header>
    <nav>
      <ul class="horizontal-menu">
        <li><a href="#">Section 1</a>
          <ul class="dropdown-menu">
            <li><a href="./index.html">home page</a></li>
            <li><a href="./index.jsp">genome finder</a></li>
            <li><a href="https://sefapps02.qut.edu.au/atlas/tREX5.php">Version 5</a></li>
            <li><a href="https://sefapps02.qut.edu.au/atlas/tREX6.php">Version 6</a></li>
            <li><a href="#">Subsection 1.3</a></li>
          </ul>
        </li>
        <li><a href="#">Section 2</a>
          <ul class="dropdown-menu">
            <li><a href="#">Subsection 2.1</a></li>
            <li><a href="#">Subsection 2.2</a></li>
            <li><a href="#">Subsection 2.3</a></li>
          </ul>
        </li>
        <li><a href="#">Section 3</a>
          <ul class="dropdown-menu">
            <li><a href="#">Subsection 3.1</a></li>
            <li><a href="#">Subsection 3.2</a></li>
            <li><a href="#">Subsection 3.3</a></li>
          </ul>
        </li>
      </ul>
    </nav>
  </header>

  <div class="content">
    <!-- Your website content goes here -->
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Displaying Gene</title>
    </head>
    <body onload="submit()">
        <h1>Displaying Gene</h1>
        <div style="text-align: center;">
            <p style="font-size: 20px;">Gene Wanted: <input type="text" id="gene" value="NbL00g00050.1">
            <button id="submit" onclick="submit()">Submit</button></p>
        </div>
        <div style="text-align: center;">
            <div style="display: inline-block;">
                <img class="image-main" id="img_1"  width="800" height="400">
            </div>
            <div style="display: inline-block;">
                <fieldset style="float: left; text-align: left;">
                    <legend>Legend</legend>
                    <ul>                
                        <li><span style="color: blue;">&#9632;</span> Blue: represents flowers</li>
                        <li><span style="color: red;">&#9632;</span> Red: represents leaves</li>
                        <li><span style="color: cyan;">&#9632;</span> Cyan: represents roots</li>
                        <li><span style="color: magenta;">&#9632;</span> Magenta: represents seedlings</li>
                        <li><span style="color: pink;">&#9632;</span> Pink: represents stems</li>
                    </ul>
                </fieldset>
            </div>
        </div>
        
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <table id="popupTable">
                    <!-- Table content dynamically generated in JavaScript -->
                </table>
            </div>
        </div>
        
        
        <!--<p id="rawdatahere"></p>--> 
        <script src="index.js"></script>
        <link href="newcss.css" rel="stylesheet" type="text/css">
    </body>
  </div>

  <script src="script.js"></script>
</body>
</html>