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
              $('#pptLstTbl').DataTable({
                 "pagingType":"full_numbers"
               });
            });

    </script>


</head>
<body  >

<h1 style="color:red;text-align:center" >  PPT List</h1>



<c:choose>
    <c:when test="${!empty pptLists}">
        <table  bgcolor="cyan" border="1" id="pptLstTbl">
            <thead style="color:marron">
            <tr>
               <th> ID</th>   <th>Subject</th> <th> Path</th> <th>Type</th><th>Created Date</th><th>Updated Date</th> <th> Status</th> <th>Action</th>

            </tr>
            </thead>

            <tbody>
                <c:forEach var="ppt" items="${pptLists}" varStatus="index">
                    <tr>
                       <td>${index.count}</td>
                        <td>${ppt.subject}</td>
                        <td>${ppt.path}</td>
                        <td>${ppt.type}</td>
                        <td>${ppt.storedDate}</td>
                        <td>${ppt.updatedDate}</td>
                        <td>${ppt.status}</td>
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
        <h1 style="color:red;text-align:center"> NO PPT's  Found</h1>
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
