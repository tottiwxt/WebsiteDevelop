<!DOCTYPE html>

<html>

<head>
    <title>Welcome - ken.io</title>
</head>

<body>


<#if user??>
        ${user.name}<br>
 <#else>
        nobody<br>
 </#if>
</body>

</html>