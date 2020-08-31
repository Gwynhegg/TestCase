import static spark.Spark.get;
import static spark.Spark.post;
import com.google.gson.Gson;

public class Main {


    public static void main(String[] args){

        TextManagement textManagement = new TextManagement();

        // Программирование метода POST. Добавление нового текста
        post("/texts", (request, response) -> {
            response.type("application/json");

            Text text = new Gson().fromJson(request.body(), Text.class);             // Ввод производится через JSON-объект, где id - идентификатор текста, content - ег осодержимое

            textManagement.addText(text);
            text.createCategories();
            EasyFileTree easyFileTree = new EasyFileTree(null);              // Для создания правильной иерархии категорий используем структуру данных "дерево"
            easyFileTree.setId(text.getId());
            String[] categories =  text.getContent().split("\\r?\\n");

            for (String temp: categories){                                          // Разбиваем текст по категориям и добавляем в дерево
                textManagement.addCategories(text.getId(), easyFileTree.addChild(new EasyFileTree(new Text("temporal_id",temp.replaceAll("#",""))),temp.lastIndexOf('#')+1));
            }
            return new Gson().toJson(new ResponseStructure(StatusResponse.SUCCESS));
        });

        // Программирование метода GET. Получение данных о всех текстах на сервере
        get("/texts", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new ResponseStructure(StatusResponse.SUCCESS, new Gson().toJsonTree(textManagement.getTexts())));
        });

        // Программирование метода GET. Вывод категорий отдельного текста
        get("/texts/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new ResponseStructure(StatusResponse.SUCCESS, new Gson().toJsonTree(textManagement.getCategories(request.params(":id")))));
        });

    }
}
