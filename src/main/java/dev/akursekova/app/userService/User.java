package dev.akursekova.app.userService;

import lombok.Data;

@Data
public class User {
    private String name;
    private Integer numberOfGames;
    private String ipAddress;
    private Boolean questRestarted;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getQuestRestarted() {
        return questRestarted;
    }

    public void setQuestRestarted(Boolean questRestarted) {
        this.questRestarted = questRestarted;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }
}
