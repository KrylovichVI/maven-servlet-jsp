<html>
<head>
    <%@include file="static/bootstrap.jsp"%>
    <%@include file="static/jstl.jsp"%>
</head>
<body>
<%@include file="static/navbar.jsp"%>
<table class="table">
    <h3>Users of Black List</h3>
    <thead>
    <tr>
        <th scope="col">Username</th>
        <th scope="col">Date of block user</th>
        <th scope="col">Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersOfBlackList}" var="user" >
        <tr>
            <td><c:out value="${user.authUser.username}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.dateBlock}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.authUser.role}" default="defaultValue" escapeXml="true"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="${pageContext.request.contextPath}/blackList" method="post">
        <div class="row">
            <div class="container">
                <div class="col ml-2">
                    <label for="userName">Username: </label>
                    <input id="userName" class="form-control w-50 p-3" name="userName" type="text" placeholder="User of black list">
                    <button type="submit" class="btn btn-primary mt-2" >Delete</button>
                </div>
            </div>
        </div>
        <p style="color: red">${errorBlackList}</p>
    </div>
</form>
</body>
</html>
