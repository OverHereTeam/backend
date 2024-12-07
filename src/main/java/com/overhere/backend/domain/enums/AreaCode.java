package com.overhere.backend.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum AreaCode {
    SEOUL("1"), GYEONGGI("31"), GANGWON("32"), GYEONG_BUK("35"), GYEONG_NAM("36"),
    JEON_BUK("37"), JEON_NAM("38"), JEJU("39");
    
    public final String code;
    
    AreaCode(String code) {
        this.code = code;
    }
}
