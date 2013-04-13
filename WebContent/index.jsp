<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>URLShortening</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="./js/myscript.js"  type="text/javascript"></script>
</head>
<body>
<div id="container" align="center">
<div id="banner"><h2>URL Shortner</h2></div> 
<form id="form">
	<input type="text" id="longurl" size=100 />
	<input type="button" id="submit" value="Shorten">
</form>
<div id="message"></div>
<p style="color: red"></p>
</div>
</body>
</html>