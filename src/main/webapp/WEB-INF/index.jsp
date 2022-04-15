<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title></title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container w-100 h-100">
		<h1>Register</h1>
		<div class="container w-25 border border-secondary p-3">
			<form:form action="/register" method="post" modelAttribute="newUser">
				<p>
					<form:label path="userName">Username</form:label>
					<form:errors path="userName" class="text-danger"/>
					<form:input path="userName" />
				</p>
				<p>
					<form:label path="email">Email</form:label>
					<form:errors path="email" class="text-danger"/>
					<form:input path="email" />
				</p>
				<p>
					<form:label path="password">Password</form:label>
					<form:errors path="password" class="text-danger"/>
					<form:input path="password" />
				</p>
				<p>
					<form:label path="confirm">Confirm PW</form:label>
					<form:errors path="confirm" class="text-danger" />
					<form:input path="confirm" />
				</p>
				<input type="submit" value="Register" />
			</form:form>
		</div>
		<h1>Login</h1>
		<div class="container w-25 border border-secondary p-3">
			<form:form action="/login" method="post" modelAttribute="newLogin">
				<p>
					<form:label path="email">Email</form:label>
					<form:errors path="email" class="text-danger"/>
					<form:input path="email" />
				</p>
				<p>
					<form:label path="password">Password</form:label>
					<form:errors path="password" class="text-danger"/>
					<form:input path="password" />
				</p>
				<input type="submit" value="Login" />
			</form:form>
		</div>
	</div>
</body>
</html>