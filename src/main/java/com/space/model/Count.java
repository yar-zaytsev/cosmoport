package com.space.model;

import javax.persistence.Entity;

/**
 * Created by Ярпиво on 12.05.2019.
 */
//@Entity
public class Count {
    private Integer count;

    public Count(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
