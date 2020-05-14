<html>
<head>
    <%@include file="static/bootstrap.jsp"%>
    <%@include file="static/jstl.jsp"%>
</head>
<body>
<%@include file="static/navbar.jsp"%>
<table class="table">
    <h3>User Orders</h3>
    <thead>
    <tr>
        <th scope="col">Id_Order</th>
        <th scope="col">Order name</th>
        <th scope="col">Data open order</th>
        <th scope="col">Books</th>
        <th scope="col">Status order</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userOrders}" var="user" >
        <tr>
            <td><c:out value="${user.id}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.name}" default="defaultValue" escapeXml="true"/></td>
            <td><c:out value="${user.dateCreate}" default="defaultValue" escapeXml="true"/></td>
            <td>
                <c:forEach items="${user.bookSet}" var="book">
                    <c:out value="${book.bookName}" default="defaultValue" escapeXml="true"/>
                    <br>
                </c:forEach>
            </td>
            <td><c:out value="${user.status}" default="defaultValue" escapeXml="true"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <form action="${pageContext.request.contextPath}/userOrders" method="post">
        <div class="form-group ml-4">
            <h3>Create order</h3>
            <div class="form-group col-md-2">
                <label for="orderName">Order name</label>
                <input type="text" class="form-control" id="orderName" name="orderName">
            </div>
            <div class="form-group col-md-2">
                <label for="multiSelect">Add books</label>
                <select name="bookId" multiple class="custom-select" id="multiSelect">
                        <c:forEach items="${bookList}" var="book">
                            <option value="${book.id}">${book.bookName}</option>
                        </c:forEach>
                </select>
            </div>
            <br>
            <button type="submit" class=" form-group btn btn-primary mt-2">Add order</button>
        </div>
    </form>
</body>
</html>
