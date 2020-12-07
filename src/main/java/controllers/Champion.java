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
        System.out.println("Invoked champion.getStat()");
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
    @Path("abc")
    public String abcGet (@FormDataParam("level") Integer level) {
        System.out.println("Invoked champion.getStat()");

        JSONArray response = new JSONArray();

        String query = "SELECT  Name, ImagePath FROM Stats JOIN Champions ON Stats.ChampionID = Champions.ChampionID WHERE Level = " + level + " ORDER BY Name DESC" ;  //means order the records in descending order of WordID and take only the first which will have the highest ID value
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
    @Path("IDget")
    public String championIDGet (@FormDataParam("level") String level, @FormDataParam("name") String name) {
        System.out.println("Invoked champion.getStat()");
        String query = "SELECT StatsID FROM Stats INNER JOIN Champions ON Stats.ChampionID = Champions.ChampionID WHERE Stats.Level ="+level+" AND  Champions.Name ="+name;
        try  {
            PreparedStatement ps = Main.db.prepareStatement(query);

            String response = (ps.executeQuery()).toString();
            return response;
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }



}

