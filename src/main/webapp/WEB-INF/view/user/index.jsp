<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%--<link href="<%=request.getContextPath()%>/css/githubstyle.css" rel="stylesheet">--%>
<!DOCTYPE html>
<html>
<style>

</style>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js">
    var currentPage = 0;
</script>


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
        <button value="" id="queryDataase"
                style="width: 300px; height:50px; font-family:KaiTi; font-size:20px;color: blue; margin: 0 auto; ">查询数据库
        </button>
    </div>
    <div class="main_panel">
        <div class="tab">
            <div class="tab_list">
                <ul>
                    <li class="active">Person</li>
                    <li>User</li>
                </ul>
            </div>
            　　　　　
        </div>
        <!--person 部分-->
        <div class="database-item" id="person_tab">
            <form id="personForm" method="post" target="hiddenIFrame">
                <div class="input_table_list">
                    <div class="item clearfix">
                        <label class="txt_label">UserName:</label>
                        <span class="input_wrap"><input id="person_in_username" class="user_input" name="username"
                                                        autocomplete="off"></span>
                        <div class="error-code" id="username_error" style="display:block">
                        </div>
                    </div>
                    <div class="item clearfix">
                        <label class="txt_label">name:</label>
                        <span class="input_wrap"><input id="person_in_name" class="user_input" name="name"
                                                        autocomplete="off"></span>
                        <div class="error-code" id="name_error" style="display:block">
                        </div>
                    </div>
                    <div class="item clearfix">
                        <label class="txt_label">age:</label>
                        <span class="input_wrap"><input id="person_in_age" class="user_input" name="age"
                                                        autocomplete="off"></span>
                        <div class="error-code" id="age_error" style="display:block">
                        </div>
                    </div>
                    <div class="item clearfix">
                        <label class="txt_label">telenum:</label>
                        <span class="input_wrap"><input id="person_in_telenum" class="user_input" name="telenum"
                                                        autocomplete="off"></span>
                        <div class="error-code" id="telenum_error" style="display:block">
                        </div>
                    </div>
                    <div class="item" style="padding:3% 25%">
                        <span class="input_wrap"><input id='person_insert_btn' type="button" value="insert"/></span>
                        <span class="input_wrap"><input id='person_remove_btn' type="button" value="remove"/></span>
                    </div>
                </div>
            </form>
        </div>

        <!--user 部分-->
        <div class="database-item" id="user_tab" style="display: none">
            <form id="userForm" method="post" target="hiddenIFrame">
                <div class="item clearfix">
                    <label class="txt_label">UserName:</label>
                    <span class="input_wrap"><input id="in_user_username" class="user_input" name="username"
                                                    autocomplete="off"/></span>
                    <div class="error-code" id="user_username_error" style="display:block">
                    </div>
                </div>
                <div class="item clearfix">
                    <label class="txt_label">password:</label>
                    <span class="input_wrap"><input type="password" id="in_user_password" class="user_input"
                                                    name="password"
                                                    autocomplete="off"/></span>
                    <div class="error-code" id="user_password_error" style="display:block">
                    </div>
                </div>

                <div class="item" style="padding:3% 25%">
                    <span class="input_wrap"><input id='user_insert_btn' type="button" value="insert"/></span>
                    <span class="input_wrap"><input id='user_remove_btn' type="button" value="remove"/></span>
                </div>

            </form>
        </div>
    </div>
</div>


<iframe style="display:none" name="hiddenIFrame" id="hiddenIFrame"></iframe>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/renderindex.js">

</script>
</html>

