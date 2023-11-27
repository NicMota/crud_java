package models;
import models.vo.PlayerVO;


import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class PlayerModel 
{
    private ArrayList<PlayerVO> playersList = new ArrayList<>();
    private Connection conn;
    public boolean conectar()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");

            this.conn = DriverManager.getConnection("jdbc:hsqldb:file:C:/Users/User/Desktop/crud_java/db/crud_java","sa","");
            
            return true;
            
            
        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return false;
        }catch(ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean playerExists(String name)
    {   
        if(this.conectar()){
            try
            {
            

                String query = "SELECT count(*) FROM jogadores_cs WHERE name=?";
                PreparedStatement stmt = this.conn.prepareStatement(query);

                stmt.setString(1, name);

                ResultSet res = stmt.executeQuery();

                if(res.getRow() == 0)
                {
                    stmt.close();
                    res.close();
                    return false;
                }
                stmt.close();
                res.close();
                this.conn.close();
                return true;        
            }
            catch(SQLException e)
            {
                return false;
            }
        }
        return false;
        
    }
    public String[] getPlayersNameArray()
    {   
        String[] playersNameArray = new String[playersList.size()];
        for (int i = 0; i < playersNameArray.length; i++) 
        {   
            if(playersList.get(i) != null)
            playersNameArray[i] = playersList.get(i).getName();
        }  
        
        return playersNameArray;

    }
    public boolean addPlayer(PlayerVO newPlayer)
    {   
        if(this.conectar())
        {
            if(playerExists(newPlayer.getName()))
            {
                JOptionPane.showMessageDialog(null, "Already Exists a Player with that Name!");
                return false;
            }
        
            try {
                String query = "INSERT INTO jogadores_cs(id,name,team,age,active) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = this.conn.prepareStatement(query);

                stmt.setInt(1, newPlayer.getId());
                stmt.setString(2, newPlayer.getName());
                stmt.setString(3,newPlayer.getTeam());
                stmt.setString(4,newPlayer.getAge());
                stmt.setBoolean(5,newPlayer.isActive());

                int res = stmt.executeUpdate();
                if(res==1)
                {   JOptionPane.showMessageDialog(null, "Jogador Adicionado com Sucesso");
                    stmt.close();
                    this.conn.close();
                    return true;
                }
                stmt.close();
                this.conn.close();
                return false;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return false;
            }
                
        }
        return false;

        
    }
    public PlayerVO searchPlayer(String name)
    {  
        for(PlayerVO p : playersList)
        {   
         
            if(p.getName().equals(name))
            {
                return p;
            }
        }
        return null;
    }
    public boolean removePlayer(String name)
    {
         for(PlayerVO p : playersList)
        {
            if(p.getName().equals(name));
            {   
                playersList.remove(p);
                return true;
            }
        }
        return false;
    }

    public ArrayList<PlayerVO> getPlayersList()
    {
        return playersList;
    }
    public boolean editPlayer(String oldPlayersName,String newTeam,String newName,String newAge,boolean isActive)
    {
        for (PlayerVO p : playersList) 
        {
            if(p.getName().equals(oldPlayersName))
            {
                p.setTeam(newTeam);
                p.setName(newName);
                p.setAge(newAge);
                p.setActive(isActive);

                return true;
            }
        }
        return false;
        

    }
}
