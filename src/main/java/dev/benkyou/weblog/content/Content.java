package dev.benkyou.weblog.content;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content extends PanacheEntityBase {

    @Id
    private Long id;

    @Column(name = "path_name")
    private String pathName;

    private String content;

    public Long getId() {
        return id;
    }

    public Content setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPathName() {
        return pathName;
    }

    public Content setPathName(String pathName) {
        this.pathName = pathName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Content setContent(String content) {
        this.content = content;
        return this;
    }
}
