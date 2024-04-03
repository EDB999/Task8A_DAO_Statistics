module com.example.task8a_dao_statistics {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.naming;


    opens com.example.task8a_dao_statistics to javafx.fxml;
    exports com.example.task8a_dao_statistics;
}