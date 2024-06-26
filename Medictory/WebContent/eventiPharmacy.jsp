<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import ="logic.controller.ControllerPharmacyEvent" %> 
   	<%@page import ="logic.model.SessioneFarmacia" %> 
    <%@page import ="logic.ingegnerizzazione.PharmacyAllEventBean" %> 
    <%@page import ="logic.ingegnerizzazione.InputException" %>
    <%@page import ="logic.ingegnerizzazione.RequirementException" %>
    <%@page import ="logic.model.EventoDAO" %>
    <%@page import ="logic.model.EventoFarmacia" %>
    <%@page import ="java.util.List" %> 
 <%
 	ControllerPharmacyEvent controller = new ControllerPharmacyEvent();
 	SessioneFarmacia s = (SessioneFarmacia)session.getAttribute("sessione farmacia");
	if (s!= null) controller.premiazione(s);
	if(request.getParameter("elimina") != null){
		try{
				controller.deleteEvent(s, request.getParameter("evento"));
		}catch(InputException e1) {
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
        <title>Medictory | Eventi Attivi</title>
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
                                    <h2>Eventi<span class="title-bg">Attivi</span></h2>
                                    <p>In questa sezione trovi tutti gli eventi che hai creato e che sono attualmente "in corso" o "prossimi"</p>
                                </div>
                            </div>
                        </div> <!-- /.row -->
                        

	            <table>
	            <caption></caption>
 		        <tr>
			    	<th scope="col">Evento</th>
    			    <th scope="col">Descrizione</th>
    			    <th scope="col">Requisiti</th>
			    	<th scope="col">Premio</th>
    			    <th scope="col">Inizio</th>
    			    <th scope="col">Fine</th>
  				</tr>
  			<% 
  				List<PharmacyAllEventBean> eventi = controller.findEvent(s);
  				if (eventi != null){
	  				for (PharmacyAllEventBean e : eventi ){
	  					%>
	  					<tr>
	    			    <td> <%= e.getEvento()%></td>
				    	<td><%= e.getDescrizione() %></td>
	    			    <td><%= e.getRequisiti() %></td>
	    			    <td><%= e.getPremio() %></td>
	    			    <td><%= e.getInizio() %></td>
	    			    <td><%= e.getFine() %></td>
			     		  </tr>
  					<% }
  				}%>  
		    </table>

<br><br>
		<div class="container">
                    <div class="row">
		        
		
			<form id="contact-form" action="#" method="post">
                        <input type="text" placeholder="Evento" name="evento">
			<span class="btn">
                   <button type="submit" class="btn btn-primary btn-custom-border text-uppercase" name="elimina">Elimina</button>
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