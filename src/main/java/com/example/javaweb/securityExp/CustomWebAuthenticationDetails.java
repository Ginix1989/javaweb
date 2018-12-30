package com.example.javaweb.securityExp;


import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 携带额外信息
 */

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String invalue;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        invalue = request.getParameter("author");
    }

    public String getInvalue(){
        return invalue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; invalue: ").append(this.getInvalue());
        return sb.toString();
    }
}
