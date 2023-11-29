package controllers;
import models.PlayerModel;
import models.vo.PlayerVO;

import java.util.ArrayList;
public class PlayerController 
{
    private PlayerModel playerModel;

    public PlayerController()
    {
        playerModel = new PlayerModel();
    }
    public boolean addPlayer(PlayerVO newPlayer)
    {
        return playerModel.addPlayer(newPlayer);
    }
    public PlayerVO searchPlayer(String name)
    {
        return playerModel.searchPlayer(name);
    }
    public boolean removePlayer(String name)
    {
        return playerModel.removePlayer(name);
    }
    public ArrayList<PlayerVO> getPlayersList()
    {
        return playerModel.getPlayersList();
    }
    public ArrayList<String> getPlayersName()
    {
        return playerModel.getPlayersName();
    }
    public boolean editPlayer(int id,String newTeam,String newName,String newAge,boolean isActive)
    {
        return playerModel.editPlayer( id,newTeam,newName,newAge,isActive);
    }
    
}
