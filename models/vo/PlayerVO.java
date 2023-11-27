package models.vo;

public class PlayerVO {
    private String team,name,age;
    private boolean active;
    public PlayerVO(String team,String name, String age, boolean active)
    {
        this.team = team;
        this.name = name;
        this.age = age;
        this.active = active;
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
