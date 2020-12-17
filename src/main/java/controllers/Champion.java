package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Path("champion/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Champion {

    @POST
    @Path("get")
    public String championGet (@FormDataParam("level") Integer level, @FormDataParam("stat") String stat) {
        System.out.println("Invoked champion.getStat()"+stat);
        System.out.println("stat " + stat);

        JSONArray response = new JSONArray();

        String query = "SELECT " + stat + ", Name, ImagePath FROM Stats JOIN Champions ON Stats.ChampionID = Champions.ChampionID WHERE Level = " + level + " ORDER BY " + stat + " DESC" ;  //means order the records in descending order of WordID and take only the first which will have the highest ID value
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("stat", results.getString(1));
                row.put("name", results.getString(2));
                row.put("imagePath", results.getString(3));
                response.add(row);
            }
             return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("getall")
    public String championGetall (@FormDataParam("StatsID") Integer StatsID) {
        System.out.println("Invoked champion.getStats()"+StatsID);
        System.out.println("StatsID " + StatsID);

        JSONArray response = new JSONArray();

        String query = "SELECT Hp,Hpr,Mp,Mpr,AD,AP,PR,MR,AttackSpeed,MS,Range,Level,ChampionID FROM Stats WHERE StatsID="+StatsID;  //means order the records in descending order of WordID and take only the first which will have the highest ID value
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("Hp", results.getString(1));
                row.put("Hpr", results.getString(2));
                row.put("Mp", results.getString(3));
                row.put("Mpr", results.getString(4));
                row.put("AD", results.getString(5));
                row.put("AP", results.getString(6));
                row.put("PR", results.getString(7));
                row.put("MR", results.getString(8));
                row.put("AttackSpeed", results.getString(9));
                row.put("MS", results.getString(10));
                row.put("Range", results.getString(11));
                row.put("Level", results.getString(12));
                row.put("ChampionID", results.getString(13));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("Levelgetall")
    public String championLevelGetall (@FormDataParam("ChampionID") Integer ChampionID, @FormDataParam("Level") Integer Level) {
        System.out.println("Invoked champion.getLevelStats()"+ChampionID);
        System.out.println("ChampionID: " + ChampionID);
        System.out.println("Level: " + Level);

        JSONArray response = new JSONArray();

        String query = "SELECT Hp,Hpr,Mp,Mpr,AD,AP,PR,MR,AttackSpeed,MS,Range FROM Stats WHERE ChampionID="+ChampionID+" AND Level="+Level;
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("Hp", results.getString(1));
                row.put("Hpr", results.getString(2));
                row.put("Mp", results.getString(3));
                row.put("Mpr", results.getString(4));
                row.put("AD", results.getString(5));
                row.put("AP", results.getString(6));
                row.put("PR", results.getString(7));
                row.put("MR", results.getString(8));
                row.put("AttackSpeed", results.getString(9));
                row.put("MS", results.getString(10));
                row.put("Range", results.getString(11));
                response.add(row);
            }
            System.out.println("Database returns: "+response.toString());
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("abc")
    public String abcGet (@FormDataParam("level") Integer level) {
        System.out.println("Invoked champion.getStat()");

        JSONArray response = new JSONArray();

        String query = "SELECT  Name, ImagePath FROM Stats JOIN Champions ON Stats.ChampionID = Champions.ChampionID WHERE Level = " + level + " ORDER BY Name ASC" ;  //means order the records in descending order of WordID and take only the first which will have the highest ID value
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("name", results.getString(1));
                row.put("imagePath", results.getString(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("Getname")
    public String Getname (@FormDataParam("ChampionID") Integer ChampionID) {
        System.out.println("Invoked champion.getStat() ID:"+ChampionID);

        JSONArray response = new JSONArray();

        String query ="SELECT Name,ImagePath FROM Champions WHERE ChampionID="+ChampionID;
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("name", results.getString(1));
                row.put("imagePath", results.getString(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("GetChampID")
    public String GetChampID (@FormDataParam("StatsID") Integer StatsID) {
        System.out.println("Invoked champion.getChampID() statsID:"+StatsID);

        JSONArray response = new JSONArray();

        String query ="SELECT ChampionID FROM Stats WHERE StatsID = "+StatsID;
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            JSONObject row = new JSONObject();
            row.put("ChampionID", results.getString(1));
            System.out.println(row.toString());
            return row.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("search")
    public String searchGet (@FormDataParam("level") Integer level,@FormDataParam("search") String search) {
        System.out.println("Invoked champion.getSearch()");

        JSONArray response = new JSONArray();

        String query = "SELECT  Name, ImagePath FROM Stats JOIN Champions ON Stats.ChampionID = Champions.ChampionID WHERE Level = " + level + " AND Name LIKE "+search+" ORDER BY Name ASC";  //means order the records in descending order of WordID and take only the first which will have the highest ID value
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("name", results.getString(1));
                row.put("imagePath", results.getString(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("StatsID")
    public String championIDGet (@FormDataParam("level") String level, @FormDataParam("name") String name) {
        System.out.println("Invoked champion.getStatsID()");
        String query = "SELECT StatsID FROM Stats INNER JOIN Champions ON Stats.ChampionID = Champions.ChampionID WHERE Stats.Level ="+level+" AND  Champions.Name = '"+name+"'";
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            JSONObject row = new JSONObject();
            row.put("StatsID", results.getString(1));
            System.out.println(row.toString());
            return row.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }



}

