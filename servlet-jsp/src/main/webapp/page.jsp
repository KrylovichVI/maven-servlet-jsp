<html>
    <head>
        <%@include file="static/bootstrap.jsp"%>
    </head>
<body>
<%@include file="static/navbar.jsp"%>
<h3>Form Book</h3>
<form action="${pageContext.request.contextPath}/page" method="post">
    <div class="row">
        <div class="col ml-2">
            <label for="bookName">Book: </label>
            <input id="bookName" class="form-control" name="bookName" type="text" placeholder="Title of the book">
        </div>
        <div class="col ml-2">
            <label for="author">Author: </label>
            <input id="author" class="form-control" name="author" type="text" placeholder="Name author">
        </div>
        <div class="col my-4">
            <button type="submit" class="btn btn-primary" >Add book</button>
        </div>
    </div>
</form>


<c:if test="${booksList != null}">
<div class="row row-cols-1 row-cols-md-3">
    <c:forEach items="${booksList}" var="item">
        <div class="col mb-4">
            <div class="card h-100">
                <div class="card-body text-dark">
                    <h5 class="card-title">${item.author}</h5>
                    <p class="card-text">${item.bookName}</p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</c:if>

<br/>
</body>
</html>


