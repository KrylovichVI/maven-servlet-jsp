<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table">
        <h3>Users of Black List</h3>
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Date of block user</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody>
        <#list usersOfBlackList as user>
            <tr>
                <td>${user.authUser.username}</td>
                <td>${user.dateBlock}</td>
                <td>${user.authUser.role}</td>
            </tr>
        </#list>
        </tbody>
    </table>

    <form action="/blackList" method="post">
        <div class="row">
            <div class="container">
                <div class="col ml-2">
                    <label for="userName">Username: </label>
                    <input id="userName" class="form-control w-50 p-3" name="userName" type="text" placeholder="User of black list">
                    <button type="submit" class="btn btn-primary mt-2" >Delete</button>
                </div>
            </div>
        </div>
        <p style="color: red">${errorBlackList}</p>
        </div>
    </form>
    </body>
    </html>
</@c.page>
