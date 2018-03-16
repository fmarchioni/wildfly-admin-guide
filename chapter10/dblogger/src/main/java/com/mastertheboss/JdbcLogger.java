package com.mastertheboss;

import java.sql.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.*;
import java.util.Date;

public class JdbcLogger extends Handler
{
 
 
          private String driverClassName;
          private String jdbcUrl;
          private String username;
          private String password;
     
 
 
          private Connection connection;
 
 
          private List<String> parameters = new ArrayList<String>();
 
 
          @Override
          public void publish(LogRecord record)
          {
                    if (!ensureReady())
                    {
                              return;
                    }
                    try
                    {
                              insertRecord(record);
                    }
                    catch (SQLException e)
                    {
                              e.printStackTrace();
                    }
          }
 
 
          private synchronized boolean ensureReady()
          {
                    if (connection != null)
                    {
                              return true;
                    }
                    try
                    {
                              
                              setupConnection();
                    }
                    catch (ClassNotFoundException e)
                    {
                              e.printStackTrace();
                              return false;
                    }
                    catch (SQLException e)
                    {
                              e.printStackTrace();
                              return false;
                    }
                    return true;
          }
 
 
       
 
          private void setupConnection() throws ClassNotFoundException, SQLException
          {
        	        
                    Class.forName(driverClassName);
                    connection = DriverManager.getConnection(jdbcUrl, username, password);
          }
 
 
          private void insertRecord(LogRecord logRecord) throws SQLException
          {
                    PreparedStatement statement = null;
                    try
                    {
                    	      
                              statement = connection.prepareStatement("insert into log_table (timestamp,log_level,class,message) values (?,?,?,?)");
                              statement.setString(1,  new java.util.Date().toString());
                              statement.setString(2,  logRecord.getLevel().getName());
                              statement.setString(3,  logRecord.getSourceClassName());
                              statement.setString(4,  logRecord.getMessage());
                              statement.executeUpdate();
                    }
                    finally
                    {
                              if (statement != null)
                              {
                                        statement.close();
                              }
                    }
          }
 
 
         
 
 
      
          @Override
          public void flush()
          {
          }
 
 
          @Override
          public void close()
          {
                    if (connection != null)
                    {
                              try
                              {
                                        connection.close();
                              }
                              catch (SQLException e)
                              {
                                        e.printStackTrace();
                              }
                    }
          }
 
 
          public void setDriverClassName(String driverClassName)
          {
                    this.driverClassName = driverClassName;
          }
 
 
          public void setJdbcUrl(String jdbcUrl)
          {
                    this.jdbcUrl = jdbcUrl;
          }
 
 
          public void setUsername(String username)
          {
                    this.username = username;
          }
 
 
          public void setPassword(String password)
          {
                    this.password = password;
          }
 
 
  
}
