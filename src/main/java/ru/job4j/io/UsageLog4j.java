package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte b = Byte.MAX_VALUE;
        short sh = 32000;
        int i = 10;
        long l = 100L;
        float f = 1.0F;
        double d = 10.0D;
        char c = 'A';
        boolean is = true;

        LOG.debug("byte {} short {} int {} long {} float {} double {} char {} boolean {}", b, sh, i, l, f, d, c, is);



    }
}
