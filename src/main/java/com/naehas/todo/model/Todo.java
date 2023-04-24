/*
* created by: Chetan Verma
* role: Associate Software Engineer Trainee as a Java-Backend Developer
* company: Naehas Software Private Limited
*/

package com.naehas.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TODO",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "info"})}
)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "name")
    @JsonProperty
    private String user;
    @Column(nullable = false, name = "info")
    @JsonProperty
    private String desc;
    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty
    private Date targetDate;
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    @JsonProperty
    private Boolean isDone;

    public Todo() {
    }

    public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public Todo(String user, String desc, Date targetDate, Boolean isDone) {
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {

        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
            json.put("user", user);
            json.put("desc", desc);
            json.put("targetDate", targetDate);
            json.put("isDone", isDone);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return json.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
