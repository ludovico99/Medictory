<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
   <%@page import ="logic.controller.ControllerCustomerRiciclaggio" %> 
   <%@page import ="logic.model.SessioneCliente" %> 
    <%@page import ="logic.ingegnerizzazione.RiciclaggioUtenteBean" %> 
    <%@page import ="java.util.List" %> 
     <%@page import ="logic.ingegnerizzazione.InputException" %> 
 <%
	ControllerCustomerRiciclaggio controller = new ControllerCustomerRiciclaggio();
 	SessioneCliente s = (SessioneCliente)session.getAttribute("sessione cliente");
 	try {
		if(request.getParameter("submit") != null){
				controller.ricicla(s,request.getParameter("farmaco"));

		}		
	}catch (InputException ev){
		ev.printStackTrace();
%>
			<jsp:forward page="Error.jsp">
			<jsp:param name="errore" value="<%=ev.getMessage()%>"/>
			</jsp:forward>
<%
	}
%>     
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <!-- meta charset -->
        <meta charset="utf-8">
        <!-- site title -->
        <title>Medictory | Riciclaggio</title>
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
                                    <h2>Riciclaggio<span class="title-bg">Ricicla</span></h2>
                                    <p>In questa sezione trovi tutti i medicinali scaduti che hai in casa... affrettati a riciclarli per ottenere tanti premi!</p>
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
<% 
  				List<RiciclaggioUtenteBean> riciclaggio = controller.findResources(s);
  				if(riciclaggio != null){
  				for (RiciclaggioUtenteBean r : riciclaggio ){
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
                        <input type="text" placeholder="Farmaco" name = "farmaco">
                         <input type="submit" class="btn btn-primary btn-custom-border text-uppercase" value = "submit" name = "submit"/>
			<span class="btn">
                          
                        </span>
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