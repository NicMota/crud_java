package models;
import models.vo.PlayerVO;

import java.util.ArrayList;

import javax.swing.JOptionPane;
public class PlayerModel 
{
    private ArrayList<PlayerVO> playersList = new ArrayList<>();
    
    public boolean playerExists(String name)
    {
        for(PlayerVO p : playersList)
        {
            if(p.getName().equals(name))
                return true;
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
        if(playerExists(newPlayer.getName()))
        {
            JOptionPane.showMessageDialog(null, "Already Exists a Player with that Name!");
            return false;
        }
      
        playersList.add(newPlayer);
        return true;
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
