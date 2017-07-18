package com.toquery.cleverweb.web.controller.router;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ToQuery
 * @version V1.0
 * @date 2017/6/6 11:12
 */
@RestController
@RequestMapping("/vue")
public class VueRouterController {

    @GetMapping("/router")
    public List<VueRouter> getRouter() {

        VueRouter area = new VueRouter("/area", "地区管理", "Area", null);
        VueRouter dict = new VueRouter("/dict", "字典管理", "Dict", null);
        VueRouter user = new VueRouter("/user", "用户管理", "User", null);

        VueRouter root = new VueRouter("/", "系统管理", null, null);
        root.setChildren(Lists.newArrayList(area, dict, user));
        return Lists.newArrayList(root);
    }

    class VueRouter {
        private String path;
        private String menuName;
        private String component;
        private List<VueRouter> children;

        public VueRouter() {
        }

        public VueRouter(String path, String menuName, String component, List<VueRouter> children) {
            this.path = path;
            this.menuName = menuName;
            this.component = component;
            this.children = children;
        }


        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public List<VueRouter> getChildren() {
            return children;
        }

        public void setChildren(List<VueRouter> children) {
            this.children = children;
        }
    }
}
