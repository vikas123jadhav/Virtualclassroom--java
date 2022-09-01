<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html  xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="ISO-8859-1">
    <title> Admin List</title>

    <!-- JQuery to provide plugins to change looks of table -->
    <link  href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

    <script>
          function deleteConfirm(){
                return confirm("Are you sure , You want to Delete?");
           }

           $(document).ready(function(){
              $('#adminLstTbl').DataTable({
                 "pagingType":"full_numbers"
               });
            });

    </script>


</head>
<body  >
<h1 style="color:red;text-align:center" > Admin List</h1>

<c:choose>
    <c:when test="${!empty adminList}">
        <table  bgcolor="cyan" border="1" id="adminLstTbl">
            <thead style="color:maroon">
            <tr>
                <th>Sr no</th> <th>Admin Name</th> <th> Username</th> <th>Mobile No</th><th>Created Date</th> <th> Status</th><th>Action</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="admin" items="${adminList}" varStatus="index">
                    <tr>
                        <td>${index.count}</td>
                        <td>${admin.name}</td>
                        <td>${admin.username}</td>
                        <td>${admin.mobileNo}</td>
                        <td>${admin.createdDate}</td>
                        <td>${admin.status}</td>
                        <td>
                            <a href="adminList/editAdminById?aid=${admin.aid}">Edit</a>
                            <a href="adminList/deleteAdminById?aid=${admin.aid}" onclick="confirm('Do u want to Delete')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </c:when>

    <c:otherwise>
        <h1 style="color:red;text-align:center"> NO Admin Data Found</h1>
    </c:otherwise>
</c:choose>

    <blink>
        <h1 style="color:green;text-align:center"> ${resultInfo}</h1>
    </blink>

<br>
<br>
<br>
<center>
    <div style="float:center">

        <a href="classroom" ><h4>Change Role</h4> </a>
        <a href="loginAsAdmin" ><h4>To Admin Login</h4> </a>
        <a href="toDashBoard" ><h4>Back </h4> </a>
    </div>
</center>
