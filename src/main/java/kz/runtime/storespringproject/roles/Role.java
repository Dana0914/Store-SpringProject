package kz.runtime.storespringproject.roles;

import java.util.Collection;
import java.util.List;

public enum Role {
    USER, ADMIN;

    public Collection<Object> getPrivileges() {
        return List.of();
    }
}
