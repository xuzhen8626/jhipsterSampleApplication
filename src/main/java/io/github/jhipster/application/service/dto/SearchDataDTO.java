package io.github.jhipster.application.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.DataCategory;

/**
 * A DTO for the SearchData entity.
 */
public class SearchDataDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 256)
    private String title;

    @Size(max = 2000)
    private String content;

    @Size(max = 20)
    private String sourceType;

    @Size(max = 64)
    private String entityId;

    @NotNull
    @Max(value = 9)
    private Integer kudosNum;

    @NotNull
    @Max(value = 9)
    private Integer commentNum;

    @NotNull
    @Max(value = 9)
    private Integer readNum;

    @NotNull
    @Max(value = 9)
    private Integer collectionNum;

    @Size(max = 32)
    private String source;

    private DataCategory category;

    private Integer status;

    @Lob
    private String originData;

    @Size(max = 64)
    private String lastModifiedBy;

    private ZonedDateTime lastModifiedTime;

    @NotNull
    @Size(max = 64)
    private String createdBy;

    private ZonedDateTime createdTime;

    private Boolean delFlag;

    private Integer version;

    @Size(max = 64)
    private String tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getKudosNum() {
        return kudosNum;
    }

    public void setKudosNum(Integer kudosNum) {
        this.kudosNum = kudosNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public DataCategory getCategory() {
        return category;
    }

    public void setCategory(DataCategory category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOriginData() {
        return originData;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(ZonedDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SearchDataDTO searchDataDTO = (SearchDataDTO) o;
        if(searchDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), searchDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SearchDataDTO{" +
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
