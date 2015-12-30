<#macro layout title>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>${title}</title>
  </head>
  <body>
    <h1><a href='/'>My lists</a></h1>
    <#nested>
  </body>
</html>
</#macro>