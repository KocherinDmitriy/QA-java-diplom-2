package api.utils.createuser.pojo;

import java.util.ArrayList;
import java.util.List;

public class CreateAnswerPOJO {
    private Boolean success;
    private ArrayList<CreateAnswerUserBlockPOJO> createUserBlockPOJOS;
    private String accessToken;
    private String refreshToken;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<CreateAnswerUserBlockPOJO> getCreateUserBlockPOJOS() {
        return createUserBlockPOJOS;
    }

    public void setUser(ArrayList<CreateAnswerUserBlockPOJO> createUserBlockPOJOS) {
        this.createUserBlockPOJOS = createUserBlockPOJOS;
    }

    public String getAccessToken() {
        accessToken = accessToken.replace("Bearer ", "");
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
