<%@ page language="java" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h1 style="color:red;text-align:center" xmlns:form="http://www.w3.org/1999/xhtml"> Faculty Sign up - Account Here ! <h1>
    <br>
    <br>

<form:form modelAttribute="faculty">
     <table align="center" bgcolor="pink" border="0">
         <tr>
            <td> <b> Enter Name :: </b> </td>
            <td> <form:input path="fname" /></td>
          </tr>
         <tr>
             <td> <b> Enter User-Name :: </b> </td>
             <td> <form:input path="userName" /></td>
         </tr>
         <tr>
             <td> <b> Enter MobileNo :: </b> </td>
             <td> <form:input path="mobileNo" /></td>
         </tr>
          <tr>
              <td> <b> Enter Subject :: </b> </td>
              <td> <form:input path="subject"/> </td>
          </tr>
          <tr>
              <td> <b> Enter Deptno :: </b></td>
              <td> <form:input path="deptno"/> </td>
          </tr>
          <tr>
              <td> Enter Password ::</td>
              <td> <form:input type="password" path="password" /></td>
          </tr>

         <tr>
             <td> <b> Re-Enter Password :: </b> </td>
             <td> <input type="password"  /></td>
         </tr>

          <tr>
              <td> <button type="reset"> Reset </button></td>
              <td> <input type="submit" value="Create"> </td>
           </tr>

     </table>
</form:form>
<br>
    <br>


    <blink>
        <h4 Style="color:red;text-align:center"> ${resultInfo} </h4>
    </blink>

    <blink>
        <h4 Style="color:green;text-align:center"> ${msgInfo} </h4>
    </blink>



    <center>
        <div style="float:center">

            <a href="classroom" ><h4>Change Role</h4> </a>
            <a href="loginAsAdmin" ><h4>To Admin Login</h4> </a>
            <a href="toDashBoard" ><h4>Back </h4> </a>
        </div>
    </center>