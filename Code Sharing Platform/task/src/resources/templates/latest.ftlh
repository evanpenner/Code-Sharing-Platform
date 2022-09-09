<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet"
           target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
  <#list latest as item>
<pre id="code_snippet"><code>${item.code}</code></pre>
<span id="load_date">${item.date}</span>
 </#list>
</body>
</html>