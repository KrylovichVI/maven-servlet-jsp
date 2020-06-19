<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
        <h3>Users and their roles</h3>
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody>
        <#list usersRole as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/userList" method="post">
        <div class="row">
            <div class="col ml-2">
                <label for="usrName">Username: </label>
                <input id="usrName" class="form-control" name="usrName" type="text" placeholder="User of black list">
            </div>
            <div class="col ml-2 my-4">
                <button type="submit" class="btn btn-primary" >Add in black list</button>
            </div>
        </div>
        <p style="color: red">${errorUserList}</p>
    </form>
</@c.page>

