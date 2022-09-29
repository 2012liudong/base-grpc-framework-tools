package com.zd.tools.project.generator.analysis;

import cn.hutool.log.Log;
import com.zd.tools.project.generator.ExtCache;
import com.zd.tools.project.generator.model.file.ExtFileLoadUtil;
import lombok.Data;

@Data
public abstract class AbstractProcess {
    private static final Log log = Log.get();

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

    public void before(){
        ExtCache.extSourceFile = ExtFileLoadUtil.loadExtFile();
    }

    abstract protected void parse();

    abstract protected void clear();

    abstract protected void build();

    public final void process(){
        before();
        parse();
        if(context.hasError()){
            context.getErrorList().stream().forEach(item -> log.error(item));
            return;
        }
        clear();
        build();
    }
}
