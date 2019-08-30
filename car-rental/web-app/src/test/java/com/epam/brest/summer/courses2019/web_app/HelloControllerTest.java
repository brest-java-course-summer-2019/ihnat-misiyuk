package com.epam.brest.summer.courses2019.web_app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:../resources/app-context-test.xml"})
public class HelloController {
}
