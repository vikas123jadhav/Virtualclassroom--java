<%@ page language="java" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns:form="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html" lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Login</title>


    <link href="<c:url value="/../resources/css/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

    <script>
        const togglePassword = document.querySelector('#togglePassword');
  const password = document.querySelector('#id_password');

  togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});
    </script>
</head>
<body  >

<center>
    <h1> Admin Login here </h1>

    <blink>
        <h4 Style="color:red;text-align:center"> ${resultLogin} </h4>
    </blink>

    
    <div class="container">
        <div class="main">

            <form:form modelAttribute="admin">
<!--                <h2  align="center" style="color:red" > Admin Login </h2>-->
                <h2 align="center"> <img src="../caffe%20logo2.png" class="logo" > </h2>

                <table cellspacing="2" align="center" cellpadding="8"   border="0"  >
                        <tr>
                            <td style="color:black ;">UserName  </td>
                            <td> <form:input path="username" class="tb"/></td>
                        </tr>

                        <tr>
                            <td style="color:black">Password  </td>
                            <td>
                                <form:input type="password" path="password" name="password" autocomplete="current-password" required="" id="id_password"/>
                                <i class="far fa-eye" id="togglePassword" style="margin-left: -30px; cursor: pointer; color:black"></i>
                            </td>
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



</center>
</body>
</html>