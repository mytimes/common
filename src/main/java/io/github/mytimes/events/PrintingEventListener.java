package io.github.mytimes.events;

import okhttp3.Call;
import okhttp3.EventListener;

public class PrintingEventListener extends EventListener {
    public void callEnd(Call call) {
       System.out.println("caller end " + call.request().url().toString());
    }


    public void callStart(Call call) {
        System.out.println("caller start " + call.request().url().toString());
    }

}
