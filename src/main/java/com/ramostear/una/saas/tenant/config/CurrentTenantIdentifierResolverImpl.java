/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName:una-saas
 * @Author : Tan Chaohong
 * @date : 2019 - 5 - 26
 * @version : 2.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.ramostear.una.saas.tenant.config;

import com.ramostear.una.saas.context.TenantContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * @author : Created by Tan Chaohong (alias:ramostear)
 * @create-time 2019/5/26 0026-22:38
 * @modify by :
 * @since:
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    /**
     * 默认的租户ID
     */
    private static final String DEFAULT_TENANT = "tenant_1";

    /**
     * 解析当前租户的ID
     * @return
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        //通过租户上下文获取租户ID，此ID是用户登录时在header中进行设置的
        String tenant = TenantContextHolder.getTenant();
        //如果上下文中没有找到该租户ID，则使用默认的租户ID，或者直接报异常信息
        return StringUtils.isNotBlank(tenant)?tenant:DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
