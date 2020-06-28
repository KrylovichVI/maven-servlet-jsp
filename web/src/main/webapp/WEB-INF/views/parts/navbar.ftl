<#include "security.ftl">
<#import "../include/spring.ftl" as spring>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><@spring.message "navbar.title"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !user??>
                <li class="nav-item">
                    <a class="nav-link" href="/login"><@spring.message "navbar.login"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration"><@spring.message "navbar.registration"/></a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/book"><@spring.message "navbar.mainPage"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/settings"><@spring.message "navbar.settings"/></a>
                </li>
            </#if>
            <#if user?? && !isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/userOrders"><@spring.message "navbar.orders"/></a>
                </li>
            </#if>
            <#if user?? && isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/userList"><@spring.message "navbar.userList"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blackList"><@spring.message "navbar.blackList"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/adminOrders"><@spring.message "navbar.allOrders"/></a>
               </li>
            </#if>
        </ul>
            <#if name != 'unknown'>
                <div class="navbar-text mr-3">
                    <div class="row">
                        <label class="mt-2">${name}</label>
                        <a class="nav-link" href="/logout"><@spring.message "navbar.logout"/></a>
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
