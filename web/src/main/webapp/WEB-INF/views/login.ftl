<#import "parts/common.ftl" as c>

<@c.page>
<form action="/login" method="post">
    <div class="form-group ml-4">
        <h3>Login</h3>
        <div class="form-group col-md-2">
            <label for="login">User Name</label>
            <input type="text" class="form-control" id="login" name="login">
        </div>
        <div class="form-group col-md-2">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Log In</button>
    </div>
    <#if error??>
        <p style="color: red">${error}</p>
    </#if>
</form>
</@c.page>
