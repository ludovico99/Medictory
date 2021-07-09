<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
 <!-- meta charset -->
        <meta charset="utf-8">
        <!-- site title -->
        <title>Medictory | Error Page</title>
        <!-- meta description -->
        <meta name="description" content="">
        <!-- mobile viwport meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/new.css">
      
</head>
<body>
	<div class="container myError" id="container">
		
		<h1 style="color: #FFFFFF">ERRORE</h1>
		<h2 style="color: #FFFFFF"><%=request.getParameter("errore") %></h2>
	</div>
</body>
</html>