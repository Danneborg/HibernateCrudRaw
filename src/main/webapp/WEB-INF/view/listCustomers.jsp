<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link type="text/css" href="<c:url value="/resources/css/listCustomer.css" />" rel="stylesheet">
    <title>Customers</title>
</head>
<body>
<div class="container d-flex flex-column justify-content-center">

    <div class="p-2">
        <div class="d-flex flex-column justify-content-center">
            <div class="p-2"><h1>List of customers</h1></div>
            <div class="p-2"><a id='addCustomer' href="showAddForm" type="button" class="btn btn-light rounded m-2">Add
                new customer</a></div>
            <div class="p-2"><!--  add a search box -->
                <form:form action="search" method="GET">
                    Search customer: <input type="text" name="searchName"/>
                    <input type="submit" value="Search" class="add-button"/>
                </form:form>
            </div>
        </div>
    </div>
    <div class="p-2">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tempCustomer" items="${customers}">

                <!-- constructing a update link -->
                <c:url var="updateLink" value="/customer/showFormUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <!-- constructing a delete link -->
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>
                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update</a>
                        |
                        <a class="deleteLink" href="${deleteLink}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</div>


<script src="<c:url value="/resources/js/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/popper.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/listCustomer.js" />" type="text/javascript"></script>
</body>
</html>