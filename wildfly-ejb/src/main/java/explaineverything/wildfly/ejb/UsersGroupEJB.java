package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.model.User;
import explaineverything.wildfly.vo.GroupVO;
import explaineverything.wildfly.vo.UserGroupRq;
import explaineverything.wildfly.vo.UserVO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UsersGroupEJB {

    @Inject
    private UserEJB userEJB;

    @Inject
    private GroupEJB groupEJB;

    private UserVO createUserVo(User user) {
        if (user != null) {
            UserVO rs = new UserVO();
            rs.setId(user.getId());
            rs.setEmail(user.getEmail());
            rs.setType(user.getType());
            rs.setCreated(user.getCreated());
            rs.setName(user.getName());
            List<Group> groupList = new ArrayList<>(user.getGroups());
            rs.setGroups(groupList.stream().map(o -> {
                GroupVO vo = new GroupVO();
                vo.setCreated(o.getCreated());
                vo.setName(o.getName());
                vo.setId(o.getId());
                return vo;
            }).collect(Collectors.toList()));
            return rs;
        }
        return null;
    }

    private GroupVO createGroupVo(Group group) {
        if (group != null) {
            GroupVO rs = new GroupVO();
            rs.setId(group.getId());
            rs.setCreated(group.getCreated());
            rs.setName(group.getName());
            List<User> userList = new ArrayList<>(group.getUsers());
            rs.setUsers(userList.stream().map(o -> {
                UserVO vo = new UserVO();
                vo.setId(o.getId());
                vo.setEmail(o.getEmail());
                vo.setType(o.getType());
                vo.setCreated(o.getCreated());
                vo.setName(o.getName());
                return vo;
            }).collect(Collectors.toList()));
            return rs;
        }
        return null;
    }


    public UserVO createUser(UserVO userVO) {
        User user = new User();
        user.setName(userVO.getName());
        user.setEmail(userVO.getEmail());
        user.setType(userVO.getType());
        user.setCreated(LocalDateTime.now());
        user = userEJB.create(user);
        return createUserVo(user);
    }

    public UserVO getUser(long id) {
        return createUserVo(userEJB.get(id));
    }

    public UserVO updateUser(UserVO userVo) {
        User user = userEJB.get(userVo.getId());
        if (user == null) {
            return null;
        }
        if (userVo.getName() != null) {
            user.setName(userVo.getName());
        }
        if (userVo.getEmail() != null) {
            user.setEmail(userVo.getEmail());
        }
        if (userVo.getType() != null) {
            user.setType(userVo.getType());
        }
        if (userVo.getCreated() != null) {
            user.setCreated(userVo.getCreated());
        }
        return createUserVo(userEJB.update(user));
    }

    public void deleteUser(Long id) {
        userEJB.delete(id);
    }

    public GroupVO createGroup(GroupVO groupVO) {
        Group group = new Group();
        group.setName(groupVO.getName());
        group.setCreated(LocalDateTime.now());
        return createGroupVo(groupEJB.create(group));
    }

    public GroupVO getGroup(long id) {
        return createGroupVo(groupEJB.get(id));
    }

    public GroupVO updateGroup(GroupVO groupVO) {
        Group group = groupEJB.get(groupVO.getId());
        if (group == null) {
            return null;
        }
        if (groupVO.getName() != null) {
            group.setName(groupVO.getName());
        }
        if (groupVO.getCreated() != null) {
            group.setCreated(groupVO.getCreated());
        }
        return createGroupVo(groupEJB.update(group));
    }

    public GroupVO assignUserToGroup(UserGroupRq rq) {
        Group group = groupEJB.get(rq.getGroupId());
        if (group == null) {
            return null;
        }
        User user = userEJB.get(rq.getUserId());
        if (user == null) {
            return null;
        }
        group.addUser(user);
        return createGroupVo(groupEJB.update(group));
    }

    public GroupVO removeUser(UserGroupRq rq) {
        Group group = groupEJB.get(rq.getGroupId());
        if (group == null) {
            return null;
        }
        User user = userEJB.get(rq.getUserId());
        if (user == null) {
            return null;
        }
        group.removeUser(user);
        return createGroupVo(group);
    }

    public void deleteGroup(Long id) {
        groupEJB.delete(id);
    }

}
