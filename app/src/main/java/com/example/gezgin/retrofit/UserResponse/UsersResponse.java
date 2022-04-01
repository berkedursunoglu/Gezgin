
package com.example.gezgin.retrofit.UserResponse;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UsersResponse {

    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
