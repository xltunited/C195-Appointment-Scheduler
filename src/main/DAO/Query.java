package main.DAO;

import java.sql.Statement;

import java.sql.ResultSet;

import static main.DAO.JDBC.connection;

/**
 * Class for creating and executing a select/update query
 */
public class Query {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;

    /**
     * Function that determines which type of query is being made and executes depending on query
     * @param q query to be executed
     */
    public static void makeQuery(String q){
        query =q;
        try{
            stmt=connection.createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=stmt.executeQuery(q);
            if(query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);

        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * Returns the result of the query
     * @return The result of a query
     */
    public static ResultSet getResult(){
        return result;
    }
}