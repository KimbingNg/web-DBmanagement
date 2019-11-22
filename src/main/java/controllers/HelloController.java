package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import util.DbPool;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

@Controller
public class HelloController {

    @RequestMapping("/database")
    public String demo() {
        return "/user/database";
    }

//    public void foo() {
//        PrintWriter out = new PrintWriter(null);
//        String sql = "select table_name from information_schema.tables where table_schema='lab2';";
//        Connection connection = DbPool.getConnection();
//        Statement statement = connection.createStatement();
//        ArrayList<String> tables = new ArrayList<>();
//        try (ResultSet tableResultSet = statement.executeQuery(sql)) {
//            while (tableResultSet.next()) {
//                tables.add(tableResultSet.getString(1));
//            }
//        }
//        int tbCount = 0;
//        for (String currTable : tables) {
//            out.println("<div id='table_" + (tbCount++) + "'>");
//            out.println("<h2>"+currTable+"</h2>");
//            out.println("<table>");
//            System.out.println("table:" + tables);
//            sql = "select * from " + currTable;
//            try (ResultSet resultSet = statement.executeQuery(sql)) {
//                ResultSetMetaData metaData = resultSet.getMetaData();
//                int columnCount = metaData.getColumnCount();
//                System.out.println("column count" + columnCount);
//                out.println("<thead><tr>");
//                for (int i = 1; i <= columnCount; ++i) {
//                    out.println("<td>" + metaData.getColumnName(i) + "</td>");
//                }
//                out.println("</tr></thead>");
//                while (resultSet.next()) {
//                    out.println("<tr>");
//                    for (int i = 1; i <= columnCount; ++i) {
//                        String string = resultSet.getString(i);
//                        out.println("<td>" + string + "</td>");
//                    }
//                    out.println("</tr>");
//                }
//            }
//            out.println("</table>");
//            out.println("</div>");
//        }
//    }
}