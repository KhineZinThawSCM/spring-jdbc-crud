<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Create</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container py-5">
		<h1>Post Create</h1>
		<c:url var="storeAction" value="/posts/store"></c:url>
		<form:form action="${storeAction}" method="POST" modelAttribute="postForm">
			<div class="mb-3">
				<label for="title" class="form-label">Title</label> 
				<form:input type="text" path="title" class="form-control" placeholder="Enter Title" />
				<form:errors path="title" cssClass="text-danger" />
			</div>
			<div class="mb-3">
				<label for="description" class="form-label">Description</label> 
				<form:input type="text" path="description" class="form-control" placeholder="Enter Description" />
				<form:errors path="description" cssClass="text-danger" />
			</div>
			<div class="mb-3">
				<button type="submit" class="btn btn-success">Add New</button>
			</div>
		</form:form>
	</div>
</body>
</html>