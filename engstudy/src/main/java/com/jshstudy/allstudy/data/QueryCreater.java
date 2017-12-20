package com.jshstudy.allstudy.data;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-12-18.
 */

public class QueryCreater {
    private final String FUNC_CREATE = "CREATE ";
    private final String FUNC_INSERT = "INSERT ";
    private final String FUNC_SELECT = "SELECT ";
    private final String FUNC_UPDATE = "UPDATE ";
    private final String FUNC_DELETE = "DELETE ";
    private final String FUNC_DROP = "DROP ";

    private final String NAME_DATABASE = "DATABASE ";
    private final String NAME_TABLE = "TABLE ";

    private final String PARAM_INTO = "INTO ";
    private final String PARAM_SET = "SET ";
    private final String PARAM_WHERE = "WHERE ";
    private final String PARAM_ORDER = "ORDER ";
    private final String PARAM_LIMIT = "LIMIT ";
    private final String PARAM_OFFSET = "OFFSET ";

    public String createTable(String tableName, Row row){
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(row);
        return createTable(tableName, rows);
    }

    // CREATE TABLE table (row1 typ1 option..., row2...)
    public String createTable(String tableName, ArrayList<Row> rows){
        if(!checkEmty(tableName) || rows == null || rows.size()<1) return null;

        return FUNC_CREATE + NAME_TABLE + tableName + wrap1(rows);
    }

    // INSERT INTO table VALUES(val1, val2...)
    // INSERT INTO table (row1, row2...) VALUES(val1, val2...)
    public String insert(String tableName, ArrayList<Row> rows, ArrayList<String> values){
        if(!checkEmty(tableName)||rows==null || values == null || rows.size() == values.size()) return null;

        return FUNC_INSERT + PARAM_INTO + tableName + wrap2(rows) + wrap3(values);
    }

    // UPDATE table SET row1 = val1, row2 = val2...
    // WHERE ...
    public String update(){
        return null;
    }

    // SELECT
    public String select(){
        return null;
    }

    // DELETE
    public String delete(){
        return null;
    }

    // DROP
    public String drop(){
        return null;
    }

    public String updateIdxInit(String tableName){
        return updateIdxInit(tableName, 1);
    }

    public String updateIdxInit(String tableName, int initIdx){
        if(!checkEmty(tableName)) return null;
        return "UPDATE SQLITE_SEQUENCE SET seq = "+initIdx+" WHERE name = '"+tableName+"'";
    }

    private String wrap1(ArrayList<Row> rows){
        StringBuilder sb = new StringBuilder("(");

        if(rows == null || rows.size() <1) return null;

        Row lastRow = rows.get(rows.size()-1);

        for(Row row : rows){
            String rowName = row.getRowName();
            String type = row.getType();
            String option = row.getOption();

            if(!checkEmty(rowName))continue;
            sb.append(rowName).append(" ");

            if(!checkEmty(type))continue;
            sb.append(type).append(" ");

            if(checkEmty(option)) sb.append(option);

            if(!lastRow.equals(row)) sb.append(", ");
        }

        sb.append(")");

        return sb.toString();
    }

    private String wrap2(ArrayList<Row> rows){
        StringBuilder sb = new StringBuilder("(");

        if(rows == null || rows.size() <1) return null;

        Row lastRow = rows.get(rows.size()-1);

        for(Row row : rows){
            String rowName = row.getRowName();

            if(!checkEmty(rowName))continue;
            sb.append(rowName).append(" ");

            if(!lastRow.equals(row)) sb.append(", ");
        }

        sb.append(")");

        return sb.toString();
    }

    private String wrap3(ArrayList<String> val){
        StringBuilder sb = new StringBuilder("(");

        if(val == null || val.size() <1) return null;

        int totalCnt = val.size();
        for(int idx = 0; idx < totalCnt ; idx++){
            sb.append(val.get(idx));

            if(idx != totalCnt-1) sb.append(", ");
        }

        sb.append(")");

        return sb.toString();
    }

    public boolean checkEmty(String str){
        return str != null && str.isEmpty();
    }

    public enum Type{
        INT("INTEGER"),
        TEXT("TEXT");

        private final String type;
        Type(String type){
            this.type = type;
        }

        public String getValue(){
            return type;
        }
    }

    public enum Option{
        PRIMARY_KEY("PRIMARY KEY")
        , BASE_IDX("PRIMARY KEY AUTOINCREMENT")
        , NOT_NULL("NOT NULL");

        private final String option;

        Option(String option) {
            this.option = option;
        }

        public String getValue(){
            return option;
        }
    }

    public class Row{

        private String rowName = null;
        private String type = null;
        private String option = null;

        public String getRowName() {
            return rowName;
        }

        public void setRowName(String rowName) {
            this.rowName = rowName;
        }

        public String getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type.getValue();
        }

        public String getOption() {
            return option;
        }

        public void setOption(Option option) {
            this.option = option.getValue();
        }

    }
}
