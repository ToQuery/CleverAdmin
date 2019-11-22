package io.github.toquery.cleverweb.entity;

import io.github.toquery.framework.dao.entity.AppBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "example_article")
public class ExampleArticle extends AppBaseEntity {

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ExampleArticleType type = ExampleArticleType.NEWS;

    @Column(name = "published")
    private boolean published = false;

    @Column(name = "show_date")
    private Date showDate;

    @Lob
    @Column(columnDefinition = "text", name = "content")
    private String content;
}
