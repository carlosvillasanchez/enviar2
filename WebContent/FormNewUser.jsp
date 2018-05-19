<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/FormLogin.css">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<title>Nuevo usuario</title>
</head>
<body>

<div class="container">
	<div class="login-container">
        <div id="output"></div>
        <div class="avatar"></div>
        <div class="form-box">
            <form action="NewUserServlet" method="">
                <input name="email" type="text" placeholder="Inserte aqui su email">
                <input type="text" name="name" placeholder="Inserte aqui el nombre"/>
                <input type="password" name="password" placeholder="Inserte aqui su contraseña">
                <input type="password" name="password2" placeholder="Inserte aqui su contraseña otra vez">
                <button class="btn btn btn-block login" type="submit">Crear</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>