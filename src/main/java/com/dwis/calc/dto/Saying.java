package com.dwis.calc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by Dominik on 02.04.16.
 */
public class Saying {

    private long id;

    @Length(max = 3)
    private String content;

    public Saying(){}

    public Saying(long id, String content){
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }


}
