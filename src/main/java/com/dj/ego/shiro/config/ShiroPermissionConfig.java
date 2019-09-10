package com.dj.ego.shiro.config;

import cn.hutool.core.util.StrUtil;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Access;
import com.dj.ego.shiro.realm.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 戴俊明
 * @version 1.0
 * @className ShiroPermissionConfig
 * @description 权限匹配的设置
 * @date 2019/9/7 23:28
 **/

@Slf4j
@Component
public class ShiroPermissionConfig {

    @Autowired
    private IService<Access> accessService;

    @Autowired
    private UserRealm userRealm;

    /**
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author 戴俊明
     * @description 从数据库加载用户拥有的菜单权限和 API 权限.
     * @date 2019/9/7 23:28
     **/
    public Map<String, String> getUrlPermsMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/auth/logout", "noSessionCreation,authcToken[permissive]");
        filterChainDefinitionMap.put("/auth/**", "noSessionCreation,anon");

        filterChainDefinitionMap.put("/swagger-ui.html", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/v2/common-docs", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/webjars/**", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/doc.html", "noSessionCreation,anon");

        filterChainDefinitionMap.put("/druid/**", "noSessionCreation,anon");

        List<Access> accessList = accessService.selectAll();
        Collections.reverse(accessList);

        List<Access> pageList = accessList.stream()
                .filter(access -> access.getType() < 3)
                .collect(Collectors.toList());
        for (Access access : pageList) {
            String url = access.getUrl();
            if (url != null) {
                String perms = "noSessionCreation,authcToken,access[" + access.getCode() + "]";
                filterChainDefinitionMap.put(url, perms);
            }
        }

        List<Access> dataList = accessList.stream()
                .filter(access -> access.getType() == 3)
                .collect(Collectors.toList());

        dataList.stream()
                .filter(access -> access.getCode().contains("access"))
                .forEach(access -> {
                    String url = access.getUrl();
                    if (url != null) {
                        String method = Access.METHODS.get(access.getMethod());
                        if (StrUtil.isNotEmpty(method)) {
                            url += ("==" + method);
                        }
                        filterChainDefinitionMap.put(url,
                                "noSessionCreation,authcToken,access[" + access.getCode() + "]");
                    }
                });

        dataList.stream()
                .filter(access -> !access.getCode().contains("access"))
                .forEach(access -> {
                    String url = access.getUrl();
                    if (url != null) {
                        String method = Access.METHODS.get(access.getMethod());
                        if (StrUtil.isNotEmpty(method)) {
                            url += ("==" + method);
                        }
                        filterChainDefinitionMap.put(url,
                                "noSessionCreation,authcToken,access[" + access.getCode() + "]");
                    }
                });

//        filterChainDefinitionMap.put("/**", "noSessionCreation,authcToken");

        return filterChainDefinitionMap;
    }

    private String createShiroAccess(Access access) {
        String url = access.getUrl();
        if (url != null) {
            String method = Access.METHODS.get(access.getMethod());
            if (StrUtil.isNotEmpty(method)) {
                url += ("==" + method);
            }
            return "noSessionCreation,authcToken,access[" + access.getCode() + "]";
        }
        return null;
    }

}