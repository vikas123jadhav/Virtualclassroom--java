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
              $('#videoLstTbl').DataTable({
                 "pagingType":"full_numbers"
               });
            });

    </script>


</head>
<body  >


<h2 style="color:red;text-align:center">  Video List</h2>


<c:choose>
    <c:when test="${!empty videoLists}">
        <table  bgcolor="cyan"  id="videoLstTbl" border="1">
            <thead style="color:maroon">
            <tr>
                <th>Sr no</th> <th>Subject</th> <th> Path</th> <th>Type</th><th>Created Date</th><th>Updated Date</th> <th> Status</th> <th>Action</th>
            </tr>
            </thead>

            <tbody>
                <c:forEach var="vid" items="${videoLists}" varStatus="index">
                    <tr>
                        <td>${index.count}</td>
                        <td>${vid.subject}</td>
                        <td>${vid.path}</td>
                        <td>${vid.type}</td>
                        <td>${vid.storedDate}</td>
                        <td>${vid.updatedDate}</td>
                        <td>${vid.status}</td>
                        <td>
                            <a href=" ">Edit</a>
                            <a href=" " onclick="deleteConfirm()">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>

    <c:otherwise>
        <h1 style="color:red;text-align:center"> NO Videos Found</h1>
    </c:otherwise>
</c:choose>


<br>
<br>
<br>

<blink>
    <h1 style="color:green;text-align:center"> ${resultInfo}</h1>
</blink>

<center>
    <div style="float:center">

        <a href="classroom" ><b>Change Role</b> </a> <br> <br>
        <a href="loginAsAdmin" ><b>To Admin Login</b> </a> <br> <br>
        <a href="toDashBoard" ><b>Back </b> </a>
    </div>
</center>

</body>
</html>
