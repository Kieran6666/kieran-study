package com.kieran.practice.canal.handle;

import com.kieran.practice.model.User;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

//@Component
@CanalTable("m_user")
public class UserHandle implements EntryHandler<User> {


}
