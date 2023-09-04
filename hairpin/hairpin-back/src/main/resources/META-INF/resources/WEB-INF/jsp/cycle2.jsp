<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Circle Image</title>
</head>
<body>
    <h1>Circle Image</h1>
    <h3>${rawdata} (rawdata)</h3>
    <h3>${instances0} (instances0)</h3><h3></h3>    
	<h3>${red_sequence} (red_sequence)</h3>	
	<h3>${splitbyspace0} (splitbyspace0)</h3>

	<h3>${sequence} (sequence)</h3>	
	<h3>${symbols} (symbols)</h3>



	
    <%
        // Get the Base64-encoded image data from the model
        String encodedImage = (String) request.getAttribute("circleImage");
        if (encodedImage != null) {
    %>
        <!-- Display the image using the Base64 data -->
        <img src="data:image/png;base64,<%= encodedImage %>" alt="Circle Image">
    <%
        } else {
    %>
        <p>No circle image data found.</p>
    <%
        }
    %>
</body>
</html>


