package com.gsuna.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.gsuna.project.enums.Status;

@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Column
	@Convert(converter = Status.Converter.class)
	private Status status = Status.ACTIVE;

	@Version
	@Column
	private int version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@PrePersist
	public void prePersist() {
        createDate=new Date();
    }
 
    @PreUpdate
    public void preUpdate() {
        updateDate=new Date();
    }
	
}
