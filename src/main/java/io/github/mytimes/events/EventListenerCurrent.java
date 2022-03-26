package io.github.mytimes.events;

import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Protocol;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.atomic.AtomicLong;


public class EventListenerCurrent extends EventListener {
    private Long time;
    private Long caller;

    public void callEnd(Call call) {
        System.out.println(call.request().url().toString() + "|" + time + "|" + caller + "|" + "caller end ");
    }


    public void callStart(Call call) {
        System.out.println(call.request().url().toString() + "|" + time + "|" + caller + "|" + "caller start ");
    }

    public void responseHeadersEnd(
            Call call,
            Response response) {
        System.out.println(call.request().url().toString() + "responseHeadersEnd | " + response.code());

    }

    public void connectFailed(
            Call call,
            InetSocketAddress inetSocketAddress,
            Proxy roxy,
            Protocol protocol,
            IOException ioe
    ) {
        System.out.println("connectFailed");
    }


    public void requestFailed(
            Call call,
            IOException ioe
    ) {
        System.out.println("requestFailed");
    }

    public void responseFailed(
            Call call,
            IOException ioe
    ) {
        System.out.println("responseFailed");
    }

    public void callFailed(
            Call call,
            IOException ioe
    ) {
        System.out.println("callFailed");
    }

    public void satisfactionFailure(Call call, Response response) {
        System.out.println("satisfactionFailure");
    }


    EventListenerCurrent(long time, Long callerId) {
        this.time = time;
        this.caller = callerId;
    }

    public static final Factory FACTORY = new Factory() {
        final AtomicLong nextCallId = new AtomicLong(1L);

        @Override
        public EventListener create(Call call) {
            long callId = nextCallId.getAndIncrement();
            return new EventListenerCurrent(System.nanoTime(), callId);
        }
    };
}
