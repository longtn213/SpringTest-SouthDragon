package com.SouthDragon.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Don't run until JIRA #123 resolved")
    void basicTest(){
        //execute method and perform test
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly(){
        //execute method and perform test
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly(){
        //execute method and perform test
    }


    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForMacAndWindowsOnly(){
        //execute method and perform test
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly(){
        //execute method and perform test
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testForOnlyJava21(){
        //execute method and perform test
    }
    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testForOnlyJava13(){
        //execute method and perform test
    }
    @Test
    @EnabledForJreRange(min=JRE.JAVA_13, max = JRE.JAVA_17)
    void testForOnlyJavaRange(){
        //execute method and perform test
    }
    @Test
    @EnabledIfEnvironmentVariable(named = "SouthDragon", matches = "DEV")
    void testForOnlyDevEnvironment(){
        //execute method and perform test
    }
    @Test
    @EnabledIfSystemProperty(named = "SouthDragon-Sys-Prop", matches = "CI_CD_DEPLOY")
    void testForOnlySystemProperty(){
        //execute method and perform test
    }
}
