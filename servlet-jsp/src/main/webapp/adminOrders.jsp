<html>
<head>
    <%@include file="static/bootstrap.jsp"%>
    <%@include file="static/jstl.jsp"%>
</head>
<body>
<%@include file="static/navbar.jsp"%>
<table class="table">
    <h3>Orders all Users</h3>
    <thead>
    <tr>
        <th scope="col">Id_Order</th>
        <th scope="col">Order name</th>
        <th scope="col">Username</th>
        <th scope="col">Data open order</th>
        <th scope="col">Data update Status</th>
        <th scope="col">Status order</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersOrders}" var="user" >
        <tr>
            <td><c:out value="${user.id}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.name}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.authUser.username}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.dateCreate}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.dateUpdate}" default="-" escapeXml="true"/></td>
            <td><c:out value="${user.status}" default="defaultValue" escapeXml="true"/></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteOrderServlet" method="post" >
                    <button type="submit" class="btn btn-primary" name="${user.id}">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <form action="${pageContext.request.contextPath}/adminOrders" method="post">
        <div class="form-group ml-4">
            <h3>Update Status Order</h3>
            <div class="form-group col-md-2">
                <label for="idName">Order id</label>
                <input type="number" class="form-control" id="idName" name="idName">
            </div>
            <div class="form-group col-md-2">
                <label for="status-order">Role: </label>
                <select id="status-order" class="form-control" name="status-order">
                    <option id="processing-role" name="processing-role">IN_PROCESSING</option>
                    <option id="confirmed-role" name="confirmed-role">CONFIRMED</option>
                    <option id="canceled-role" name="canceled-role">CANCELED</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
            <c:if test="${idError}">
                <p style="color: red">Error id</p>
            </c:if>
        </div>
    </form>
</body>
</html>
