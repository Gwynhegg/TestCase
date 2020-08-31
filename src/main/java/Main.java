import static spark.Spark.get;
import static spark.Spark.post;
import com.google.gson.Gson;

public class Main {


    public static void main(String[] args){

        TextManagement textManagement = new TextManagement();

        post("/texts", (request, response) -> {
            response.type("application/json");
            Text text = new Gson().fromJson(request.body(), Text.class);

            textManagement.addText(text);
            text.createCategories();
            EasyFileTree easyFileTree = new EasyFileTree(null);
            easyFileTree.setId(text.getId());
            String[] categories =  text.getContent().split("\\r?\\n");

            for (String temp: categories){
                textManagement.addCategories(text.getId(), easyFileTree.addChild(new EasyFileTree(new Text("temporal_id",temp.replaceAll("#",""))),temp.lastIndexOf('#')+1));
            }
            return new Gson().toJson(new ResponseStructure(StatusResponse.SUCCESS));
        });

        get("/texts", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new ResponseStructure(StatusResponse.SUCCESS, new Gson().toJsonTree(textManagement.getTexts())));
        });

        get("/texts/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new ResponseStructure(StatusResponse.SUCCESS, new Gson().toJsonTree(textManagement.getCategories(request.params(":id")))));
        });

    }
}
