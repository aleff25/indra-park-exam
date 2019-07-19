package com.indraparkapi.domain.park.dashboard;

import java.util.Date;
import java.util.Map;

public class Dashboard {

    private Date date;
    private Map<String, Long> options;

    public Dashboard(Date dash, Map<String, Long> models) {
        this.date = dash;
        this.options = models;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Long> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Long> options) {
        this.options = options;
    }
}
