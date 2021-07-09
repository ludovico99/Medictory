<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import ="logic.controller.ControllerCustomerAppuntamento" %>  
   <%@page import ="logic.model.SessioneCliente" %>
   <%@page import ="logic.ingegnerizzazione.InputException" %> 
<%
    
	if(request.getParameter("crea") != null){
		ControllerCustomerAppuntamento controller = new ControllerCustomerAppuntamento();
		SessioneCliente s =(SessioneCliente) session.getAttribute("sessione cliente");
		try{
			controller.prenotaRitiro(request.getParameter("nome"), request.getParameter("citta"), request.getParameter("indirizzo"), request.getParameter("data"),request.getParameter("farmacia"),request.getParameter("email"), s);
		}catch (InputException e){
			e.printStackTrace();
			%>
			<jsp:forward page="Error.jsp">
			<jsp:param name="errore" value="<%=e.getMessage()%>"/>
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
        <title>Medictory | Ritiro</title>
        <!-- meta description -->
        <meta name="description" content="">
        <!-- mobile viwport meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Libre+Baskerville:400,400i|Open+Sans:400,600,700,800" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/dark.css">
		 <link rel="stylesheet" href="css/creaE.css">
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

    <body class="creaE">
	<!--serve per l'apertura particolare-->
        <div class="preloader">
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            
        </div>
    
                                <div class="h2">
                                    <h2 >Prenota il tuo appuntamento</h2>
									
									
								
                                </div>
        <main class="site-wrapper">               
			<div class="container">
                   
			<form id="contact-form" action="#" method="post">
                 <br>       
				 <br>
				 

				<input type="text" placeholder="Nome" name="nome">
				<input type="text" placeholder="Città" name ="citta">
				<input type="text" placeholder="Indirizzo e numero civico" name="indirizzo">
				<input type="date" placeholder="Seleziona la data" name="data" >
				<input type="text" placeholder="Farmacia associata" name="farmacia">
				<input type="email" placeholder="Email" name="email">
			
			<span class="btn">
                <button type="submit" class="btn btn-primary btn-custom-border text-uppercase" name="crea" >Crea</button>
            </span>
            <br>
		    </form>
	   
		<a href="CustomerHomepage.jsp">Back</a>
		</div>
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