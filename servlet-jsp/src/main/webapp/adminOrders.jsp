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
        <th scope="col">Order books</th>
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
            <td>
                <c:forEach items="${user.bookSet}" var="book">
                    <c:out value="${book.bookName}" default="defaultValue" escapeXml="true"/>
                    <br>
                </c:forEach>
            </td>
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
<div class="container mb-4">
    <nav aria-label="...">
        <ul class="pagination pagination-sm">
            <c:choose>
                <c:when test="${currentPage <= 1}">
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/adminOrders?page=${currentPage - 1}">Previous</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach begin="1" end="${countPage}" var="pgs">
                <c:choose>
                    <c:when test="${currentPage != null && currentPage == pgs}">
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${pgs}<span class="sr-only">(current)</span></span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a  href="${pageContext.request.contextPath}/adminOrders?page=${pgs}" class="page-link">${pgs}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage >= countPage}">
                    <li class="page-item disabled">
                        <span class="page-link" >Next</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a href="${pageContext.request.contextPath}/adminOrders?page=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>
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
