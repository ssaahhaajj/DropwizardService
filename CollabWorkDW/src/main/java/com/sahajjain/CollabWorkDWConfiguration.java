package com.sahajjain;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;

public class CollabWorkDWConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName="Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }
    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }
    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }
    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public void setDatabase(DataSourceFactory factory) {
        this.database=factory;
    }
    public DataSourceFactory getDatabase() {
        return database;
    }

}
