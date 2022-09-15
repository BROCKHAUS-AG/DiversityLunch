package de.brockhausag.diversitylunchspringboot.profile.utils.genericOverload;

public interface BaseEntityForGenericMapper<T> {
    Long getId();
    void setId(Long id);
    String getDescriptor();
    void setDescriptor(String descriptor);
    T clone();
}
