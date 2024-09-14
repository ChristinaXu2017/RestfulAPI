package org.gov.qld.maintenance.request;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "requests")
public class Request {

    @Id
    private long id;
    private String type;

    @Enumerated(EnumType.STRING)
    private Priority poriority;
    private String description;

    // Default constructor
    public Request() {
    }

    // Parameterized constructor
    public Request(long id, String type, Priority poriority, String description) {
        this.id = id;
        this.type = type;
        this.poriority = poriority;
        this.description = description;
    }

    // Getters and setters
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

    public Priority getPoriority() {
        return poriority;
    }

    public void setPoriority(Priority poriority) {
        this.poriority = poriority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}



