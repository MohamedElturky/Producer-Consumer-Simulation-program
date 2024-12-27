package com.producer_consumer.simulation.domain.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Data Transfer Object (DTO) representing a system connection or action.
 */
public class SystemDto implements Serializable {
    private int fromId;
    private int toId;
    private String source;

    // Default constructor for serialization/deserialization
    public SystemDto() {
    }

    // Parameterized constructor for convenience
    public SystemDto(int fromId, int toId, String source) {
        this.fromId = fromId;
        this.toId = toId;
        this.source = source;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemDto systemDto = (SystemDto) o;
        return fromId == systemDto.fromId &&
                toId == systemDto.toId &&
                Objects.equals(source, systemDto.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromId, toId, source);
    }

    @Override
    public String toString() {
        return "SystemDto{" +
                "fromId=" + fromId +
                ", toId=" + toId +
                ", source='" + source + '\'' +
                '}';
    }
}
