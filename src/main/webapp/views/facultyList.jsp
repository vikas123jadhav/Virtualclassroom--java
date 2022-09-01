<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html  xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="ISO-8859-1">
    <title> PPT List</title>

    <!-- JQuery to provide plugins to change looks of table -->
    <link  href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

    <script>
          function deleteConfirm(){
                return confirm("Are you sure , You want to Delete?");
           }

           $(document).ready(function(){
              $('#facLstTbl').DataTable({
                 "pagingType":"full_numbers"
               });
            });

    </script>


</head>
<body  >

<h1 style="color:red;text-align:center"  > Faculty List</h1>


<!--${facultyLists}-->

<c:choose>
    <c:when test="${!empty facultyLists}">
        <table   bgcolor="cyan" border="1" id="facLstTbl">
            <thead style="color:maroon">
            <tr>
                <th>Sr no</th> <th>Faculty Name</th> <th> Username</th> <th>Subject</th> <th>Mobile No</th> <th>DeptNo</th><th>Created Date</th> <th> Status</th><th>Action</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="fac" items="${facultyLists}" varStatus="index">
                    <tr>
                        <td>${index.count}</td>
                        <td>${fac.fname}</td>
                        <td>${fac.userName}</td>
                        <td>${fac.subject}</td>
                        <td>${fac.mobileNo}</td>
                        <td>${fac.deptno}</td>
                        <td>${fac.createdDate}</td>
                        <td>${fac.status}</td>
                        <td>
                            <a href=" ">Edit</a>
                            <a href=" " onclick="confirm('Do u want to Delete')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </c:when>

    <c:otherwise>
        <h1 style="color:red;text-align:center"> NO Faculty Data Found</h1>
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