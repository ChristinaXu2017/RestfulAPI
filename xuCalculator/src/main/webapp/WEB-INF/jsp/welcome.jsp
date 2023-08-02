 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
 
 <html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet" >
				
		<title>Calculate your date</title>		
	</head>
	<body>
		<h5>src/main/webapp/WEB-INF/jsp/welcome.jsp</h5>
		
		
		<div class="container">
			<h1>Enter Calculator Days</h1>
		<form:form method="post" modelAttribute="xuCalculator" >
			<fieldset class="mb-3">				
				<form:label path="startDate">Start Date</form:label>
				<form:input type="text" path="startDate" required="required"/>
				<form:errors path="startDate" cssClass="text-warning"/>
			</fieldset>			
			<fieldset class="mb-3">				
				<form:label path="endDate">End Date</form:label>
				<form:input type="text" path="endDate" required="required"/>
				<form:errors path="endDate" cssClass="text-warning"/>
			</fieldset>
			
			<h2>${result} </h2>
				<input type="submit" class="btn btn-success"/>			
			</form:form>			
		</div>		

		
		
		
	</body>
</html>
