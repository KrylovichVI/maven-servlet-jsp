<html>
<head>
    <%@include file="static/bootstrap.jsp"%>
</head>
<body>
<%@include file="static/navbar.jsp"%>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <h3>Registration</h3>
    <div class="form-group row ml-4">
        <label for="login-reg" class="mt-2">User Name: </label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="login-reg" name="login-reg">
        </div>
    </div>
    <div class="form-group row ml-4">
        <label for="pass-reg" class="mt-3">Password: </label>
        <div class="col-sm-4">
            <input type="pass-reg" class="form-control m-2" id="pass-reg" name="pass-reg">
        </div>
    </div>
    <div class="form-group row ml-4">
        <label for="role-reg">Role: </label>
        <div class="col-sm-1">
            <select id="role-reg" class="form-control" name="role-reg">
                <option id="admin-role" name="admin-role">ADMIN</option>
                <option selected id="user-role" name="user-role">USER</option>
            </select>
        </div>
    </div>
    <div class="form-group row ml-2">
        <button type="submit" class="btn btn-primary">Registration</button>
    </div>
</form>
</body>
</html>
