<html>
<head>
    <%@include file="static/bootstrap.jsp"%>
    <%@include file="static/jstl.jsp"%>
</head>
<body>
<%@include file="static/navbar.jsp"%>

<h3>User info</h3>
        <div class="container ml-4">
            <label>First Name: </label>
            <p class="font-weight-bold"> <c:out value="${userInfo.firstName}" default="defaultValue" escapeXml="true"/></p>
            <label>Last Name: </label>
            <p class="font-weight-bolder"> <c:out value="${userInfo.lastName}" default="defaultValue" escapeXml="true"/></p>
            <label>Phone: </label>
            <p class="font-weight-normal"> <c:out value="${userInfo.phone}" default="defaultValue" escapeXml="true"/></p>
            <label>Email: </label>
            <p class="font-weight-light"><c:out value="${userInfo.email}" default="defaultValue" escapeXml="true"/></p>
            <label>Username: </label>
            <p class="font-weight-bold"><c:out value="${authUser.username}" default="defaultValue" escapeXml="true"/></p>
            <label>Role: </label>
            <p class="font-weight-normal"><c:out value="${authUser.role}" default="defaultValue" escapeXml="true"/></p>
            <br>
        </div>

<form action="${pageContext.request.contextPath}/settings" method="post">
    <div class="form-group ml-4">
        <div class="form-group col-md-2">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName">
        </div>
        <div class="form-group col-md-2">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName">
        </div>
        <div class="form-group col-md-2">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" name="email">
        </div>
        <div class="form-group col-md-2">
            <label for="phone">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </div>
</form>

</body>
</html>