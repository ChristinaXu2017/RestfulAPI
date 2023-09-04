<%@ page contentType="image/png" %>
<%@ page import="java.io.OutputStream" %>

<%
    // Get the byte array data from the model
    byte[] imageData = (byte[]) request.getAttribute("circleImage");
    if (imageData != null) {
        // Write the image data to the response
        response.setContentType("image/png");
        OutputStream outStream = response.getOutputStream();
        outStream.write(imageData);
        outStream.flush();
        outStream.close();
    }
%>