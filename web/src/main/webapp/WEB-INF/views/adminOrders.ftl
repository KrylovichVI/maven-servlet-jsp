<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>


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
                <td>${user.order.id}</td>
                <td>${user.order.name}</td>
                <td>${user.username}</td>
                <td>${user.order.dateCreate}</td>
                <td><#if user.order.dateUpdate??>${user.order.dateUpdate}<#else>-</#if></td>
                <td>
                    <#list user.order.bookSet as book>
                        ${book.bookName}
                        <br>
                    </#list>
                </td>
                <td>${user.order.status}</td>
                <td>
                    <form action="/adminOrders/${user.order.id}" method="post" >
                        <button type="submit" class="btn btn-primary" name="${user.order.id}">Delete</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <@p.pager "/adminOrders" currentPage countPage/>

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

