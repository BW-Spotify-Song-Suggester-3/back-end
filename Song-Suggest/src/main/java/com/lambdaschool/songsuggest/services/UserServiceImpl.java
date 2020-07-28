package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.exceptions.ResourceNotFoundException;
import com.lambdaschool.songsuggest.models.Role;
import com.lambdaschool.songsuggest.models.User;
import com.lambdaschool.songsuggest.models.UserRoles;
import com.lambdaschool.songsuggest.models.Useremail;
import com.lambdaschool.songsuggest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "userService")
public class UserServiceImpl
        implements UserService {

    @Autowired
    private UserRepository userrepos;


    @Autowired
    private RoleService roleService;

    public User findUserById(long id) throws
            ResourceNotFoundException {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username) {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id) {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name) {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null) {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0) {
            userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
                .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
                .toLowerCase());

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles()) {
            Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());
            newUser.getRoles()
                    .add(new UserRoles(newUser, addRole));
        }

        newUser.getUseremails()
                .clear();
        for (Useremail ue : user.getUseremails()) {
            newUser.getUseremails()
                    .add(new Useremail(newUser,
                            ue.getUseremail()));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
            User user,
            long id) {
        User currentUser = findUserById(id);

        if (user.getUsername() != null) {
            currentUser.setUsername(user.getUsername()
                    .toLowerCase());
        }

        if (user.getPassword() != null) {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getPrimaryemail() != null) {
            currentUser.setPrimaryemail(user.getPrimaryemail()
                    .toLowerCase());
        }

        if (user.getRoles()
                .size() > 0) {
            currentUser.getRoles()
                    .clear();
            for (UserRoles ur : user.getRoles()) {
                Role addRole = roleService.findRoleById(ur.getRole()
                        .getRoleid());

                currentUser.getRoles()
                        .add(new UserRoles(currentUser, addRole));
            }
        }

        if (user.getUseremails()
                .size() > 0) {
            currentUser.getUseremails()
                    .clear();
            for (Useremail ue : user.getUseremails()) {
                currentUser.getUseremails()
                        .add(new Useremail(currentUser,
                                ue.getUseremail()));
            }
        }

        return userrepos.save(currentUser);
    }

    @Transactional
    @Override
    public void deleteAll() {
        userrepos.deleteAll();
    }
}
