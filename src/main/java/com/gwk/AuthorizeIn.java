package com.gwk;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
public class AuthorizeIn {
    //当String变量符合notblank时,notblank的message会设置为如下所示
    @NotBlank(message = "缺少response_type参数")
    private String responseType;
    @NotBlank(message = "缺少client_id参数")
    private String ClientId;

    private String state;

    @NotBlank(message = "缺少redirect_uri参数")
    private String redirectUri;

    //构造函数，当没有构造函数给变量赋值，调用检验函数会报错。
//    public AuthorizeIn(){
//        responseType="1";
//        ClientId="1";
//        state="1";
//        redirectUri="1";
//    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}