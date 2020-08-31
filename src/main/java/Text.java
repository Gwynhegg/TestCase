import java.util.HashMap;

public class Text {

    private String id;
    private String content;
    private HashMap<String, Text> categories;

    public Text(String id, String content){
        super();
        this.id = id;
        this.content = content;
    }

    public void createCategories(){
        categories = new HashMap<String, Text>();
    }
    public void addCategories(Text text){
        categories.put(text.getId(),text);
    }

    public HashMap<String, Text> getCategories(){
        return categories;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getId(){
        return id;
    }
}
