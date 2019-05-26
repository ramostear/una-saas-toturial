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

package com.ramostear.una.saas.common.util;

import com.ramostear.una.saas.common.lang.ObjectIdentifier;

import java.util.Random;

/**
 * @author : Created by Tan Chaohong (alias:ramostear)
 * @create-time 2019/5/25 0025-8:04
 * @modify by :
 * @since:
 */
public class TwitterIdentifier implements ObjectIdentifier<Long> {
    @Override
    public Long general() {
        return next();
    }

    @Override
    public String generalIdentifier(String prefix) {
        return prefix+next();
    }

    public String generalIdentifier(){
        return generalIdentifier("");
    }

    private static final long EPOCH = 1264953600000L;

    private static final int BITS_F0 = 10;
    private static final int BITS_F1 = 4;
    private static final int BITS_F2 = 4;

    private static final int SHIFT_F1 = BITS_F0;
    private static final int SHIFT_F2 = (BITS_F0 + BITS_F1);
    private static final int SHIFT_F3 = (BITS_F0 + BITS_F1 + BITS_F2);

    private static final int MASK_F0 = (1 << BITS_F0) - 1;
    private static final int MASK_F1 = (1 << BITS_F1) - 1;
    private static final int MASK_F2 = (1 << BITS_F2) - 1;

    /**
     * 机器ID
     */
    private long machine;
    /**
     * 进程ID
     */
    private long pid;
    /**
     * 序列号
     */
    private long sequence;
    /**
     * 最新的时间戳
     */
    private long latestTs;

    public TwitterIdentifier(){
        this(new Random().nextLong(),new Random().nextLong());
    }

    private TwitterIdentifier(long machine, long pid){
        this.machine = (machine & MASK_F2)<< SHIFT_F2;
        this.pid = (pid & MASK_F1) << SHIFT_F1;
    }

    private synchronized long next(){
        long timestamp = currentMs();
        if(timestamp < latestTs){
            throw new RuntimeException("older timestamp.");
        }
        if(latestTs == timestamp){
            sequence = (sequence +1 ) & MASK_F0;
            if(sequence == 0){
                timestamp = nextMs(latestTs);
            }
        }else{
            sequence = 0;
        }
        long id = ((timestamp - EPOCH) << SHIFT_F3) | machine | pid | sequence;
        latestTs = timestamp;
        return id;
    }

    private long currentMs(){
        return System.currentTimeMillis();
    }

    private long nextMs(long latestTs){
        long timestamp = currentMs();
        while (timestamp <= latestTs){
            timestamp = currentMs();
        }
        return timestamp;
    }
}
