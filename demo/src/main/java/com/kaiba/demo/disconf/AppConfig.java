package com.kaiba.demo.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfItem;
import org.springframework.stereotype.Component;

/**
 * Created by luliru on 2017/5/20.
 */
@Component
@DisconfFile(filename = "welcomeWords")
public class AppConfig {

    private String welcomeWords;

    @DisconfItem(key = "welcomeWords")
    public String getWelcomeWords() {
        return welcomeWords;
    }

    public void setWelcomeWords(String welcomeWords) {
        this.welcomeWords = welcomeWords;
    }
}
