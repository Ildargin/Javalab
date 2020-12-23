<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<body>
<form method="${method}" action="${action}">
    <#list inputs as input>
        <input type="${input.getType()}" name="${input.getName()}" placeholder="${input.getPlaceholder()}">
    </#list>
</form>
</body>
</html>