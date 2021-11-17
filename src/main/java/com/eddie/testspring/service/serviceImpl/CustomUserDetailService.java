package com.eddie.testspring.service.serviceImpl;

import com.eddie.testspring.model.User;
import com.eddie.testspring.model.Permission;
import com.eddie.testspring.model.Role;
import com.eddie.testspring.repository.PermissionDao;
import com.eddie.testspring.repository.RoleDao;
import com.eddie.testspring.repository.UserDao;
import com.eddie.testspring.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 1:36 下午
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsernameOrEmailOrPhone(s,s,s).orElseThrow(()-> new UsernameNotFoundException("未找到用户信息："+ s));
        List<Role> roles = roleDao.selectByUserId(user.getId());
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);
        return UserPrincipal.create(user,roles,permissions);
    }
}
