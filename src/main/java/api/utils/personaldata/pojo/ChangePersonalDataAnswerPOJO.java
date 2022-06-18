package api.utils.personaldata.pojo;

import java.util.List;

public class ChangePersonalDataAnswerPOJO {
    private Boolean success;
    private List<UserFieldsAnswerChangePOJO> userFieldsList;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<UserFieldsAnswerChangePOJO> getUserFieldsList() {
        return userFieldsList;
    }

    public void setUserFieldsList(List<UserFieldsAnswerChangePOJO> userFieldsList) {
        this.userFieldsList = userFieldsList;
    }


}
