<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@page import ="logic.model.SessioneCliente" %> 
<%@page import ="logic.controller.ControllerCustomerResource" %> 
<%@page import ="logic.ingegnerizzazione.RisorseUtenteBean" %>  
<%@page import ="java.util.List" %> 
<%@page import ="logic.model.FarmacoCliente" %> 
<%@page import ="logic.ingegnerizzazione.Observer" %>
<%@page import ="logic.ingegnerizzazione.InputException" %>


<%
	if(request.getParameter("submit") != null){
		ControllerCustomerResource controller = new ControllerCustomerResource();
		SessioneCliente s =(SessioneCliente) session.getAttribute("sessione cliente");
		try {
			if(request.getParameter("farmaco").compareTo("") != 0  && request.getParameter("quantitativo").compareTo("") != 0 && request.getParameter("scadenza").compareTo("") != 0 ){	
				FarmacoCliente farmaco = controller.addMedicine(s,request.getParameter("farmaco"),request.getParameter("descrizione"), request.getParameter("scadenza"), Integer.parseInt(request.getParameter("quantitativo")));
				if (farmaco != null)
				farmaco.notifica();
			}
			else throw new InputException("Non hai inserito tutti i parametri");
		}catch (InputException ev){
			ev.printStackTrace();
	%>
				<jsp:forward page="Error.jsp">
				<jsp:param name="errore" value="<%=ev.getMessage()%>"/>
				</jsp:forward>
	<%
		}
	
	}
	
%> 
 
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
    <script type="text/javascript">

        window.onunload = codeAddress;
    </script>
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
    <body onunload = "alert(('Torna a trovarci!');)" class="dark">
	
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
			    	<th scope="col">Scadenza</th>
  				</tr>
  			<% SessioneCliente s = (SessioneCliente)session.getAttribute("sessione cliente");
  				ControllerCustomerResource controller = new ControllerCustomerResource();
  				List<RisorseUtenteBean> risorse = controller.findResources(s);
  				
  				if (risorse != null){
  				for (RisorseUtenteBean r : risorse ){
  					%>
  					<tr>
    			    <td> <%=r.getFarmaco()%></td>
    		
			    <td><%= r.getDescrizione() %></td>
    			    <td><%=r.getQuantitativo() %></td>
			    <td><%=r.getScadenza() %></td>
		       </tr>
  			<tr>
  					<% }} %>    		
		    </table>

<br><br>
		<div class="container">
                    <div class="row">
		        
		
			<form id="contact-form" action="risorse.jsp" method="post">
                        <input type="text" placeholder="Farmaco" name = "farmaco">
						<input type="text" placeholder="Descrizione" name = "descrizione">
						<input type="text" placeholder="Quantità" name = "quantitativo">
						<input type="date" placeholder="Scadenza" name = "scadenza">
						<input type="submit" class="btn btn-primary btn-custom-border text-uppercase" name = "submit" value = "submit">
		    </form>
		
		    </div>
		</div>

                </div> <!-- /.pt-tablecell -->
                <br><br>    
                <a href="CustomerHomepage.jsp">Back</a>          
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