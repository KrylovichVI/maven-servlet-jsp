<#import "parts/common.ftl" as c>

<@c.page>
<#--    <#if authUser.role.name() == 'ADMIN'>-->
<#--        <h3>Add Book in Library</h3>-->
<#--        <form action="/page" method="post">-->
<#--            <div class="row">-->
<#--                <div class="col ml-2">-->
<#--                    <label for="bookName">Book: </label>-->
<#--                    <input id="bookName" class="form-control" name="bookName" type="text" placeholder="Title of the book">-->
<#--                </div>-->
<#--                <div class="col ml-2">-->
<#--                    <label for="author">Author: </label>-->
<#--                    <input id="author" class="form-control" name="author" type="text" placeholder="Name author">-->
<#--                </div>-->
<#--                <div class="col my-4">-->
<#--                    <button type="submit" class="btn btn-primary" >Add book</button>-->
<#--                </div>-->
<#--            </div>-->
<#--        </form>-->
<#--    </#if>-->

    <h3>Book in Library</h3>
    <div class="container mb-4">
        <nav aria-label="...">
            <ul class="pagination pagination-sm">
                <#if currentPage <= 1>
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="/page?page=${currentPage - 1}">Previous</a>
                    </li>
                </#if>
                <#list 1..countPage as pgs>
                    <#if currentPage?? && currentPage == pgs>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${pgs}<span class="sr-only">(current)</span></span>
                        </li>
                    <#else >
                        <li class="page-item">
                            <a  href="/page?page=${pgs}" class="page-link">${pgs}</a>
                        </li>
                    </#if>
                </#list>
                <#if currentPage gte countPage>
                    <li class="page-item disabled">
                        <span class="page-link" >Next</span>
                    </li>
                <#else >
                    <li class="page-item">
                        <a href="/page?page=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>

    <#if booksList??>
        <div class="row row-cols-1 row-cols-md-3">
            <#list booksList as item>
                <div class="col mb-4">
                    <div class="card h-100">
                        <div class="card-body text-dark">
                            <h5 class="card-title">${item.author}</h5>
                            <p class="card-text">${item.bookName}</p>
<#--                            <#if authUser.role.name() == 'ADMIN'>-->
<#--                                <form action="/deleteBook" method="post">-->
<#--                                    <button class="btn btn-primary" name="${item.id}">Delete</button>-->
<#--                                </form>-->
<#--                            </#if>-->
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</@c.page>


