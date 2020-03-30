package com.example.springsinginexample.productodefinale.model;

import javax.persistence.*;

@Entity
public class PostEmails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int postid;
    @Column(name="postemail")
    private int postowner;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getPostowner() {
        return postowner;
    }

    public void setPostowner(int postowner) {
        this.postowner = postowner;
    }
}
