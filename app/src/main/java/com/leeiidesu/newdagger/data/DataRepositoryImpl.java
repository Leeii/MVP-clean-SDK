package com.leeiidesu.newdagger.data;


import com.leeiidesu.newdagger.domain.DataRepository;

/**
 * Created by liyi on 2018/4/27.
 */

public class DataRepositoryImpl implements DataRepository {
    public DataRepositoryImpl() {
    }


    @Override
    public String append(String format) {
        return " DataRepositoryImpl - >" + format;
    }
}
