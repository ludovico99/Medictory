<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@page import = "logic.model.SessioneFarmacia" %> 
<%@page import = "logic.controller.ControllerPharmacyGestione" %>
<%@page import = "logic.ingegnerizzazione.PharmacyGestioneBean"%>
<%@page import = "logic.ingegnerizzazione.InputException"%>
<%@page import ="java.util.List" %> 
<%@page import ="java.util.ArrayList" %> 

<%
	
	if(request.getParameter("Verifica") != null){
		int dim = Integer.parseInt(request.getParameter("Verifica"));
		ControllerPharmacyGestione controller = new ControllerPharmacyGestione();
		SessioneFarmacia s =(SessioneFarmacia) session.getAttribute("sessione farmacia");
		int j=0;
		for(j=0; j<dim; j++){
			String cBox = "c" + Integer.toString(j);
			if(request.getParameter(cBox) != null) {
				System.out.println("ok"+Integer.toString(j));
				String [] input = request.getParameter(cBox).split("/");
				controller.verifica(s, input[0], input[1], input[3], input[2]);
			}
		}
		
}	
%>


<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <!-- meta charset -->
        <meta charset="utf-8">
        <!-- site title -->
        <title>Medictory | Verifica</title>
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
                                    <h2>Verifica<span class="title-bg">Verifica</span></h2>
                                    <p>Verifica i riciclaggi che i tuoi clienti hanno effettuato</p>
                                </div>
                            </div>
                        </div> <!-- /.row -->
		<form id="formTb" action="#" method="post">
	    <table>
	    <caption> </caption>
 		   <tr>
    			    <th scope="col">Utente</th>
			    	<th scope="col">Farmaco</th>
    			    <th scope="col">Quantitativo</th>
			   		<th scope="col">Scadenza</th>
			   		<th scope="col">Verifica</th>
  			</tr>
  			<% SessioneFarmacia s = (SessioneFarmacia) session.getAttribute("sessione farmacia");
  				ControllerPharmacyGestione controller = new ControllerPharmacyGestione();
  				List<PharmacyGestioneBean> risorse = controller.findResources(s);
  				int i=0;
  				if (risorse != null){%>
  				
			
 
 			  <%for (PharmacyGestioneBean r : risorse ){
 			  		String id = "c"+Integer.toString(i);%>
  					<tr>
	    			    <td><%=r.getUtente()%></td>
				    	<td><%=r.getFarmaco() %></td>
	    			    <td><%=r.getQuantitativo() %></td>
	    			    <td><%=r.getScadenza() %></td>
	    			    <td><input type="checkbox" value = "<%=(r.getUtente()+"/"+r.getFarmaco()+"/"+r.getQuantitativo()+"/"+r.getScadenza())%>" name="<%=id%>"></td>
			       	</tr>
  			  <%i++;}%>
 			
			
  			
		    </table>
		    <br><br>
			    <span class="btn">
		        	<button type="submit" name="Verifica" value = "<%=i %>" class="btn btn-primary btn-custom-border text-uppercase">Verifica</button>
		        </span>
		        <% } %> 
		    </form>




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