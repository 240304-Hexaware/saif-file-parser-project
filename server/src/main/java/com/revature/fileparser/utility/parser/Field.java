package com.revature.fileparser.utility.parser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Field {

    private String name;
    private Integer startPos;
    private Integer endPos;
    private Integer fieldSize;
    private String dataType;

    public Field() {
    }

    public Field(String name, Integer startPos, Integer endPos, Integer fieldSize, String dataType) {
        this.name = name;
        this.startPos = startPos;
        this.endPos = endPos;
        this.fieldSize = fieldSize;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartPos() {
        return startPos;
    }

    @JsonProperty("start_pos")
    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    public Integer getEndPos() {
        return endPos;
    }

    @JsonProperty("end_pos")
    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public Integer getFieldSize(){
        return fieldSize;
    }

    public void setFieldSize(Integer fieldSize){
        this.fieldSize = fieldSize;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                ", dataType=" + dataType +
                '}';
    }
}