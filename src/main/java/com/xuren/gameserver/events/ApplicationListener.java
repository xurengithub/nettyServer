package com.xuren.gameserver.events;

public interface ApplicationListener {
    public void onApplicationEvent(BaseUserEvent event);
}
