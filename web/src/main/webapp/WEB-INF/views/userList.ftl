<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
        <h3>Users and their roles</h3>
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
            <th scope="col">Role</th>
            <th scope="col">Black List</th>
        </tr>
        </thead>
        <tbody>
        <#list usersRole as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <form action="/userList/${user.username}" method="post">
                        <#if user.blackList??>
                            ${containsInBlackList}
                        <#else>
                            <button class="btn btn-primary" name="${user.username}">Add in black list</button>
                        </#if>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>

