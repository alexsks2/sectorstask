<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>

    <input type="button" id="fillButton" value="Fill"
           onclick="window.location.href='list'; this.disabled = true"
           class="add-button"
    />

    <h3>${message}</h3>
    <br>
    Name: <input type="text" required placeholder="Name is required">
    <br>
    <br>

    <div id="wrapper">
        <h2>Sectors:</h2>
    </div>

    <select multiple="" size="5">
        <c:forEach var="tempSector" items="${sectorList}">
            <option value="${tempSector.id}">${tempSector.name}</option>
        </c:forEach>
    </select>

    <br>
    <br>
    <input type="checkbox"> Agree to terms

    <br>
    <br>
    <input type="submit" value="Save">

</body>
</html>