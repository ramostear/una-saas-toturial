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

package com.ramostear.una.saas.common.util;

import com.ramostear.una.saas.master.model.MasterTenant;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

/**
 * @author : Created by Tan Chaohong (alias:ramostear)
 * @create-time 2019/5/26 0026-22:15
 * @modify by :
 * @since:
 */
@Slf4j
public class DataSourceUtils {

    public static DataSource wrapperDataSource(MasterTenant tenant){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(tenant.getUsername());
        dataSource.setPassword(tenant.getPassword());
        dataSource.setJdbcUrl(tenant.getUrl());
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setConnectionTimeout(20000);
        dataSource.setMinimumIdle(10);
        dataSource.setMaximumPoolSize(20);
        dataSource.setIdleTimeout(300000);
        dataSource.setConnectionTimeout(20000);
        String tenantId = tenant.getId();
        String tenantConnectionPoolName = tenantId+"-connection-pool";
        dataSource.setPoolName(tenantConnectionPoolName);
        log.info("Configuration datasource:{},Connection pool name:{}",tenant.getId(),tenantConnectionPoolName);
        return dataSource;
    }

}
