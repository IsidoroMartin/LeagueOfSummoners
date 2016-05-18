package com.leagueofsummoners.model.dao.tables;

public class Querys {

    public static final String SUMMONERS_QUERY_GET_LAST_MATCH_ID = "SELECT MIN(e.ID_MATCH) FROM Matchs e WHERE ID_USER = :idUser";


}
