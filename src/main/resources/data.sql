insert into sys_menu (id, code, name, level, sort_num, parent_id, parent_ids, has_children, create_user_id, create_time,
                      last_update_user_id, last_update_time)
values (1, 'root', '根菜单', 0, 1, null, null, true, null, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (101, 'system', '系统管理', 1, 1, 1, '1', true, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10101, 'user', '用户管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10102, 'role', '角色管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10103, 'menu', '菜单管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10104, 'log', '日志管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (10105, 'config', '配置管理', 2, 1, 101, '1,101', false, 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11');

insert into sys_role (id,name, create_user_id, create_time, last_update_user_id, last_update_time)
values (1, '管理员', 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11'),
       (2, '普通用户', 1, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11');

insert into sys_user (id, user_name,nick_name, password, email, enabled, last_password_reset_date, create_user_id,
                      create_time, last_update_user_id, last_update_time)
values (1, 'admin', 'admin','$2a$10$71U/mNIYhw8qH8z9YURdpOgxqFVcOFucWl6TGCYTaKRuruqnQ7kRO', 'admin@qq.com', true, '2019-06-06 11:11:11', 1, '2019-06-06 11:11:11', 1,
        '2019-06-06 11:11:11');

insert into sys_user_role (user_id, role_id)
values (1, 1),
       (1, 2);

insert into sys_role_menu(menu_id, role_id)
values (101, 1),
       (10101, 1),
       (10102, 1),
       (10103, 1),
       (10104, 1),
       (10105, 1);
