package api.utils.personaldata.answercreateuser;
import api.utils.personaldata.answercreateuser.CreateUserResponseUserBlock;
public class CreateUserResponse {
    private Boolean success;
    private String accessToken;
    private String refreshToken;
    private CreateUserResponseUserBlock createUserResponseUserBlocks;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CreateUserResponseUserBlock getCreateUserResponseUserBlocks() {
        return createUserResponseUserBlocks;
    }

    public void setUser(CreateUserResponseUserBlock createUserResponseUserBlocks) {
        this.createUserResponseUserBlocks = createUserResponseUserBlocks;
    }

    public String getAccessToken() {
        if (accessToken != null) {
            accessToken = accessToken.replace("Bearer ", "");
        }
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

