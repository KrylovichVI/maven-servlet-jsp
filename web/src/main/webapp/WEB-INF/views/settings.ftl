<#import "parts/common.ftl" as c>

<@c.page>
    <h3>User info</h3>
    <div class="container ml-4">
        <label>First Name: </label>
        <p class="font-weight-bold">${userInfo.firstName}</p>
        <label>Last Name: </label>
        <p class="font-weight-bolder">${userInfo.lastName}</p>
        <label>Phone: </label>
        <p class="font-weight-normal">${userInfo.phone}</p>
        <label>Email: </label>
        <p class="font-weight-light">${userInfo.email}</p>
        <label>Username: </label>
        <p class="font-weight-bold">${authUser.username}</p>
        <label>Role: </label>
        <p class="font-weight-normal">${authUser.role}</p>
        <br>
    </div>

    <form action="/settings" method="post">
        <div class="form-group ml-4">
            <div class="form-group col-md-2">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name">
            </div>
            <div class="form-group col-md-2">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name">
            </div>
            <div class="form-group col-md-2">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="exemple@gmail.com">
            </div>
            <div class="form-group col-md-2">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="80291234567">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </div>
    </form>
</@c.page>
