<html>
<head>
    <%@include file="static/bootstrap.jsp"%>
    <%@include file="static/jstl.jsp"%>
</head>
<body>
    <%@include file="static/navbar.jsp"%>
    <table class="table">
        <h3>Users and their roles</h3>
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersRole}" var="user" >
            <tr>
                <td><c:out value="${user.username}" default="defaultValue" escapeXml="true"/></td>
                <td><c:out value="${user.password}" default="defaultValue" escapeXml="true"/></td>
                <td><c:out value="${user.role}" default="defaultValue" escapeXml="true"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/userList" method="post">
        <div class="row">
            <div class="col ml-2">
                <label for="usrName">Username: </label>
                <input id="usrName" class="form-control" name="usrName" type="text" placeholder="User of black list">
            </div>
            <div class="col ml-2 my-4">
                <button type="submit" class="btn btn-primary" >Add in black list</button>
            </div>
        </div>
        <p style="color: red">${errorUserList}</p>
    </form>
</body>
</html>
