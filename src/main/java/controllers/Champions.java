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

@Path("Champions/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Champions {

    @GET
    @Path("getimg/{CombinedID}")
    public String getimg(@PathParam("CombinedID") Integer CombinedID) {
        System.out.println("Invoked Food.getFood() with ChampID " + CombinedID);
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT img FROM Champions WHERE ChampID =(SELECT ChampID FROM Levels WHERE CombinedID = ?)");
            ps.setInt(1, CombinedID);
            ResultSet results = ps.executeQuery();
            JSONObject response = new JSONObject();
            if (results.next()== true) {
                response.put("img", results.getString(1));
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @GET
    @Path("getname/{CombinedID}")
    public String getname(@PathParam("CombinedID") Integer CombinedID) {
        System.out.println("Invoked Food.getFood() with CombinedID " + CombinedID);
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Name FROM Champions WHERE ChampID =(SELECT ChampID FROM Levels WHERE CombinedID = ?)");
            ps.setInt(1, CombinedID);
            ResultSet results = ps.executeQuery();
            JSONObject response = new JSONObject();
            if (results.next()== true) {
                response.put("Name", results.getString(1));
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @GET
    @Path("getimgABC")
    public String GetABC() {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " );

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT img FROM Champions WHERE Name DESC");
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("img", results.getString(1));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getHp/{level}")
    public String GetHp(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.Hp,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.Hp DESC ");
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

    @GET
    @Path("getHpr/{level}")
    public String GetHpr(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.Hpr,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.Hpr DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("Hpr", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getMp/{level}")
    public String GetMp(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getMp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.MpSp,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.MpSp DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("Mp", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getMpr/{level}")
    public String GetMpr(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.MpSpr,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.MpSpr DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("Mpr", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getAD/{level}")
    public String GetAD(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.AD,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.AD DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("AD", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getAP/{level}")
    public String GetAP(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.AP,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.AP DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("AP", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getPR/{level}")
    public String GetPR(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.PR,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.PR DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("PR", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getMR/{level}")
    public String GetMR(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.MR,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.MR DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("MR", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getAS/{level}")
    public String GetAS(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.AS,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.AS DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("AS", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getMS/{level}")
    public String GetMS(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.MS,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.MS DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("MS", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getRange/{level}")
    public String GetRange(@PathParam("level") Integer level) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.Range,Stats.CombinedID FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? ORDER BY Stats.Range DESC ");
            ps.setInt(1, level);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("Range", results.getInt(1));
                row.put("CombinedID", results.getInt(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

    @GET
    @Path("getStats/{Level}/{CombinedID}")
    public String GetStats(@PathParam("Level") Integer level, @PathParam("CombinedID") Integer CombinedID) {

        JSONArray response = new JSONArray();
        System.out.println("Invoked Champions.getHp() with level " + level);

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Stats.* FROM Stats INNER JOIN Levels ON Levels.CombinedID = Stats.CombinedID WHERE Levels.Level = ? AND Levels.ChampID=(SELECT ChampID FROM Levels WHERE CombinedID = ?)");
            ps.setInt(1, level);
            ps.setInt(2, CombinedID);
            ResultSet results = ps.executeQuery();
            while (results.next() == true) {
                JSONObject row = new JSONObject();
                row.put("CombinedID", results.getInt(1));
                row.put("Hp", results.getInt(2));
                row.put("Hpr", results.getInt(3));
                row.put("Mp", results.getInt(4));
                row.put("Mpr", results.getInt(5));
                row.put("AD", results.getInt(6));
                row.put("AP", results.getInt(7));
                row.put("PR", results.getInt(8));
                row.put("MR", results.getInt(9));
                row.put("AS", results.getInt(10));
                row.put("MS", results.getInt(11));
                row.put("Range", results.getInt(12));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items.  Error code xx.\"}";
        }
    }

}

