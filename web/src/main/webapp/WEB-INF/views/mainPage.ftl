<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#import "parts/pager.ftl" as p>

<@c.page>
 <#if isAdmin>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add Book in Library
    </a>

    <div class="collapse" id="collapseExample">
        <h3>Add Book in Library</h3>
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="bookName">Book: </label>
                    <input id="bookName" class="form-control" name="bookName" type="text" placeholder="Title of the book">
                </div>
                <div class="form-group">
                    <label for="author">Author: </label>
                    <input id="author" class="form-control" name="author" type="text" placeholder="Name author">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile"/>
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" >Add book</button>
                </div>
            </form>
        </div>
    </div>
 </#if>

    <h3>Book in Library</h3>
    <@p.pager "/book" currentPage countPage/>
<#--    <div class="container mb-4">-->
<#--        <nav aria-label="...">-->
<#--            <ul class="pagination pagination-sm">-->
<#--                <#if currentPage <= 1>-->
<#--                    <li class="page-item disabled">-->
<#--                        <span class="page-link">Previous</span>-->
<#--                    </li>-->
<#--                <#else>-->
<#--                    <li class="page-item">-->
<#--                        <a class="page-link" href="/page?page=${currentPage - 1}">Previous</a>-->
<#--                    </li>-->
<#--                </#if>-->
<#--                <#list 1..countPage as pgs>-->
<#--                    <#if currentPage?? && currentPage == pgs>-->
<#--                        <li class="page-item active" aria-current="page">-->
<#--                            <span class="page-link">${pgs}<span class="sr-only">(current)</span></span>-->
<#--                        </li>-->
<#--                    <#else >-->
<#--                        <li class="page-item">-->
<#--                            <a  href="/book?page=${pgs}" class="page-link">${pgs}</a>-->
<#--                        </li>-->
<#--                    </#if>-->
<#--                </#list>-->
<#--                <#if currentPage gte countPage>-->
<#--                    <li class="page-item disabled">-->
<#--                        <span class="page-link" >Next</span>-->
<#--                    </li>-->
<#--                <#else >-->
<#--                    <li class="page-item">-->
<#--                        <a href="/book?page=${currentPage + 1}" class="page-link">Next</a>-->
<#--                    </li>-->
<#--                </#if>-->
<#--            </ul>-->
<#--        </nav>-->
<#--    </div>-->

    <div class="card-columns" id="message-list">
        <#list booksList as item>
            <div class="card my-3" data-id="${item.id}">
                <#if item.filename??>
                    <img src="/img/${item.filename}" class="card-img-top" />
                </#if>
                <div class="m-2">
                    <div class="card-body text-dark">
                        <h5 class="card-title">${item.author}</h5>
                        <p class="card-text">${item.bookName}</p>
                    <#if isAdmin>
                        <form action="/book/delete/${item.id}" method="post">
                            <button class="btn btn-primary" name="${item.id}">Delete</button>
                        </form>
                    </#if>
                    </div>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>


