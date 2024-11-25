package com.example.project.base.handlers;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private int getCurrentUnixTimestamp() {
        double timestamp = System.currentTimeMillis() / 1000;
        return (int) timestamp;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", getCurrentUnixTimestamp(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", getCurrentUnixTimestamp(), metaObject);
    }

}