package explaineverything.wildfly.vo;

import explaineverything.wildfly.model.Type;

import java.time.LocalDateTime;
import java.util.List;

public class UserVO {
    private Long id;
    private String name;
    private String email;
    private Type type;
    private List<GroupVO> groups;
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<GroupVO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupVO> groups) {
        this.groups = groups;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
