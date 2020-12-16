<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<body>
<form method="${method}" action="${action}">
    <#list inputs as input>
        <input type="${input.type}" name="${input.name}" placeholder="${input.placeholder}">
    </#list>
</form>
</body>
</html>