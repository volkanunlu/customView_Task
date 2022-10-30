package com.volkanunlu.ecommerceapp.models;

import java.util.List;

public class AddAddressProperties {

    private String header;
    private String hint;
    private List<String> content;

    public AddAddressProperties(String header, String hint, List<String> content) {
        this.header = header;
        this.hint = hint;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}

