package Interfaces;

import java.sql.SQLException;

public interface CRUD <T> {
    void Diplay() throws SQLException;
    void Add(T t)throws SQLException;
    void Update(T t)throws SQLException;
    void Delete(T t)throws SQLException;
}
