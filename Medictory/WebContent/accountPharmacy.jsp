<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        
<%@page import="logic.controller.ControllerPharmacyAccount" %>
<%@page import="logic.controller.ControllerLogout" %> 
<%@page import ="java.util.List" %> 
<%@page import ="logic.model.SessioneFarmacia" %> 
<%@page import ="logic.ingegnerizzazione.ListaClientiBean" %> 
   	
<%	
  	ControllerPharmacyAccount controller = new ControllerPharmacyAccount();
  	SessioneFarmacia s =(SessioneFarmacia) session.getAttribute("sessione farmacia");
  	if (request.getParameter("logout")!= null){
  		ControllerLogout c = new ControllerLogout();
  		c.makeDataPharmacyPersistent(s);
%>
	<jsp:forward page="index.jsp"/>
<%
  	}
 %>    
    
    
    
    
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <!-- meta charset -->
        <meta charset="utf-8">
        <!-- site title -->
        <title>Medictory | Account</title>
        <!-- meta description -->
        <meta name="description" content="">
        <!-- mobile viwport meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- fevicon -->
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
        
        <!-- ================================
        CSS Files
        ================================= -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/dark.css">
        <link id="color-changer" rel="stylesheet" href="css/colors/color-0.css">
    </head>

    <body class="dark">

        <div class="preloader">
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
            <div class="loading-mask"></div>
        </div>

        

        <main class="site-wrapper">
            <div class="pt-table">
                <div class="pt-tablecell page-about relative">

                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
                                <div class="page-title text-center">
                                    <h2>Account <span class="title-bg">FARMA</span></h2>
                                </div>
                            </div>
<br><br>

<!--primo container sulla sinistra-->
                            <div class="col-xs-12 col-md-6">
                                <div class="about-author">
                                    <div class="author-desc">
					<br>
					<br>
                                        <p><strong>USERNAME: </strong><%= s.getUsername() %></p>
                                        <p><strong>EMAIL: </strong><%= s.getEmail() %></p>
                                        <p><strong>NOME FARMACIA: </strong><%= s.getNomeFarmacia() %></p>
                                        <p><strong>INDIRIZZO: </strong><%= s.getIndirizzo() %></p>
                                        <p><strong>NUMERO CLIENTI: </strong><%= s.getClienti().size() %></p>
                                    </div>
                                </div>

                           <form id="contact-form" action="#" method="post">
							 <input type="submit" class="btn btn-primary btn-custom-border text-uppercase" value = "LOGOUT" name = "logout">
								</form>

                            </div> <!-- /.col -->

<!--secondo container sulla destra-->

                            <div class="col-xs-12 col-md-6">

<!-- inizio blocco con la lista dei clienti sul sito -->

				<div class="history-block">
                                    <div class="section-title light clear">
                                        <h3>Clienti</h3>
                                    </div>
                                    <!-- /.section-title -->
                                    <div class="history-scroller">
                                        
                                        <%	List<ListaClientiBean> clienti = controller.findListOfCustomers(s/*, controllerGrafico*/);
                                    	for (ListaClientiBean c : clienti){
                                    	
                                   %>
                                        <div class="history-item">
                                            <div class="history-text">
                                                <h5><%= c.getUsername()%></h5>
												<span><%=c.getEmail()%></span>
                                               
                                            </div>
                                        </div>
                                        <%
                                   	      	}
                                   		 %>
                                         
                                        </div>
                                    
                  </div> 

<!-- fine blocco con la lista dei clienti sul sito -->


                            </div> <!-- /.col -->
                        </div> <!-- /.row -->
                    </div> <!-- /.container -->
				
				<br><br>    
                <a href="PharmacyHomepage.jsp">Back</a>  
                   
                </div> <!-- /.pt-tablecell -->
                
            </div> <!-- /.pt-table -->
        </main> <!-- /.site-wrapper -->
        
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
        <script src="js/main.js"></script>
    </body>
</html>