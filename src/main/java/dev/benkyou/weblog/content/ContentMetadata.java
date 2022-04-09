package dev.benkyou.weblog.content;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.ws.rs.QueryParam;
import java.util.Date;

@Entity
@Table(name = "content_metadata")
public class ContentMetadata extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path_name")
    private String pathName;

    @QueryParam("tags")
    private String tags;

    @QueryParam("category")
    private String category;

    @QueryParam(("title"))
    private String title;

    @QueryParam("keywords")
    private String keywords;

    private String introduction;

    @Transient
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public ContentMetadata setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPathName() {
        return pathName;
    }

    public ContentMetadata setPathName(String pathName) {
        this.pathName = pathName;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public ContentMetadata setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ContentMetadata setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ContentMetadata setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public ContentMetadata setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public ContentMetadata setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ContentMetadata setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ContentMetadata setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public ContentMetadata setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
