<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	

<div class="container">
	
	<h1>Enter Todo Details</h1>
	
	<form:form method="post" modelAttribute="todo">

		<fieldset class="mb-3">				
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required"/>
			<form:errors path="description" cssClass="text-warning"/>
		</fieldset>

		<fieldset class="mb-3">				
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning"/>
		</fieldset>

		
		<form:input type="hidden" path="id"/>

		<form:input type="hidden" path="done"/>

		<input type="submit" class="btn btn-success"/>
	
	</form:form>
	
</div>


       <div style="text-align: center;">
        
			<form:form method="post" modelAttribute="lab360">
			
				<fieldset class="mb-3">				
					<form:label path="id">miRNA ID</form:label>
					<form:input type="text" path="id" required="required"/>
					<form:errors path="id" cssClass="text-warning"/>
				</fieldset>
							 
				<form:input type="hidden" path="pureNumber"/>
				<form:input type="hidden" path="sequence"/>
				<form:input type="hidden" path="precursor_seq"/>
				<form:input type="hidden" path="structure"/>		
				<form:input type="hidden" path="chr"/>
				<form:input type="hidden" path="strand"/>
				<form:input type="hidden" path="start"/>
				<input type="submit" class="btn btn-success"/>
			
			</form:form>
			
			<form:form method="post" action="/miRNA">
				<form:input path="mirnaid" required="required"/> 
        		<button type="submit">SubmitID</button>
   			 </form:form>
					
				
	        <h2>Model("image") is ${image} </h2>
	        <h2>Model("geneid") is ${geneid} </h2>
        </div>

<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>
