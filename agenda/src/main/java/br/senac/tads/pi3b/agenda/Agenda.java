/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3b.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlys.afreitas
 */
public class Agenda {
    
    private Connection obterConexao() throws ClassNotFoundException, SQLException{
        
        Connection conn = null;
        
        //passo 1: Registrar driver JDBC
        Class.forName("com.mysql.jdbc.Driver");
        
        //Passo 2: Obter a conexao
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendabd",
                "root",
                "");
        return conn;
        
        
    }
      public static void main(String[] args)   {
    
       Agenda agenda = new Agenda();
       agenda.executar();
    
    
    }
    
    public void executar(){
    String querySql
            =  "Select Id, NOME, DTnASCIMENTO FROM PESSOA";
    
    try (Connection conn = obterConexao();
    PreparedStatement stmt = conn.prepareStatement(querySql);
    ResultSet resultados = stmt.executeQuery()) {
        
        while (resultados.next()) {
            long id = resultados.getLong("ID");
            String nome = resultados.getString("NOME");
            Date dtNascimento = resultados.getDate("DTNASCIMENTO");
            System.out.println(id +" " + dtNascimento);
            
        }
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    }
    
  
    

