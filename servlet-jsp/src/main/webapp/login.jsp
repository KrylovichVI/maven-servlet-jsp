<html>
    <head>
        <%@include file="static/bootstrap.jsp"%>
    </head>
    <body>
        <%@include file="static/navbar.jsp"%>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group ml-4">
                <h3>Login</h3>
                <div class="form-group col-md-2">
                    <label for="login">User Name</label>
                    <input type="text" class="form-control" id="login" name="login">
                </div>
                <div class="form-group col-md-2">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Log In</button>
            </div>
            <p style="color: red">${error}</p>
        </form>
    </body>
</html>

