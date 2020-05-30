package com.tzy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSet Object
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
