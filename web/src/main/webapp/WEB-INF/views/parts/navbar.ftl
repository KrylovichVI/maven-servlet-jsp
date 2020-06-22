<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">My JSP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !authUser??>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Log In</a>
                </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link" href="/registration">Registration</a>
            </li>
            <#if authUser??>
                <li class="nav-item">
                    <a class="nav-link" href="/page">Main Page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/settings">Settings</a>
                </li>
            </#if>
<#--            <#if authUser != null && authUser.role.name() == 'USER'>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/userOrders">Orders</a>-->
<#--                </li>-->
<#--            </#if>-->
<#--            <#if authUser != null && authUser.role.name() == 'ADMIN'>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/userList">User list</a>-->
<#--                </li>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/blackList">Black list</a>-->
<#--                </li>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/adminOrders">All Orders</a>-->
<#--               </li>-->
<#--            </#if>-->
        </ul>
            <#if name != 'unknown'>
                <div class="navbar-text mr-3">
                    <div class="row">
                        <label class="mt-2">${name}</label>
                        <a class="nav-link" href="/logout">Log Out</a>
                    </div>
                </div>
            <#else>
                <div class="navbar-text mr-3">
                    <div class="row">
                        <label class="mt-2">${name}</label>
                    </div>
                </div>
            </#if>
    </div>
</nav>
