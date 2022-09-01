<%@ page language="java" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html  xmlns:form="http://www.w3.org/1999/xhtml"  lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Login</title>

    <link rel="stylesheet" href="./cs_files/style.css">
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet" href="/cs_files/style.css">
</head>
<body  >

<center>
    <div class="container">
        <div class="main"></div>

            <form:form modelAttribute="admin">
<!--                <h2  align="center" style="color:red" > Admin Login </h2>-->
                <h2 align="center"> <img src="../caffe%20logo2.png" class="logo" > </h2>

                <table cellspacing="2" align="center" cellpadding="8"   border="0"  >
                        <tr>
                            <td style="color:black"> <b> Admin UserName :: </b> </td>
                            <td> <form:input path="username" class="tb"/></td>
                        </tr>

                        <tr>
                            <td style="color:black"> <b> Admin Password :: </b> </td>
                            <td> <form:input type="password" path="password" /></td>
                        </tr>

                        <tr>
                            <td></td>

                            <td>
                                <input type="reset" value="Reset" class="btn" id="reset" />
                                <input type="submit" value="Login" name="login" class="btn" id="login"> </td>
                            </td>
                        </tr>

                    </table>
            </form:form>
        </div>
    </div>

    <br>
    <br>

    <a href="classroom" ><h4>Change Role</h4> </a>

<blink>
        <h4 Style="color:red;text-align:center"> ${resultLogin} </h4>
</blink>

</center>
</body>
</html>