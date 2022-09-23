package com.zd.tools.project.generator.analysis;

import lombok.Data;

@Data
public abstract class AbstractProcess {

    private Context context;

    public AbstractProcess(Context context) {
        this.context = context;
    }

    public void error(String msg, Object... params) {
        this.context.addError(msg, params);
    }

    public boolean hasError() {
        return this.context.hasError();
    }

    abstract protected void validate();

    abstract protected void parse();

    abstract protected void clear();

    abstract protected void build();

    public final void process(){
        validate();
        parse();
        if(!context.hasError()){
            clear();
            build();
        }
    }
}
