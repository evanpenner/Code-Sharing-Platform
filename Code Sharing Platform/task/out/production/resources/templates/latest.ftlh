<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
</head>
<body>
  <#list latest as item>
<pre id="code_snippet">${item.code}</pre>
<span id="load_date">${item.date}</span>
 </#list>
</body>
</html>