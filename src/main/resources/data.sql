insert into sys_menu (id, code, name, level, sort_num, parent_id, parent_ids, has_children, create_user_id, create_time,
                      last_update_user_id, last_update_time)
values (1, 'root', '根菜单', 0, 1, null, null, true, null, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (101, 'system', '系统管理', 1, 1, 1, '1', true, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10101, 'user', '用户管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10102, 'role', '角色管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10103, 'menu', '菜单管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11');

insert into sys_role (id, code, name, create_user_id, create_time, last_update_user_id, last_update_time)
values (1, 'admin', '前端演示角色', 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (2, 'root', '后端超级管理员', 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11');

insert into sys_user (id, user_name,nick_name, password, email, enabled, last_password_reset_date, create_user_id,
                      create_time, last_update_user_id, last_update_time)
values (1, 'admin', 'admin','admin', 'admin@qq.com', true, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11', 1,
        '2019-06-06 11:11:11'),
       (2, 'root', 'root','root', 'root@qq.com', true, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11', 1,
        '2019-06-06 11:11:11');

insert into sys_user_role (user_id, role_id)
values (1, 1),
       (1, 2);

insert into sys_role_menu(menu_id, role_id)
values (10101, 2),
       (10103, 2);
