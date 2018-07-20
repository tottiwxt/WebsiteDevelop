<!DOCTYPE html>

<html>

<head>
    <title>Welcome - ken.io</title>
</head>

<body>

<#if user??>
    ${user.name}<br>
<#else>
    noCookie<br>
</#if>
</body>

</html>