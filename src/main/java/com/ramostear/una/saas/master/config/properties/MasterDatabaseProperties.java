/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName:una-saas
 * @Author : Tan Chaohong
 * @date : 2019 - 5 - 25
 * @version : 2.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.ramostear.una.saas.master.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Created by Tan Chaohong (alias:ramostear)
 * @create-time 2019/5/25 0025-8:32
 * @modify by :
 * @since:
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("una.master.datasource")
public class MasterDatabaseProperties {

    private String url;

    private String password;

    private String username;

    private String driverClassName;

    private long connectionTimeout;

    private int maxPoolSize;

    private long idleTimeout;

    private int minIdle;

    private String poolName;

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("MasterDatabaseProperties [ url=")
                .append(url)
                .append(", username=")
                .append(username)
                .append(", password=")
                .append(password)
                .append(", driverClassName=")
                .append(driverClassName)
                .append(", connectionTimeout=")
                .append(connectionTimeout)
                .append(", maxPoolSize=")
                .append(maxPoolSize)
                .append(", idleTimeout=")
                .append(idleTimeout)
                .append(", minIdle=")
                .append(minIdle)
                .append(", poolName=")
                .append(poolName)
                .append("]");
        return builder.toString();
    }
}
