package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.DataCategory;

/**
 * A SearchData.
 */
@Entity
@Table(name = "search_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "searchdata")
public class SearchData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 256)
    @Column(name = "title", length = 256, nullable = false)
    private String title;

    @Size(max = 2000)
    @Column(name = "content", length = 2000)
    private String content;

    @Size(max = 20)
    @Column(name = "source_type", length = 20)
    private String sourceType;

    @Size(max = 64)
    @Column(name = "entity_id", length = 64)
    private String entityId;

    @NotNull
    @Max(value = 9)
    @Column(name = "kudos_num", nullable = false)
    private Integer kudosNum;

    @NotNull
    @Max(value = 9)
    @Column(name = "comment_num", nullable = false)
    private Integer commentNum;

    @NotNull
    @Max(value = 9)
    @Column(name = "read_num", nullable = false)
    private Integer readNum;

    @NotNull
    @Max(value = 9)
    @Column(name = "collection_num", nullable = false)
    private Integer collectionNum;

    @Size(max = 32)
    @Column(name = "source", length = 32)
    private String source;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private DataCategory category;

    @Column(name = "status")
    private Integer status;

    @Lob
    @Column(name = "origin_data")
    private String originData;

    @Size(max = 64)
    @Column(name = "last_modified_by", length = 64)
    private String lastModifiedBy;

    @Column(name = "last_modified_time")
    private ZonedDateTime lastModifiedTime;

    @NotNull
    @Size(max = 64)
    @Column(name = "created_by", length = 64, nullable = false)
    private String createdBy;

    @Column(name = "created_time")
    private ZonedDateTime createdTime;

    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "version")
    private Integer version;

    @Size(max = 64)
    @Column(name = "tenant_id", length = 64)
    private String tenantId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public SearchData title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public SearchData content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceType() {
        return sourceType;
    }

    public SearchData sourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getEntityId() {
        return entityId;
    }

    public SearchData entityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getKudosNum() {
        return kudosNum;
    }

    public SearchData kudosNum(Integer kudosNum) {
        this.kudosNum = kudosNum;
        return this;
    }

    public void setKudosNum(Integer kudosNum) {
        this.kudosNum = kudosNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public SearchData commentNum(Integer commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public SearchData readNum(Integer readNum) {
        this.readNum = readNum;
        return this;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public SearchData collectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
        return this;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getSource() {
        return source;
    }

    public SearchData source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public DataCategory getCategory() {
        return category;
    }

    public SearchData category(DataCategory category) {
        this.category = category;
        return this;
    }

    public void setCategory(DataCategory category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public SearchData status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOriginData() {
        return originData;
    }

    public SearchData originData(String originData) {
        this.originData = originData;
        return this;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public SearchData lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public SearchData lastModifiedTime(ZonedDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
        return this;
    }

    public void setLastModifiedTime(ZonedDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SearchData createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public SearchData createdTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean isDelFlag() {
        return delFlag;
    }

    public SearchData delFlag(Boolean delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getVersion() {
        return version;
    }

    public SearchData version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTenantId() {
        return tenantId;
    }

    public SearchData tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchData searchData = (SearchData) o;
        if (searchData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), searchData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SearchData{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", sourceType='" + getSourceType() + "'" +
            ", entityId='" + getEntityId() + "'" +
            ", kudosNum=" + getKudosNum() +
            ", commentNum=" + getCommentNum() +
            ", readNum=" + getReadNum() +
            ", collectionNum=" + getCollectionNum() +
            ", source='" + getSource() + "'" +
            ", category='" + getCategory() + "'" +
            ", status=" + getStatus() +
            ", originData='" + getOriginData() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedTime='" + getLastModifiedTime() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", delFlag='" + isDelFlag() + "'" +
            ", version=" + getVersion() +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
