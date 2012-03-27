<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<%-- CSS --%>
<spring:url value="/css/jquery.tooltip.css" var="jQTooltipCssUrl" />
<spring:url value="/css/my.css" var="myCssUrl" />

<%-- JS --%>
<spring:url value="/js/jquery.bigframe.js" var="jQBigFrameUrl" />
<spring:url value="/js/jquery.dimensions.js" var="jQDimensionsUrl" />
<spring:url value="/js/jquery.tooltip.js" var="jQTooltipUrl"/>

<html>
<head>
    <link rel="stylesheet" href="${jQTooltipCssUrl}" type="text/css"/>
    <link rel="stylesheet" href="${myCssUrl}" type="text/css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="${jQBigFrameUrl}"></script>
    <script type="text/javascript" src="${jQDimensionsUrl}"></script>
    <script type="text/javascript" src="${jQTooltipUrl}"></script>

    <title>GraphViz</title>
</head>
<body>

<%--<div id="distance"></div>--%>
<div id="content">
    <spring:url value="/graph-${chart.id}.png" var="graphImage"/>
    <img src="${graphImage}" alt="${chart.name}" usemap="#chMap" />
</div>

${imagemap}

<script type="text/javascript">
    $("#chMap *").tooltip({
        track: true,
        delay: 0,
        showURL: false,
        showBody: " - ",
        fade: 250
    });
</script>

</body>
</html>