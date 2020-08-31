import com.google.gson.JsonElement;

public class ResponseStructure {

    private StatusResponse status;
    private String message;
    private JsonElement data;

    public ResponseStructure(StatusResponse status){
        this.status = status;
    }

    public ResponseStructure(StatusResponse status, String message){
        this.status = status;
        this.message = message;
    }

    public ResponseStructure(StatusResponse status, JsonElement data){
        this.status = status;
        this.data = data;

    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}

