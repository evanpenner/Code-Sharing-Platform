<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
<link rel="stylesheet"
       target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
<script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<pre id="code_snippet"><code>${code}</code></pre>
<span id="load_date">${date}</span>
<#if time_restriction != 0>
<span id="time_restriction">${time_restriction}</span>
</#if>
<#if has_view_restriction>
<span id="views_restriction">${views_restriction}</span>
</#if>
</body>
</html>