<#macro pager url currentPage countPage>
    <div class="container mb-4">
        <nav aria-label="...">
            <ul class="pagination pagination-sm">
                <#if currentPage <= 1>
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${currentPage - 1}">Previous</a>
                    </li>
                </#if>
                <#list 1..countPage as pgs>
                    <#if currentPage?? && currentPage == pgs>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${pgs}<span class="sr-only">(current)</span></span>
                        </li>
                    <#else >
                        <li class="page-item">
                            <a  href="${url}?page=${pgs}" class="page-link">${pgs}</a>
                        </li>
                    </#if>
                </#list>
                <#if currentPage gte countPage>
                    <li class="page-item disabled">
                        <span class="page-link" >Next</span>
                    </li>
                <#else >
                    <li class="page-item">
                        <a href="${url}?page=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
</#macro>

