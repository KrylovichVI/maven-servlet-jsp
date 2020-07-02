<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/search" class="form-inline">
                <input class="form-control" type="search" name="filter" value="${filter?ifExists}" placeholder="Search by tag"/>
                <button class="btn btn-primary ml-2" type="submit">Search</button>
            </form>
        </div>
    </div>

    <div class="card-columns" id="message-list">
    <#list page.content as item>
        <div class="card my-3" data-id="${item.id}">
            <#if item.filename??>
                <img src="${item.filename}" class="card-img-top" width="150px" height="200px"/>
            </#if>
            <div class="m-2">
                <div class="card-body text-dark">
                    <h5 class="card-title">${item.author}</h5>
                    <p class="card-text">${item.bookName}</p>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>
</@c.page>