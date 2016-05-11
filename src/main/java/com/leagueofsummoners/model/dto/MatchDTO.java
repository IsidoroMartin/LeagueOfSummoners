package com.leagueofsummoners.model.dto;

public class MatchDTO {


    private int matchDuration;
    private String championName;
    private String lanePlayed;;
    private String iconUri;
    private int kills;
    private int deaths;
    private int assistences;
    private String stats;


    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getStats(){
        this.stats =  this.kills + "/" + this.deaths + "/" + this.assistences;
        return this.stats;
    }

    public int getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssistences() {
        return assistences;
    }

    public void setAssistences(int assistences) {
        this.assistences = assistences;
    }

    public String getLanePlayed() {
        return lanePlayed;
    }

    public void setLanePlayed(String lanePlayed) {
        this.lanePlayed = lanePlayed;
    }

}