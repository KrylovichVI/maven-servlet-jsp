<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
        <h3>Users of Black List</h3>
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Date of block user</th>
            <th scope="col">Role</th>
            <th scope="col">Delete User of Black List</th>
        </tr>
        </thead>
        <tbody>
        <#list usersOfBlackList as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.dateBlock}</td>
                <td>${user.role.name()}</td>
                <td>
                    <form action="/blackList/${user.authUserId}" method="post">
                        <button class="btn btn-primary" name="${user.authUserId}">Delete in list</button>
                    </form>
                </td>
            </tr>
        <#else>
            Black List is empty
        </#list>
        </tbody>
    </table>
</@c.page>
