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
            
            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return false;
        }catch(ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean deleteAll()
    {
        String query = "DELETE FROM jogadores_cs";
        try {
            this.conectar();
            Statement stmt = this.conn.createStatement();
            

            int res = stmt.executeUpdate(query);

            if(res == 1)
            {   
                stmt.close();
                this.conn.close();
                return true;
            }
                
            stmt.close();
            this.conn.close();
            return false;

        } catch (SQLException e) {
           return false;
        }
    }
    public boolean playerExists(int id)
    {   
        if(this.conectar()){
            try
            {
            

                String query = "SELECT count(*) FROM jogadores_cs WHERE id=?";
                PreparedStatement stmt = this.conn.prepareStatement(query);
                
                stmt.setInt(1, id);
                
                ResultSet res = stmt.executeQuery();

                if(res.getRow() == 0)
                {
                    stmt.close();
                    res.close();
                    this.conn.close();
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
    public ArrayList<String> getPlayersName()
    {   
        ArrayList<String> playersNameList = new ArrayList<>();;
        String query = "SELECT name FROM jogadores_cs";
        try {
            this.conectar();
            
            Statement stmt = this.conn.createStatement();
            
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {
                String name = res.getString("name");
                playersNameList.add(name);
            }

            this.conn.close();
            return playersNameList;
            
        } catch (SQLException e) {
            
            return null;
        }
        

    }
    public boolean addPlayer(PlayerVO newPlayer)
    {   
        if(this.conectar())
        {
            if(playerExists(newPlayer.getId()))
            {
                JOptionPane.showMessageDialog(null, "Already Exists a Player with that Id!");
                return false;
            }
        
            try {
                this.conectar();
                String query = "INSERT INTO jogadores_cs(id,name,team,age,active) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = this.conn.prepareStatement(query);

                stmt.setInt(1, newPlayer.getId());
                stmt.setString(2, newPlayer.getName());
                stmt.setString(3,newPlayer.getTeam());
                stmt.setString(4,newPlayer.getAge());
                byte actv;
                if(newPlayer.isActive())
                    actv = 1;
                else 
                    actv = 0;
                stmt.setByte(5, actv);

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
    public ArrayList<PlayerVO> searchPlayer(String name)
    {   

        String query = "SELECT * FROM jogadores_cs WHERE name LIKE ?;";
        try {
            this.conectar();
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, "%" +name+ "%");
            ResultSet res = stmt.executeQuery();
            int id;
            String nme,team,age;
            boolean active;
            ArrayList<PlayerVO> list = new ArrayList<>();
            
            while (res.next()) 
            {
                id = res.getInt("id");
                nme = res.getString("name");
                team = res.getString("team");
                age = res.getString("age");
                active = res.getBoolean("active");
                list.add(new PlayerVO(id, team, nme, age, active));
                
                
            }
            if(list.isEmpty())
                return null;
            stmt.close();
            res.close();
            this.conn.close();
            return list;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
        
        
    }
    public boolean removePlayer(String name)
    {

        String query = "DELETE FROM jogadores_cs WHERE name = ?";
        try {
            this.conectar();
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, name);

            int res = stmt.executeUpdate();

            if(res == 1)
            {   
                stmt.close();
                this.conn.close();
                return true;
            }
                
            stmt.close();
            this.conn.close();
            return false;

        } catch (SQLException e) {
           return false;
        }
    
    }

    public ArrayList<PlayerVO> getPlayersList()
    {
        try {
            ArrayList<PlayerVO> list = new ArrayList<>();
            this.conectar();
            String query = "SELECT * FROM jogadores_cs";
            Statement stmt = this.conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String team = res.getString("team");
                String age = res.getString("age");
                byte active = res.getByte("active");
                boolean actv;
                if(active == 1)
                    actv = true;
                else 
                    actv = false;
            
                list.add(new PlayerVO(id, team, name, age, actv));
                    
            }
            this.conn.close();
            stmt.close();
            res.close();
            return list;

        } catch (SQLException e) {
            return null;   
        }
    }
    public boolean editPlayer(int id,String newTeam,String newName,String newAge,boolean isActive)
    {
        String query = "UPDATE jogadores_cs SET team = ?,name = ?,age=?,active=? WHERE id = ?";
        try {
            this.conectar();
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, newTeam);
            stmt.setString(2, newName);
            stmt.setString(3, newAge);
            byte actv;
            if(isActive)
                actv = 1;
            else
                actv = 0;
            stmt.setByte(4, actv);
            stmt.setInt(5, id);

            int res = stmt.executeUpdate();

            if(res==1)
            {
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
}
