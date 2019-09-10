package com.dj.ego.shiro.util;

import cn.hutool.core.util.ReflectUtil;
import com.dj.ego.shiro.config.ShiroPermissionConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @author 戴俊明
 * @className ShiroStateManager
 * @description 更新自定义的权限过滤器
 * @date 2019/9/7 16:42
 **/

@Slf4j
@Component
public class ShiroStateManager {

    @Autowired
    ShiroPermissionConfig permissionConfig;
    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    public void updateFilterChain() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new ShiroException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }
            assert shiroFilter != null;
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(permissionConfig.getUrlPermsMap());
            //TODO:清除缓存
//            userRealm.clearAllAuthCache();

//             清除每个 Filter 中的 appliedPaths 信息
            for (Map.Entry<String, Filter> filterEntry : manager.getFilters().entrySet()) {
                if (filterEntry.getValue() instanceof PathMatchingFilter) {
                    PathMatchingFilter filter = (PathMatchingFilter) filterEntry.getValue();
                    Map<String, Object> appliedPaths = (Map<String, Object>) ReflectUtil.getFieldValue(filter, "appliedPaths");
                    synchronized (appliedPaths) {
                        appliedPaths.clear();
                    }
                }
            }

            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean
                    .getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            log.info("更新 Shiro 过滤器链");
        }
    }

}
