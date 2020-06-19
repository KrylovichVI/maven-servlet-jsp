<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
        <h3>User Orders</h3>
        <thead>
        <tr>
            <th scope="col">Id_Order</th>
            <th scope="col">Order name</th>
            <th scope="col">Data open order</th>
            <th scope="col">Books</th>
            <th scope="col">Status order</th>
        </tr>
        </thead>
        <tbody>
        <#list userOrders as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.dateCreate}</td>
                <td>
                    <#list user.bookSet as book>
                        ${book.bookName}
                        <br>
                    </#list>
                </td>
                <td>${user.status}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/userOrders" method="post">
        <div class="form-group ml-4">
            <h3>Create order</h3>
            <div class="form-group col-md-2">
                <label for="orderName">Order name</label>
                <input type="text" class="form-control" id="orderName" name="orderName">
            </div>
            <div class="form-group col-md-2">
                <label for="multiSelect">Add books</label>
                <select name="bookId" multiple class="custom-select" id="multiSelect">
                    <#list bookList as book>
                        <option value="${book.id}">${book.bookName}</option>
                    </#list>
                </select>
            </div>
            <br>
            <button type="submit" class=" form-group btn btn-primary mt-2">Add order</button>
        </div>
    </form>
</@c.page>
