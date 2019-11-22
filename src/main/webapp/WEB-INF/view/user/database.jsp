<%@ page import="dao.DaoFactory" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.PersonDao" %>
<%@ page import="util.DbPool" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%--<link href="<%=request.getContextPath()%>/css/githubstyle.css" rel="stylesheet">--%>
<!DOCTYPE html>
<html>
<style>
    th {
        font-weight: bold;
    }
</style>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js">
    var currentPage = 0;
</script>
<meta http-equiv="refresh" content="60">

<link href="${pageContext.request.contextPath}/css/page.css" rel="stylesheet">
<title>Database Operation System</title>
<body>
<div class="mid">
    <h1>Database Operation System</h1>
    <%--<div id="database_info" style="color: white; text-align: center">--%>
    <%--link:<input type="text"/> port: <input type="text"> database: <input type="text">--%>
    <%--<br/><br/><br/><br/>--%>
    <%--</div>--%>

    <div id="queryBlock" style="width: 300px ; position: relative ;margin: 0 auto 5% auto">
        <button value="" id="back"
                style="width: 300px; height:50px; font-family:KaiTi; font-size:20px;color: blue; margin: 0 auto; ">返回
        </button>
    </div>
    <div class="main_panel" style="">
        <div class="tab">
            <div class="tab_list">
                <ul>
                    <li id="persontag" class="active">Person</li>
                    <li id="usertag">User</li>
                </ul>
            </div>
        </div>
        　　　
        <%
            System.out.println("start");
            String sql = "select table_name from information_schema.tables where table_schema='lab2';";
            try (Connection connection = DbPool.getConnection()) {
                try (Statement statement = connection.createStatement()) {
                    ArrayList<String> tables = new ArrayList<>();
                    try (ResultSet tableResultSet = statement.executeQuery(sql)) {
                        while (tableResultSet.next()) {
                            tables.add(tableResultSet.getString(1));
                        }
                    }
                    int tbCount = 0;
                    for (String currTable : tables) {
                        System.out.println("debug: pending: " + currTable);
                        String style;
                        if (tbCount == 0)
                            style = "block";
                        else style = "none";
                        out.println("<div id='table_" + (tbCount++) + "' class='database-item2' style='display:" + style + ";'>");
                        out.println("<h2>" + currTable + "</h2>");
                        out.println("<table class='table-1'>");
                        System.out.println("table:" + tables);
                        sql = "select * from " + currTable;
                        try (ResultSet resultSet = statement.executeQuery(sql)) {
                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();
                            System.out.println("column count" + columnCount);
                            out.println("<thead><tr>");
                            for (int i = 1; i <= columnCount; ++i) {
                                out.println("<td>" + metaData.getColumnName(i) + "</td>");
                            }
                            out.println("</tr></thead>");
                            while (resultSet.next()) {
                                out.println("<tr>");
                                for (int i = 1; i <= columnCount; ++i) {
                                    String string = resultSet.getString(i);
                                    out.println("<td>" + string + "</td>");
                                }
                                out.println("</tr>");
                            }
                        }
                        out.println("</table>");
                        out.println("</div>");
                    }
                }
            }
        %>
        <button id="clear_btn">清空</button>
    </div>
</div>


<iframe style="display:none" name="hiddenIFrame" id="hiddenIFrame"></iframe>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/renderShow.js">

</script>
</html>

