package com.some_domain.www.multithreadingproblem_1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataStructure {

    private Map<String, List<Map<String, Ball>>> ds = null;

    public DataStructure() {
        this.ds = new LinkedHashMap<>();
    }

    public Map<String, List<Map<String, Ball>>> getDs() {
        return ds;
    }

    public void setDs(Map<String, List<Map<String, Ball>>> ds) {
        this.ds = ds;
    }
}
