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

@Path("Pages/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Favourite {
    @POST
    @Path("add")
    public String PageAdd(@FormDataParam("UserID") Integer userID , @FormDataParam("StatsID") Integer statsID, @FormDataParam("favourite") Boolean favourite) {
        System.out.println("Invoked Page.PageAdd()");
        String query = "INSERT INTO Favourites (UserID,StatsID,Favourite) VALUES("+userID+","+statsID+","+favourite+")";
        try {
            PreparedStatement ps = Main.db.prepareStatement(query);
            ps.execute();
            return "{\"OK\": \"Added User.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"UserName already taken\"}";
        }

    }

    @POST
    @Path("statsID")
    public String UserID(@FormDataParam("userID") Integer userID) {
        System.out.println("Invoked StatsID() on path Pages/statsID "+userID);
        String query = "SELECT StatsID FROM Favourites WHERE UserID="+userID+" ORDER BY PageID DESC";
        try (Statement stmt = Main.db.createStatement()) {
            ResultSet results = stmt.executeQuery(query);
            JSONObject row = new JSONObject();
            row.put("StatsID", results.getString(1));
            System.out.println(row.toString());
            return row.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items. User Not authorised.\"}";
        }
    }

    @POST
    @Path("addFavourite")
    public String FavouritePageAdd(@FormDataParam("UserID") Integer userID , @FormDataParam("StatsID") Integer statsID, @FormDataParam("favourite") Boolean favourite) {
        System.out.println("Invoked Page.PageAdd()");
        String query = "INSERT INTO Favourites (UserID,StatsID,Favourite) VALUES("+userID+","+statsID+","+favourite+")";
        try {
            PreparedStatement ps = Main.db.prepareStatement(query);
            ps.execute();
            return "{\"OK\": \"Added User.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"UserName already taken\"}";
        }

    }

}
