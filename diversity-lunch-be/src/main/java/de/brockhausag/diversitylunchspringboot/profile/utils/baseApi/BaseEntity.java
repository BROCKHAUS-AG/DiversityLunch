package de.brockhausag.diversitylunchspringboot.profile.utils.baseApi;

public interface BaseEntity {
    Long getId();
    void setId(Long id);
    String getDescriptor();
    void setDescriptor(String descriptor);
}
