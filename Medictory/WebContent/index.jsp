<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    

<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
 <!-- meta charset -->
        <meta charset="utf-8">
        <!-- site title -->
        <title>Medictory | Welcome</title>
        <!-- meta description -->
        <meta name="description" content="">
        <!-- mobile viwport meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/new.css">

</head>

    <body>

<div class="container" id="container">

	<div class="form-container sign-in-container">
			<div class="myDiv">
			<h1>Customer side</h1>
			<p>Procedi qui per accedere a Medictory come cliente</p>
			<button id= "signInCustomer" onclick="location.href = 'CustomerLogin.jsp';">Continua ...</button>
			</div>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-right">
				<h1>Pharmacy side</h1>
				<p>Procedi qui per accedere a Medictory come farmacia</p>
				<button class="ghost" id="signIn" onclick="location.href = 'PharmacyLogin.jsp';">Continua ...</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>




