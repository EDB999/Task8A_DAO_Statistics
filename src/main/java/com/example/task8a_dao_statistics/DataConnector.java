package com.example.task8a_dao_statistics;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataConnector {
    public static DataSource addDataSource(){
        final String url =
                "jdbc:postgresql://localhost:5432/Task8A?user=postgres&password=401330";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }
}
