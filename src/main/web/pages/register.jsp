<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="form" type="study.web.RegistrationForm" scope="request" /> <!-- scope определяет к чему привзка -->
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form action="register" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            <label>
                Login:
                <input type="text" name="login" value="${form.login}">
            </label>
        </p>
        <p>
            <label>
                Password:
                <input type="password" name="password" value="${form.password}">
            </label>
        </p>
        <p>
            <label>
                TelephoneNumber:
                <input type="text" name="telephoneNumber" value="${form.telephoneNumber}">
            </label>
        </p>
        <p>
            <label>
                HouseGroupAddress:
                <select name="houseGroup">
                    <c:forEach items="${form.houseGroups}" var="houseGroup">
                        <option value=${houseGroup.houseAddress}>${houseGroup.houseAddress}</option>

                    </c:forEach>
                </select>
            </label>
        </p>
        <p>
            <input type="submit">
        </p>
    </form>

</body>
</html>
