<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="logic.controller.ControllerPharmacyAppuntamenti" %>
<%@page import ="logic.model.SessioneFarmacia" %> 
<%@page import ="java.util.List" %> 
<%@page import ="logic.ingegnerizzazione.PharmacyAppuntamentiBean"%>

    
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
                                    <h2>Ritiro<span class="title-bg">Ritiro</span></h2>
                                    <p>Controlla i prossimi ritiri da effettuare</p>
                                </div>
                            </div>
                        </div> <!-- /.row -->

	            <table>
	            <caption></caption>
	 		        <tr>
		    			<th scope="col">Utente</th>
					    <th scope="col">Email</th>
		    			<th scope="col">Città</th>
					    <th scope="col">Indirizzo</th>
					    <th scope="col">Data</th>
  					</tr>
  			<% 	SessioneFarmacia s = (SessioneFarmacia)session.getAttribute("sessione farmacia");
  				ControllerPharmacyAppuntamenti controller = new ControllerPharmacyAppuntamenti();
  				List<PharmacyAppuntamentiBean> ritiri = controller.findListOfRitiri(s);
  				
  					if (ritiri != null){
  						for (PharmacyAppuntamentiBean r : ritiri ){%>
	  					<tr>
	    			    <td> <%=r.getUtente()%></td>
	    			    <td><%= r.getEmail() %></td>
	    			    <td><%=r.getCity()%></td>
				   		<td><%=r.getAddress()%></td>
	    			    <td><%=r.getData().toString() %></td>
			       </tr>
	  			
	  			<% }} %> 
			    </table>

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