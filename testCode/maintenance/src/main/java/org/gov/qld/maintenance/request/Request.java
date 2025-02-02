package org.gov.qld.maintenance.request;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String description;
    
    // admin only column
    private Boolean approval;
    private String comments;

    // Default constructor
    public Request() {}

    // for user post
    public Request(String theType, Priority priority, String description) {
        this.type = theType;
        this.priority = priority;
        this.description = description;
        this.approval = null;
        this.comments = null;
    }
    
    // Parameterized constructor for sql insert
    public Request(long id, String theType, Priority poriority, String description, Boolean approval, String comments) {
        this.id = id;
        this.type = theType;
        this.priority = poriority;
        this.description = description;
        this.approval = approval;
        this.comments = comments;
    }
    
    public enum Priority {
        LOW("low"),
        MED("med"),
        HIGH("high");

        private final String value;

        Priority(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}