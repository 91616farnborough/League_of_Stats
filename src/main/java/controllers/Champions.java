package controllers;

public class Champions {
    package controllers;

    @Path("food/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)

    public class Food {

        @GET
        @Path("get/{foodID}")
        public String getFood(@PathParam("foodID") Integer foodID) {
            System.out.println("Invoked Food.getFood() with foodID " + foodID);
            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT Name, Quantity FROM Food WHERE FoodID = ?");
                ps.setInt(1, foodID);
                ResultSet results = ps.executeQuery();
                JSONObject response = new JSONObject();
                if (result.next()== true) {
                    response.put("FoodID", FoodID);
                    response.put("Name", results.getString(1));
                    response.put("Quantity", results.getInt(2));
                }
                return response.toString();
            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"Error\": \"Unable to get item, please see server console for more info.\"}";
            }
        }


    }
