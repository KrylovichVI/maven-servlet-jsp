<#import "parts/common.ftl" as c>

<@c.page>
    <form action="/registration" method="post">
        <h3>Registration</h3>
        <div class="form-group row ml-4">
            <label for="login" class="mt-2">User Name: </label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="login" name="login">
            </div>
        </div>
        <div class="form-group row ml-4">
            <label for="pass" class="mt-3">Password: </label>
            <div class="col-sm-4">
                <input type="password" class="form-control m-2" id="pass" name="pass">
            </div>
        </div>
        <div class="form-group row ml-4">
            <label for="role">Role: </label>
            <div class="col-sm-1">
                <select id="role" class="form-control" name="role">
                    <option id="admin" name="admin">ADMIN</option>
                    <option selected id="user" name="user">USER</option>
                </select>
            </div>
        </div>
        <div class="form-group row ml-2">
            <button type="submit" class="btn btn-primary">Registration</button>
        </div>
    </form>
</@c.page>
