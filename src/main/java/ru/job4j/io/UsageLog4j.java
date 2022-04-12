package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 127;
        short sh = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = 3.4e+38f;
        double d = 1.7e+308;
        char ch = 'c';
        boolean boo = true;

        LOG.debug("Maximum values of: byte - {}, short - {}, int - {}, long - {}, float - {}, double - {}. Examples of: char - {}, boolean - {}.", b, sh, i, l, f, d, ch, boo);
    }
}
