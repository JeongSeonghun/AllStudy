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
    private final String PARAM_VALUES = " VALUES";
    private final String PARAM_SET = " SET ";
    private final String PARAM_WHERE = " WHERE ";
    private final String PARAM_FROM = " FROM ";
    private final String PARAM_ORDER = " ORDER ";
    private final String PARAM_LIMIT = " LIMIT ";
    private final String PARAM_OFFSET = " OFFSET ";

    public String createTable(String tableName, Row row){
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(row);
        return createTable(tableName, rows);
    }

    // CREATE TABLE table (row1 typ1 option..., row2...)
    public String createTable(String tableName, ArrayList<Row> rows){
        if(!checkParams(tableName) || checkParams(rows)) return null;

        return FUNC_CREATE + NAME_TABLE + tableName + wrapCreateTable(rows);
    }

    public String createTableNotExist(String tableName, ArrayList<Row> rows){
        if(!checkParams(tableName) || checkParams(rows)) return null;

        return FUNC_CREATE + NAME_TABLE + Option.NOT_EXIST.getValue() + tableName + wrapCreateTable(rows);
    }

    // INSERT INTO table VALUES(val1, val2...)
    // INSERT INTO table (row1, row2...) VALUES(val1, val2...)
    public String insert(String tableName, ArrayList<Row> rows, ArrayList<String> values){
        if(!checkParams(tableName)||checkParams(rows, values) || rows.size() == values.size()) return null;

        return FUNC_INSERT + PARAM_INTO + tableName + wrapUnit(rows) +PARAM_VALUES+ wrapBase(values);
    }

    public String insert(String tableName, ArrayList<String> values){
        if(!checkParams(tableName) || !checkParams(values)) return null;

        return FUNC_INSERT + PARAM_INTO + tableName + PARAM_VALUES + wrapBase(values);
    }

    // UPDATE table SET row1 = val1, row2 = val2...
    // WHERE ...
    public String update(String tableName, ArrayList<UpdateData> datas, String where){
        if(!checkParams(tableName, where) || !checkParams(datas)) return null;

        return FUNC_UPDATE + tableName + PARAM_SET+ wrapUpdate(datas) +(!checkEmty(where)?(PARAM_WHERE+where):null);
    }

    // SELECT * FROM table
    // SELECT row1, row2... FROM table
    // WHERE ...
    public String select(String tableName, ArrayList<Row> rows, int... limits){
        if(!checkParams(tableName) || !checkParams(rows)) return null;

        return FUNC_SELECT + wrapUnit(rows) + PARAM_FROM + tableName +addLimit(limits);
    }

    public String select(String tableName, int... limits){
        if(!checkParams(tableName)) return null;

        return FUNC_SELECT + "*" + PARAM_FROM + tableName + addLimit(limits);
    }

    private String addLimit(int... limits){
        if(limits==null || limits.length<=0) return null;

        int size = limits.length;
        if(size == 1){
            return PARAM_LIMIT + limits[0];
        }else if(size == 2){
            return PARAM_LIMIT + limits[0] + PARAM_OFFSET + limits[1];
        }else{
            return null;
        }
    }

    // DELETE FROM table
    // DELETE FROM table WHERE ...
    public String delete(String tableName, String where){
        if(!checkParams(tableName, where)) return null;

        return FUNC_DELETE + PARAM_FROM + tableName + PARAM_WHERE + where;
    }

    public String delete(String tableName){
        if(!checkParams(tableName)) return null;

        return FUNC_DELETE + PARAM_FROM + tableName;
    }

    // DROP DATABASE database
    // DROP TABLE table
    public String drop(){
        return null;
    }

    // ALTER table ...
    public String alter(){
        return null;
    }

    public String updateIdxInit(String tableName){
        return updateIdxInit(tableName, 1);
    }

    public String updateIdxInit(String tableName, int initIdx){
        if(!checkEmty(tableName)) return null;
        return "UPDATE SQLITE_SEQUENCE SET seq = "+initIdx+" WHERE name = '"+tableName+"'";
    }

    private String wrapCreateTable(ArrayList<Row> rows){
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

    private String wrapUnit(ArrayList<Row> rows){
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

    private String wrapBase(ArrayList<String> val){
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

    private String wrapUpdate(ArrayList<UpdateData> datas){
        StringBuilder sb = new StringBuilder("(");

        if(datas == null || datas.size()<=0) return null;

        int totalCnt = datas.size();
        for(int idx = 0; idx < totalCnt ; idx++){
            sb.append(datas.get(idx).toString());

            if(idx != totalCnt-1) sb.append(", ");
        }

        sb.append(")");

        return sb.toString();
    }

    private boolean checkEmty(String str){
        return str == null || str.isEmpty();
    }

    public boolean checkParams(String... params){
        for(String param : params){
            if(checkEmty(param)) return false;
        }
        return true;
    }

    public boolean checkParams(ArrayList... params){
        for(ArrayList param : params){
            if(param == null || param.size()<=0) return false;
        }
        return true;
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
        public int getCode(){
            if(type.equals(INT.getValue())){
                return 1;
            }else if(type.equals(TEXT.getValue())){
                return 10;
            }else {
                return -1;
            }
        }
    }

    public enum Option{
        PRIMARY_KEY(" PRIMARY KEY ")
        , BASE_IDX(" PRIMARY KEY AUTOINCREMENT ")
        , NOT_NULL(" NOT NULL ")
        , NOT_EXIST(" IF NOT EXIST ");

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

    public class UpdateData{
        private String rowName = null;
        private Type type = null;
        private Object data = null;

        public String getRowName() {
            return rowName;
        }

        public void setRowName(String rowName) {
            this.rowName = rowName;
        }

        public int getiData(int base) {
            if(check(Type.INT)) return base;
            return (int)data;
        }

        public String getStrData() {
            if(check(Type.TEXT)) return null;
            return (String)data;
        }

        public void setData(Object data){
            this.data = data;
            setType(data);
        }

        private void setType(Object data){
            if(data instanceof Integer){
                type = Type.INT;
            }else if(data instanceof String){
                type = Type.TEXT;
            }
        }

        public Type getType(){
            return type;
        }

        private boolean check(Type type){
            return rowName == null || rowName.isEmpty() || type == null || !this.type.equals(type);
        }

        public String toString(){
            if(checkEmty(rowName)) return "";

            StringBuilder sb = new StringBuilder();
            sb.append(rowName).append("=");
            switch (type.getCode()){
                case -1:
                    break;
                case 1:
                    sb.append(getiData(-1));
                    break;
                case 10:
                    sb.append("\"").append(getStrData()).append("\"");
                    break;
            }

            return sb.toString();
        }
    }
}
