package com.example.springsinginexample.productodefinale.model;

import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "blogposts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "posttitle")
    private String posttitle;
    @Column(name = "posttext")
    private String posttext;
    @Column(name = "postowner")
    private String postowner;

/*    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="userpost", joinColumns=@JoinColumn(name="id"), inverseJoinColumns=@JoinColumn(name="postemail"))
    private Set<PostEmails> postEmails;*/

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    public String getOwner() {
        return postowner;
    }

    public void setOwner(String postowner) {
        this.postowner = postowner;
    }
}
