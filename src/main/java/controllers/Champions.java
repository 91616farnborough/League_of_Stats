package controllers;

import com.sun.istack.Nullable;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Path("champions/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Champions {

    @GET
    @Path("getHp/{level}")
    public String GetHp(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Hp,CombinedID FROM Stats WHERE CombinedID=(SELECT CombinedID FROM Levels WHERE Level = ?) ORDER BY Hp DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("Hp", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }
}

