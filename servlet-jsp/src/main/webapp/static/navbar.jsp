<%@include file="jstl.jsp"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">My JSP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${authUser == null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Log In</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a>
            </li>
            <c:if test="${authUser != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/page">Main Page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/settings">Settings</a>
                </li>
            </c:if>
            <c:if test="${authUser != null && authUser.role.name() eq 'USER'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/userOrders">Orders</a>
                </li>
            </c:if>
            <c:if test="${authUser != null && authUser.role.name() eq 'ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/userList">User list</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/blackList">Black list</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/adminOrders">All Orders</a>
               </li>
            </c:if>
        </ul>
            <c:if test="${authUser != null}">
                <div class="navbar-text mr-3">
                    <div class="row">
                        <label class="mt-2">${authUser.username}</label>
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log Out</a>
                    </div>
                </div>
            </c:if>
    </div>
</nav>
