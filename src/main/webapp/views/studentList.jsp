<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html  xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="ISO-8859-1">
    <title> Video List</title>

    <!-- JQuery to provide plugins to change looks of table -->
    <link  href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

    <script>
          function deleteConfirm(){
                return confirm("Are you sure , You want to Delete?");
           }

           $(document).ready(function(){
              $('#studLstTbl').DataTable({
                 "pagingType":"full_numbers"
               });
            });

    </script>


</head>
<body  >
<h1 style="color:red;text-align:center"> Student List</h1>

<c:choose>
    <c:when test="${!empty studentList}">
        <table align="center" bgcolor="cyan" border="1"  id="studLstTbl">
            <thead style="color:maroon">
            <tr>
                <th>SID</th> <th>Student Name</th> <th> Username</th> <th>Mobile No</th><th>Created Date</th> <th> Status</th><th>Action</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="stud" items="${studentList}" varStatus="index" >
                    <tr>
                        <td>${index.count}</td>
                        <td>${stud.sname}</td>
                        <td>${stud.username}</td>
                        <td>${stud.mobileNo}</td>
                        <td>${stud.createdDate}</td>
                        <td>${stud.status}</td>
                        <td>
                            <a href="studentList/editStudentById?sid=${stud.sid}">Edit</a>
                            <a href="studentList/deleteStudentById?sid=${stud.sid}" onclick="confirm('Do u want to Delete')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:when>

    <c:otherwise>
        <h1 style="color:red;text-align:center"> NO Student Data Found</h1>
    </c:otherwise>
</c:choose>

<br>
<br>

<br>
<center>
    <div style="float:center">

        <a href="classroom" ><b>Change Role</b> </a> <br> <br>
        <a href="loginAsAdmin" ><b>To Admin Login</b> </a> <br> <br>
        <a href="toDashBoard" ><b>Back </b> </a>
    </div>
</center>


</body>
</html>