<html>
    <head>
        <%@include file="static/bootstrap.jsp"%>
    </head>
<body>
<%@include file="static/navbar.jsp"%>
<c:if test="${authUser.role.name() eq 'ADMIN'}">
    <h3>Add Book in Library</h3>
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
</c:if>

<h3>Book in Library</h3>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/page?page=${currentPage - 1}">Previous</a>
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
                                    <a  href="${pageContext.request.contextPath}/page?page=${pgs}" class="page-link">${pgs}</a>
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
                        <a href="${pageContext.request.contextPath}/page?page=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>

<c:if test="${booksList != null}">
<div class="row row-cols-1 row-cols-md-3">
    <c:forEach items="${booksList}" var="item">
        <div class="col mb-4">
            <div class="card h-100">
                <div class="card-body text-dark">
                    <h5 class="card-title">${item.author}</h5>
                    <p class="card-text">${item.bookName}</p>
                    <c:if test="${authUser.role.name() eq 'ADMIN'}">
                        <form action="${pageContext.request.contextPath}/deleteBook" method="post">
                            <button class="btn btn-primary" name="${item.id}">Delete</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</c:if>
</body>
</html>


