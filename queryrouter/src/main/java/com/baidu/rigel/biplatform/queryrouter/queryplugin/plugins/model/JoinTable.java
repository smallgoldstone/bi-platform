package com.baidu.rigel.biplatform.queryrouter.queryplugin.plugins.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * sql Join
 * 
 * @author luowenlei
 *
 */
public class JoinTable extends SqlSegment {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -85610952681471460L;
    
    /**
     * tableName
     */
    private String tableName;
    
    /**
     * joinOnList
     */
    private List<JoinOn> joinOnList = new ArrayList<JoinOn>();

    /* (non-Javadoc)
     * @see com.baidu.rigel.biplatform.queryrouter.queryplugin.plugins.model.SqlSegment#getSql()
     */
    @Override
    public String getSql() {
        if (StringUtils.isEmpty(this.tableName)) {
            return "";
        }
        StringBuffer sql = new StringBuffer(" left outer join ");
        sql.append(this.tableName);
        sql.append(SqlConstants.SPACE);
        sql.append(this.tableName);
        sql.append(SqlConstants.SPACE);
        sql.append(SqlConstants.JOIN_ON);
        sql.append(SqlConstants.SQL_TRUE);
        for (JoinOn joinOn : joinOnList) {
            if (StringUtils.isEmpty(joinOn.getFacttableColumnName())) {
                continue;
            }
            sql.append(SqlConstants.SPACE);
            sql.append(SqlConstants.AND);
            sql.append(SqlConstants.SPACE);
            sql.append(this.tableName);
            sql.append(SqlConstants.DOT);
            sql.append(joinOn.getJoinTableFieldName());
            sql.append(SqlConstants.SPACE);
            sql.append(SqlConstants.EQUALS);
            sql.append(SqlConstants.SPACE);
            sql.append(SqlConstants.SOURCE_TABLE_ALIAS_NAME);
            sql.append(SqlConstants.DOT);
            sql.append(joinOn.getFacttableColumnName());
            sql.append(SqlConstants.SPACE);
        }
        return sql.toString();
    }

    /**
     * default generate get joinOnList
     * @return the joinOnList
     */
    public List<JoinOn> getJoinOnList() {
        return joinOnList;
    }

    /**
     * default generate set joinOnList
     * @param joinOnList the joinOnList to set
     */
    public void setJoinOnList(List<JoinOn> joinOnList) {
        this.joinOnList = joinOnList;
    }

    /**
     * default generate get tableName
     * 
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * default generate set tableName
     * 
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
