<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
   <%@page import ="logic.controller.ControllerPharmacyEvent" %>  
   <%@page import ="logic.model.SessioneFarmacia" %> 
   <%@page import ="logic.model.EventoFarmacia" %> 
   <%@page import ="java.util.ArrayList" %> 
   <%@page import ="java.util.List" %> 
   <%@page import ="java.util.Formatter" %> 
   <%@page import ="java.util.Date" %> 
   <%@page import ="java.text.SimpleDateFormat" %> 
   <%@page import ="logic.ingegnerizzazione.*" %> 
   
   
<%

    
	if(request.getParameter("crea") != null){
		ControllerPharmacyEvent controller = new ControllerPharmacyEvent();
		SessioneFarmacia s =(SessioneFarmacia) session.getAttribute("sessione farmacia");
			try {
				if(request.getParameter("nome").compareTo("") == 0 || request.getParameter("premio").compareTo("") == 0 || request.getParameter("dettagli").compareTo("")  == 0 || request.getParameter("data inizio").compareTo("")==0 || request.getParameter("data fine").compareTo("")==0) {
					throw new InputException("Non hai inserito tutti i parametri");
				} else {
						List<String> info = new ArrayList<>();
						info.add(request.getParameter("nome"));
						info.add(request.getParameter("dettagli"));
						info.add(request.getParameter("premio"));
						info.add(request.getParameter("data inizio"));
						info.add(request.getParameter("data fine"));
						EventoFarmacia ev;
						controller.addEvent(s , info, Integer.parseInt(request.getParameter("livello")));
					
					}
				
			}catch (InputException ex) {
				%>
				<jsp:forward page="Error.jsp">
				<jsp:param name="errore" value="<%=ex.getMessage()%>"/>
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
        <title>Medictory | Nuovo Evento</title>
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
        
	
	<div class="h2"><br>
     	Crea un nuovo evento per i tuoi clienti
		<br>
	</div>
                  
     <main class="site-wrapper"> 
	<div class="container">
	    
		<form id="contact-form" action="#" method="post">
	    
			<br>
			<input type="text" placeholder="Nome" name="nome">
			<input type="text" placeholder="Dettagli" name="dettagli">
			<input type="number" placeholder="Livello richiesto" name="livello">
			<select id="Premio" name ="premio">
				<option value="Premio">Premio</option>
				<option value="coupon">Coupon</option>
				<option value="sconto">Sconto 50%</option>
				<option value="analisi">Analisi gratis</option>
			</select>
			<input type="date" placeholder="Data Inizio" name="data inizio">
			<input type="date" placeholder="Data fine" name="data fine">
			
			<span class="btn">
                <button type="submit" class="btn btn-primary btn-custom-border text-uppercase" name="crea">Crea</button>
            </span>
			
		    </form>
    
        <a href="PharmacyHomepage.jsp">Back</a> 
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