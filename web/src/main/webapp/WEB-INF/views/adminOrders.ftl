<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
        <h3>Orders all Users</h3>
        <thead>
        <tr>
            <th scope="col">Id_Order</th>
            <th scope="col">Order name</th>
            <th scope="col">Username</th>
            <th scope="col">Data open order</th>
            <th scope="col">Data update Status</th>
            <th scope="col">Order books</th>
            <th scope="col">Status order</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <#list usersOrders as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.authUser.username}</td>
                <td>${user.dateCreate}</td>
                <td>${user.dateUpdate}</td>
                <td>
                    <#list user.bookSet as book>
                        ${book.bookName}
                        <br>
                    </#list>
                </td>
                <td>${user.status}</td>
                <td>
                    <form action="/deleteOrderServlet" method="post" >
                        <button type="submit" class="btn btn-primary" name="${user.id}">Delete</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <div class="container mb-4">
        <nav aria-label="...">
            <ul class="pagination pagination-sm">
                <#if currentPage <= 1>
                        <li class="page-item disabled">
                            <span class="page-link">Previous</span>
                        </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="/adminOrders?page=${currentPage - 1}">Previous</a>
                    </li>
                </#if>
                <#list 1..countPage as pgs>
                        <#if currentPage != null && currentPage == pgs>
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${pgs}<span class="sr-only">(current)</span></span>
                            </li>
                        <#else >
                            <li class="page-item">
                                <a  href="/adminOrders?page=${pgs}" class="page-link">${pgs}</a>
                            </li>
                        </#if>
                </#list>
                <#if currentPage >= countPage>
                    <li class="page-item disabled">
                        <span class="page-link" >Next</span>
                    </li>
                <#else>
                    <li class="page-item">
                        <a href="/adminOrders?page=${currentPage + 1}" class="page-link">Next</a>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
    <form action="/adminOrders" method="post">
        <div class="form-group ml-4">
            <h3>Update Status Order</h3>
            <div class="form-group col-md-2">
                <label for="idName">Order id</label>
                <input type="number" class="form-control" id="idName" name="idName">
            </div>
            <div class="form-group col-md-2">
                <label for="status-order">Role: </label>
                <select id="status-order" class="form-control" name="status-order">
                    <option id="processing-role" name="processing-role">IN_PROCESSING</option>
                    <option id="confirmed-role" name="confirmed-role">CONFIRMED</option>
                    <option id="canceled-role" name="canceled-role">CANCELED</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
            <#if idError??>
                <p style="color: red">Error id</p>
            </#if>
        </div>
    </form>
</@c.page>

