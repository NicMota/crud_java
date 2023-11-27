package models.vo;

public class PlayerVO {
    private int id;
    private String team,name,age;
    private boolean active;
    public PlayerVO(int id,String team,String name, String age, boolean active)
    {   
        this.id = id;
        this.team = team;
        this.name = name;
        this.age = age;
        this.active = active;
    }
    
    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
