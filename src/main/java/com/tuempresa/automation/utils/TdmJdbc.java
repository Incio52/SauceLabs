package com.tuempresa.automation.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TdmJdbc {
    public static class Credential {
        public final String username;
        public final String password;
        public final String kind;
        public Credential(String u, String p, String k){ this.username=u; this.password=p; this.kind=k; }
    }

    public static List<Credential> readCredentials(String kindFilter) {
        List<Credential> list = new ArrayList<>();
        String driver = System.getenv().getOrDefault("TDM_DRIVER","");
        String url = System.getenv().getOrDefault("TDM_URL","");
        String user = System.getenv().getOrDefault("TDM_USER","");
        String pass = System.getenv().getOrDefault("TDM_PASS","");
        if (url.isEmpty()) return list; // no BD configurada
        try {
            if ("postgresql".equalsIgnoreCase(driver)) {
                Class.forName("org.postgresql.Driver");
            } else if ("mssql".equalsIgnoreCase(driver)) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }
            try (Connection cn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement ps = cn.prepareStatement("SELECT username,password,kind FROM test_credentials WHERE kind = ?")) {
                ps.setString(1, kindFilter);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) list.add(new Credential(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception ignored){}
        return list;
    }
}
