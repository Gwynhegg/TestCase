import java.util.Collection;
import java.util.HashMap;

//Класс для взаимодействия с текстом
public class TextManagement {

    private HashMap<String, Text> textMap;          // Хэшмап для хранения id и содержимого текста

    public TextManagement(){
        textMap = new HashMap<String, Text>();
    }
    public void addText (Text text) {
        textMap.put(text.getId(), text);
    }
    public void addCategories(String id, Text added_category){
        textMap.get(id).addCategories(added_category);
    }

    public Collection<Text> getTexts(){
        return textMap.values();
    }

    public Collection<Text> getCategories(String id){
        return textMap.get(id).getCategories().values();
    }

    public Text getText (String id){
        return textMap.get(id);
    }
}
