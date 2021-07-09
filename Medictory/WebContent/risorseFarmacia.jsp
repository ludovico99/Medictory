<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@page import ="logic.model.SessioneFarmacia" %> 
<%@page import ="logic.controller.ControllerPharmacyResource" %> 
<%@page import ="logic.ingegnerizzazione.RisorseFarmaciaBean" %>
<%@page import ="java.util.List" %> 
<%@page import ="logic.model.FarmacoFarmacia" %> 
<%@page import ="logic.ingegnerizzazione.Observer" %>
<%@page import ="logic.ingegnerizzazione.InputException" %>
<%@page import ="logic.ingegnerizzazione.DataNotFoundException" %>


<%
	if(request.getParameter("submit") != null){
		ControllerPharmacyResource controller = new ControllerPharmacyResource();
		SessioneFarmacia s =(SessioneFarmacia) session.getAttribute("sessione farmacia");
		try{
		
			if(request.getParameter("farmaco").compareTo("") != 0  && request.getParameter("descrizione").compareTo("") == 0 && request.getParameter("quantitativo").compareTo("") != 0 && request.getParameter("scadenza").compareTo("") == 0 ){
				controller.cambiaQuantita(s, request.getParameter("farmaco"), Integer.parseInt(request.getParameter("quantitativo")));
			}
			else if(request.getParameter("farmaco").compareTo("") != 0  && request.getParameter("descrizione").compareTo("") != 0 && request.getParameter("quantitativo").compareTo("") != 0 && request.getParameter("scadenza").compareTo("") != 0 ){	
				FarmacoFarmacia farmaco = controller.addMedicine(s,request.getParameter("farmaco"),Integer.parseInt(request.getParameter("quantitativo")),request.getParameter("descrizione"), request.getParameter("scadenza"));
				if (farmaco != null)
					farmaco.notifica();
			}else{
				throw new InputException("Non hai inserito parametri");
			}
		} catch (InputException | DataNotFoundException e1) {
				e1.printStackTrace();

	%>
				<jsp:forward page="Error.jsp">
				<jsp:param name="errore" value="<%=e1.getMessage()%>"/>
				</jsp:forward>
	<%
		}
	
	}	
%>

<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <!-- meta charset -->
        <meta charset="utf-8">
        <!-- site title -->
        <title>Medictory | Risorse</title>
        <!-- meta description -->
        <meta name="description" content="">
        <!-- mobile viwport meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Libre+Baskerville:400,400i|Open+Sans:400,600,700,800" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/dark.css">
        <link id="color-changer" rel="stylesheet" href="css/colors/color-0.css">
	<style>
	    table {
	    border-collapse: collapse;
	    width: 100%;
	}

	th, td {
    	    padding: 8px;
	    text-align: left;
	    border-bottom: 1px solid #ddd;
	}

	tr:hover{background-color:#f5f5f5}
	</style>
    </head>

    <body class="dark">
	<!--serve per l'apertura particolare-->
        <div class="preloader">
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
        </div>

        <main class="site-wrapper">


            <div class="pt-table">
                <div class="pt-tablecell page-resume relative">

                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
                                <div class="page-title text-center">
                                    <h2>Risorse<span class="title-bg">Risorse</span></h2>
                                    <p>Inserisci i tuoi medicinali</p>
                                </div>
                            </div>
                        </div> <!-- /.row -->

	            <table>
	            <caption></caption>
 		        <tr>
			    	<th scope="col">Farmaco</th>
    			    <th scope="col">Descrizione</th>
    			    <th scope="col">Quantitativo</th>
  				</tr>
  			<% SessioneFarmacia s = (SessioneFarmacia)session.getAttribute("sessione farmacia");
  				ControllerPharmacyResource controller = new ControllerPharmacyResource();
  				List<RisorseFarmaciaBean> risorse = controller.findResources(s);
  				
  				if (risorse != null){
  				for (RisorseFarmaciaBean r : risorse ){
  					%>
  					<tr>
    			    <td> <%=r.getFarmaco()%></td>
			   		<td><%= r.getDescrizione() %></td>
    			    <td><%=r.getQuantitativo() %></td>
		       </tr>
  			
  			<% }} %> 
		    </table>

<br><br>
		<div class="container">
                    <div class="row">
		        
		
			<form id="contact-form" action="#" method="post">
                        <input type="text" placeholder="Farmaco" name="farmaco">
				<input type="text" placeholder="Descrizione" name="descrizione">
				<input type="text" placeholder="Quantità" name="quantitativo">
				<input type="date" placeholder="Scadenza" name="scadenza">
			<span class="btn">
                            <button type="submit" class="btn btn-primary btn-custom-border text-uppercase" name="submit">Submit</button>
                        </span>
		    </form>
		
		    </div>
		</div>

                </div> <!-- /.pt-tablecell -->
                <br><br>    
                <a href="PharmacyHomepage.jsp">Back</a>    
            </div> <!-- /.pt-table -->

	    
        </main>
        
        <!-- ================================
        JavaScript Libraries
        ================================= -->
        <script src="js/vendor/jquery-2.2.4.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/jquery.easing.min.js"></script>
        <script src="js/isotope.pkgd.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery-validation.min.js"></script>
        <script src="js/form.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>