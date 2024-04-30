package com.aimaginarium.utils.aspect.logger;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;

public record LogColourConstant() {
    public static final String ANSI_RESET = ESC_START + RESET + ESC_END;
    public static final String ANSI_BLACK = ESC_START + BLACK_FG + ESC_END;
    public static final String ANSI_RED = ESC_START + RED_FG + ESC_END;
    public static final String ANSI_GREEN = ESC_START + GREEN_FG + ESC_END;
    public static final String ANSI_YELLOW = ESC_START + YELLOW_FG + ESC_END;
    public static final String ANSI_BLUE = ESC_START + BLUE_FG + ESC_END;
    public static final String ANSI_MAGENTA = ESC_START + MAGENTA_FG + ESC_END;
    public static final String ANSI_CYAN = ESC_START + CYAN_FG + ESC_END;
    public static final String ANSI_WHITE = ESC_START + WHITE_FG + ESC_END;

}
