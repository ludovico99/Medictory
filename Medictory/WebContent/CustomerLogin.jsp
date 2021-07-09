<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
    <%@page import="logic.controller.ControllerLogin" %>
   	<%@page import ="logic.ingegnerizzazione.LoginBean" %> 
   	<%@page import ="logic.model.SessioneCliente" %> 
   	<%@page import ="logic.ingegnerizzazione.InputException" %> 
   	<%@page import ="logic.ingegnerizzazione.FarmaciaBean" %>
   	<%@page import ="logic.controller.ControllerCustomerAccount" %>
   	<%@page import ="logic.controller.ControllerRegistrazione" %>
   	<%@page import ="java.util.ArrayList" %>
   	<%@page import="logic.ingegnerizzazione.RegistrazioneCustomerBean" %>
   	
 <% 
 	LoginBean loginBean = new LoginBean();
 	RegistrazioneCustomerBean accountBean = new RegistrazioneCustomerBean();
 	if (request.getParameter("login")!= null){
	 	if(request.getParameter("username")!=null) {
			String username = request.getParameter ("username");
			loginBean.setUsername(username);
	 	}
		if(request.getParameter("password")!=null) {
		 	String password = request.getParameter ("password");
		 	loginBean.setPassword(password);
		}
		try{
			ControllerLogin controller = new ControllerLogin();
			SessioneCliente s = controller.loginCliente(loginBean);
			session.setAttribute("sessione cliente", s);
		} catch (InputException e1) {
			e1.printStackTrace();
%>
			<jsp:forward page="Error.jsp">
			<jsp:param name="errore" value="<%=e1.getMessage()%>"/>
			</jsp:forward>
<%
		}
%>
 <jsp:forward page="CustomerHomepage.jsp"></jsp:forward>
<% 
	}else if(request.getParameter("registra") !=null){
		if(request.getParameter("usernameReg")!=null) {
			String usernameReg = request.getParameter ("usernameReg");
			accountBean.setUsername(usernameReg);
	 	}
		if(request.getParameter("emailReg")!=null) {
		 	String emailReg = request.getParameter ("emailReg");
		 	accountBean.setEmail(emailReg);
		}
		if(request.getParameter("pwdReg")!=null) {
		 	String passwordReg = request.getParameter ("pwdReg");
		 	accountBean.setPassword(passwordReg);
		}
		if(request.getParameter("farmaReg")!=null) {
		 	String farmaciaReg = request.getParameter ("farmaReg");
		 	accountBean.setFarmaciaAssociata(farmaciaReg);
		}
		
		ControllerRegistrazione controller = new ControllerRegistrazione();
		if(!controller.registraCliente(accountBean.getUsername(), accountBean.getPassword(), accountBean.getEmail(), accountBean.getFarmaciaAssociata())){
%>
			<jsp:forward page="Error.jsp">
			<jsp:param name="errore" value="Input errato"/>
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
        <title>Medictory | Customer Login</title>
        <!-- meta description -->
        <meta name="description" content="">
        <!-- mobile viwport meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
       <link rel="stylesheet" href="css/new.css">
       

</head>

    <body>


		<div class="container" id="container">
			<div class="form-container sign-up-container">
				<form action="CustomerLogin.jsp" name="customerRegistration" method="post">
					<h1>Create Account</h1>
		
					<input type="text" placeholder="Username" name = "usernameReg"/>
					<input type="email" placeholder="Email" name = "emailReg" />
					<input type="password" placeholder="Password" name = "pwdReg" /><br>
					<label for="farma">Farmacia associata:</label><br>
					<select class="mySelect" name="farmaReg" id="farma">
<%
					ControllerCustomerAccount controllerRegistrazione = new ControllerCustomerAccount();
					ArrayList<FarmaciaBean> req = (ArrayList<FarmaciaBean>) controllerRegistrazione.findListOfPharmacy();
					if(req!=null)
						for(FarmaciaBean f: req){
%>
							<option value="<%=f.getUsername()%>"><%=f.getUsername()%> [<%=f.getIndirizzo()%>]</option>
<%
					}
%>

					</select>
					<br><br>
					<button type="submit" name="registra">Sign Up</button>
				</form>
			</div>
			<div class="form-container sign-in-container">
				<form action="CustomerLogin.jsp" name="myform" method="post">
					<h1>Sign in customer</h1>
					<br>
					<input type="text" placeholder="Username" name = "username" />
					<input type="password" placeholder="Password" name = "password" />
					<button type="submit" name="login">Login</button>
		
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay">
					<div class="overlay-panel overlay-left">
						<h1>Benvenuto su Medictory!</h1>
						<p>Hai già un account? Prosegui con il login</p>
						<button class="ghost" id="signIn">Sign In</button>
					</div>
					<div class="overlay-panel overlay-right">
						<h1>Bentornato su Medictory!</h1>
						<p>Non hai ancora un account? Registrati subito!</p>
						<button class="ghost" id="signUp">Sign Up</button>
					</div>
				</div>
			</div>
		</div>


		<script>
		const signUpButton = document.getElementById('signUp');
		const signInButton = document.getElementById('signIn');
		const container = document.getElementById('container');
		
		signUpButton.addEventListener('click', () => {
			container.classList.add("right-panel-active");
		});
		
		signInButton.addEventListener('click', () => {
			container.classList.remove("right-panel-active");
		});</script>


	</body>
</html>